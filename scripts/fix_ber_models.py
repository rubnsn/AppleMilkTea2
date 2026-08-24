#!/usr/bin/env python3
"""
Fix BER model LayerDefinitions from master 1.7.10 source.
Regenerates all Model*.java in src/main/java/mods/defeatedcrow/client/model/model/
with correct rotations, texOffs, addBox, mirror, and texture size.

Usage: python scripts/fix_ber_models.py
"""
import os, re, subprocess, pathlib

ROOT = pathlib.Path("E:/AppleMilkTea2")
MODEL_DIR = ROOT / "src/main/java/mods/defeatedcrow/client/model/model"
MASTER_PREFIX = "master:src/main/java/mods/defeatedcrow/client/model/model/"

def get_master(fname):
    try:
        out = subprocess.check_output(["git","show", MASTER_PREFIX+fname], cwd=str(ROOT))
        return out.decode("utf-8", errors="ignore")
    except subprocess.CalledProcessError:
        return None

def parse_master(content):
    # Global texture size
    global_w = None
    global_h = None
    m = re.search(r'textureWidth\s*=\s*(\d+)', content)
    if m:
        global_w = int(m.group(1))
        mh = re.search(r'textureHeight\s*=\s*(\d+)', content)
        if mh:
            global_h = int(mh.group(1))
    # per-part texture sizes
    per_sizes = re.findall(r'setTextureSize\((\d+)\s*,\s*(\d+)\)', content)
    # fallback size: most common per_sizes or global
    tex_w, tex_h = None, None
    if global_w and global_h:
        tex_w, tex_h = global_w, global_h
    elif per_sizes:
        # most common
        from collections import Counter
        cnt = Counter(per_sizes)
        (tex_w, tex_h) = tuple(map(int, cnt.most_common(1)[0][0]))
    else:
        tex_w, tex_h = 64, 32

    # Find parts
    # parts are variables that are assigned new ModelRenderer(this, u,v)
    # pattern captures name, u, v
    part_pat = re.compile(r'(\w+)\s*=\s*(?:\(\s*)?new\s+ModelRenderer\s*\(\s*this\s*,\s*(\d+)\s*,\s*(\d+)\s*\)')
    parts = []
    seen = set()
    for m in part_pat.finditer(content):
        name, u, v = m.group(1), m.group(2), m.group(3)
        if name in seen:
            continue
        seen.add(name)
        # For each part, find its data after its declaration position
        pos = m.end()
        # Search in remaining content (not limited window) for correct part's addBox etc
        # Use content[pos:] to handle fields declared far from constructor
        remaining = content[pos:]
        # addBox: name.addBox(...)
        ab = re.search(rf'{re.escape(name)}\s*\.\s*addBox\s*\(\s*([^)]+)\s*\)', remaining)
        if not ab:
            continue
        box_raw = ab.group(1).strip()
        # handle 7-param addBox (x,y,z,w,h,d, scale) -> keep first 6
        box_parts = [p.strip() for p in box_raw.split(",")]
        if len(box_parts) == 7:
            # last is scale/deformation, ignore if 0.0F, otherwise keep as deformation (not handled, assume 0)
            box = ", ".join(box_parts[:6])
        else:
            box = box_raw
        # rotationPoint - first try setRotationPoint, fallback to rotationPointX/Y/Z fields
        rp = re.search(rf'{re.escape(name)}\s*\.\s*setRotationPoint\s*\(\s*([^)]+)\s*\)', remaining)
        if rp:
            rp_val = rp.group(1).strip()
        else:
            # Check for direct field assignment: name.rotationPointX = ..., Y, Z
            rxp = re.search(rf'{re.escape(name)}\s*\.\s*rotationPointX\s*=\s*([^;]+);', remaining)
            ryp = re.search(rf'{re.escape(name)}\s*\.\s*rotationPointY\s*=\s*([^;]+);', remaining)
            rzp = re.search(rf'{re.escape(name)}\s*\.\s*rotationPointZ\s*=\s*([^;]+);', remaining)
            if rxp and ryp and rzp:
                rp_val = f"{rxp.group(1).strip()}, {ryp.group(1).strip()}, {rzp.group(1).strip()}"
            else:
                # also try with 'this.' prefix
                rxp = re.search(rf'this\.{re.escape(name)}\s*\.\s*rotationPointX\s*=\s*([^;]+);', remaining)
                ryp = re.search(rf'this\.{re.escape(name)}\s*\.\s*rotationPointY\s*=\s*([^;]+);', remaining)
                rzp = re.search(rf'this\.{re.escape(name)}\s*\.\s*rotationPointZ\s*=\s*([^;]+);', remaining)
                if rxp and ryp and rzp:
                    rp_val = f"{rxp.group(1).strip()}, {ryp.group(1).strip()}, {rzp.group(1).strip()}"
                else:
                    rp_val = "0F, 0F, 0F"
        # mirror
        mir = re.search(rf'{re.escape(name)}\s*\.\s*mirror\s*=\s*true', remaining)
        has_mirror = bool(mir)
        # but ensure mirror is close (within 1000 chars after addBox)
        if mir and mir.start() > 2000:
            # Check distance from addBox
            if mir.start() - ab.start() > 2000:
                has_mirror = False
        # rotation: setRotation(name, x,y,z) or this.setRotation(name,...)
        rot_pat = re.compile(r'(?:this\.)?setRotation\s*\(\s*' + re.escape(name) + r'\s*,\s*([^,]+)\s*,\s*([^,]+)\s*,\s*([^)]+)\s*\)')
        rm = rot_pat.search(content)  # search whole content, not just window, because setRotation may be far after (but usually close)
        # More accurate: search in window plus a bit more
        if not rm:
            # search in broader window from pos to pos+3000
            rm = rot_pat.search(content[pos:pos+3000])
            if rm:
                rx, ry, rz = rm.group(1).strip(), rm.group(2).strip(), rm.group(3).strip()
            else:
                rx, ry, rz = "0F", "0F", "0F"
        else:
            # Need to ensure we get the one for this part, not a different part that happens to be found earlier
            # Find all occurrences and match the one for this part closest after declaration
            # Let's find all and pick the first after pos
            all_rots = list(rot_pat.finditer(content))
            rx, ry, rz = "0F", "0F", "0F"
            for r in all_rots:
                if r.group(0).find(name) != -1 and r.start() > m.start():
                    rx, ry, rz = r.group(1).strip(), r.group(2).strip(), r.group(3).strip()
                    break
        # texOffs u,v already captured
        parts.append({
            "name": name,
            "u": u,
            "v": v,
            "box": box,
            "rp": rp_val,
            "mirror": has_mirror,
            "rx": rx,
            "ry": ry,
            "rz": rz,
        })
    return (tex_w, tex_h), parts

def generate_java(fname, tex_w, tex_h, parts):
    classname = fname.replace(".java","")
    # Determine field declarations: private final ModelPart name;
    fields = [p["name"] for p in parts]
    # Build createBodyLayer lines
    body_lines = []
    for p in parts:
        u, v = p["u"], p["v"]
        box = p["box"]
        rp = p["rp"]
        rx, ry, rz = p["rx"], p["ry"], p["rz"]
        mirror = p["mirror"]
        # Check if rotation is effectively zero
        def is_zero(s):
            s=s.replace("F","").replace("f","").strip()
            # handle "-0.0" etc
            try:
                return abs(float(s)) < 1e-6
            except:
                return s == "0" or s == "0F"
        is_zero_rot = is_zero(rx) and is_zero(ry) and is_zero(rz)
        # Build texOffs part
        tex_part = f"texOffs({u}, {v})"
        # mirror handling: if mirror true, add .mirror() after texOffs
        if mirror:
            # vanilla pattern is .mirror().texOffs or .texOffs().mirror() – both work but we use .mirror() before addBox
            # Use .texOffs().mirror()
            builder = f"CubeListBuilder.create().{tex_part}.mirror().addBox({box})"
        else:
            builder = f"CubeListBuilder.create().{tex_part}.addBox({box})"
        if is_zero_rot:
            pose = f"PartPose.offset({rp})"
        else:
            pose = f"PartPose.offsetAndRotation({rp}, {rx}, {ry}, {rz})"
        line = f'        root.addOrReplaceChild("{p["name"]}", {builder}, {pose});'
        body_lines.append(line)
    # Build java content
    fields_decl = "\n".join([f"    private final ModelPart {n};" for n in fields])
    fields_assign = "\n".join([f'        this.{n} = root.getChild("{n}");' for n in fields])
    renders = "\n".join([f"        {n}.render(pose, buf, light, overlay, r, g, b, a);" for n in fields])
    body = "\n".join(body_lines)
    java = f"""package mods.defeatedcrow.client.model.model;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class {classname} {{
{fields_decl}

    public {classname}(ModelPart root) {{
{fields_assign}
    }}

    public static LayerDefinition createBodyLayer() {{
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
{body}
        return LayerDefinition.create(mesh, {tex_w}, {tex_h});
    }}

    public void renderToBuffer(com.mojang.blaze3d.vertex.PoseStack pose, com.mojang.blaze3d.vertex.VertexConsumer buf, int light, int overlay, float r, float g, float b, float a) {{
{renders}
    }}
}}
"""
    return java

def main():
    model_files = [f for f in os.listdir(MODEL_DIR) if f.startswith("Model") and f.endswith(".java")]
    # Also ensure we process all master files that may not exist in dev? But we process dev list
    # Add missing from master list?
    try:
        master_list = subprocess.check_output(["git","ls-tree","--name-only","master","src/main/java/mods/defeatedcrow/client/model/model"], cwd=str(ROOT)).decode("utf-8", errors="ignore").splitlines()
        for f in master_list:
            fname = os.path.basename(f)
            if fname not in model_files:
                model_files.append(fname)
    except:
        pass

    model_files = sorted(set(model_files))
    fixed = []
    failed = []
    for fname in model_files:
        master = get_master(fname)
        if not master:
            print(f"skip {fname}: no master")
            continue
        (tex_w, tex_h), parts = parse_master(master)
        if not parts:
            print(f"warn {fname}: no parts parsed")
            failed.append(fname)
            continue
        java = generate_java(fname, tex_w, tex_h, parts)
        out_path = MODEL_DIR / fname
        # Write
        with open(out_path, "w", encoding="utf-8", newline="\n") as fh:
            fh.write(java)
        # Verify rotation count
        nz = sum(1 for p in parts if not (p["rx"].strip() in ("0F","0","0.0F") and p["ry"].strip() in ("0F","0","0.0F") and p["rz"].strip() in ("0F","0","0.0F")))
        print(f"{fname}: {len(parts)} parts, texture {tex_w}x{tex_h}, non-zero rotations {nz}")
        fixed.append(fname)

    print(f"\nFixed {len(fixed)} files, failed {len(failed)}")
    if failed:
        print(failed)

if __name__ == "__main__":
    main()
