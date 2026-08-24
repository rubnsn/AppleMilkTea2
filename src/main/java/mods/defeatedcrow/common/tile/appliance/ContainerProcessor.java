package mods.defeatedcrow.common.tile.appliance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.entity.player.Inventory;
public class ContainerProcessor extends AbstractContainerMenu {
    public final TileProcessor tile;
    public ContainerProcessor(int id, Inventory inv){ this(id, inv, null); }
    public ContainerProcessor(int id, Inventory inv, TileProcessor t){ super(mods.defeatedcrow.common.registry.ModMenuTypes.PROCESSOR.get(), id); this.tile=t; }
    @Override public boolean stillValid(Player p){ return true; }
    @Override public net.minecraft.world.item.ItemStack quickMoveStack(Player p, int i){ return net.minecraft.world.item.ItemStack.EMPTY; }
}
