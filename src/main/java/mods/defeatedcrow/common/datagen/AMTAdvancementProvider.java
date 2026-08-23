package mods.defeatedcrow.common.datagen;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import java.util.concurrent.CompletableFuture;
public class AMTAdvancementProvider implements DataProvider {
    public AMTAdvancementProvider(PackOutput o, CompletableFuture<net.minecraft.core.HolderLookup.Provider> l, net.minecraftforge.common.data.ExistingFileHelper h){}
    @Override public CompletableFuture<?> run(net.minecraft.data.CachedOutput c){ return CompletableFuture.completedFuture(null); }
    @Override public String getName(){ return "AMT Advancements"; }
}
