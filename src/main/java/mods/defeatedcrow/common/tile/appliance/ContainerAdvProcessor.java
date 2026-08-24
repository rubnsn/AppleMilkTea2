package mods.defeatedcrow.common.tile.appliance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.entity.player.Inventory;
public class ContainerAdvProcessor extends AbstractContainerMenu {
    public final TileAdvProcessor tile;
    public ContainerAdvProcessor(int id, Inventory inv){ this(id, inv, null); }
    public ContainerAdvProcessor(int id, Inventory inv, TileAdvProcessor t){ super(mods.defeatedcrow.common.registry.ModMenuTypes.ADV_PROCESSOR.get(), id); this.tile=t; }
    @Override public boolean stillValid(Player p){ return true; }
    @Override public net.minecraft.world.item.ItemStack quickMoveStack(Player p, int i){ return net.minecraft.world.item.ItemStack.EMPTY; }
}
