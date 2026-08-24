package mods.defeatedcrow.common.tile.appliance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.entity.player.Inventory;
public class ContainerIceMaker extends AbstractContainerMenu {
    public final TileIceMaker tile;
    public ContainerIceMaker(int id, Inventory inv){ this(id, inv, null); }
    public ContainerIceMaker(int id, Inventory inv, TileIceMaker t){ super(mods.defeatedcrow.common.registry.ModMenuTypes.ICE_MAKER.get(), id); this.tile=t; }
    @Override public boolean stillValid(Player p){ return true; }
    @Override public net.minecraft.world.item.ItemStack quickMoveStack(Player p, int i){ return net.minecraft.world.item.ItemStack.EMPTY; }
}
