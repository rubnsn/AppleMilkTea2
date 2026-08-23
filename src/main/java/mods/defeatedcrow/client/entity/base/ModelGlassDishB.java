package mods.defeatedcrow.client.entity.base;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.LayerDefinition;
public class ModelGlassDishB {
    public ModelGlassDishB(ModelPart r){}
    public static LayerDefinition createBodyLayer(){ return LayerDefinition.create(new net.minecraft.client.model.geom.builders.MeshDefinition(), 64, 64); }
}
