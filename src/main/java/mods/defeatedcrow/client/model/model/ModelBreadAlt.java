package mods.defeatedcrow.client.model.model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.LayerDefinition;
public class ModelBreadAlt {
    public ModelBreadAlt(ModelPart root) {}
    public static LayerDefinition createBodyLayer() { return LayerDefinition.create(new net.minecraft.client.model.geom.builders.MeshDefinition(), 64, 64); }
}
