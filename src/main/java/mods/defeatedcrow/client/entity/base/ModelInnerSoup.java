package mods.defeatedcrow.client.entity.base;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.LayerDefinition;
public class ModelInnerSoup {
    public ModelInnerSoup(ModelPart r){}
    public static LayerDefinition createBodyLayer(){ return LayerDefinition.create(new net.minecraft.client.model.geom.builders.MeshDefinition(), 64, 64); }
}
