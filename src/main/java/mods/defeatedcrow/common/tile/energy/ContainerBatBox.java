package mods.defeatedcrow.common.tile.energy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.entity.player.Inventory;
public class ContainerBatBox extends AbstractContainerMenu {
    public final TileChargerBase tile;
    public ContainerBatBox(int id, Inventory inv){ this(id, inv, null); }
    public ContainerBatBox(int id, Inventory inv, TileChargerBase t){ super(mods.defeatedcrow.common.registry.ModMenuTypes.BAT_BOX.get(), id); this.tile=t; }
    @Override public boolean stillValid(Player p){ return true; }
    @Override public net.minecraft.world.item.ItemStack quickMoveStack(Player p, int i){ return net.minecraft.world.item.ItemStack.EMPTY; }
}
