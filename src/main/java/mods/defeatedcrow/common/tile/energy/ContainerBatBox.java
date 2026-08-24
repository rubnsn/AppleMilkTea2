package mods.defeatedcrow.common.tile.energy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
public class ContainerBatBox extends AbstractContainerMenu {
    public final TileChargerBase tile;
    private final ContainerData data;
    public ContainerBatBox(int id, Inventory inv){ this(id, inv, null); }
    public ContainerBatBox(int id, Inventory inv, TileChargerBase t){
        super(mods.defeatedcrow.common.registry.ModMenuTypes.BAT_BOX.get(), id);
        this.tile=t;
        if(t!=null){
            this.data = new SimpleContainerData(1);
            this.data.set(0, t.getChargeAmount());
            addDataSlots(this.data);
        } else {
            this.data = new SimpleContainerData(1);
            addDataSlots(this.data);
        }
        if(t!=null){
            this.addSlot(new Slot(t, 0, 9, 9));
            this.addSlot(new Slot(t, 1, 9, 55){ @Override public boolean mayPlace(ItemStack s){ return false; }});
            for(int j=0;j<2;j++){
                for(int k=0;k<4;k++){
                    this.addSlot(new Slot(t, 2 + k + j*4, 53 + k*18, 30 + j*18));
                }
            }
        } else {
            for(int i=0;i<10;i++) this.addSlot(new Slot(new net.minecraft.world.SimpleContainer(10), i, 53 + (i%4)*18, 30 + (i/4)*18));
        }
        for(int i=0;i<3;i++){
            for(int j=0;j<9;j++){
                this.addSlot(new Slot(inv, j + i*9 + 9, 8 + j*18, 84 + i*18));
            }
        }
        for(int i=0;i<9;i++){
            this.addSlot(new Slot(inv, i, 8 + i*18, 142));
        }
    }
    public int getCharge(){ return data.get(0); }
    @Override public boolean stillValid(Player p){
        if(tile==null) return false;
        return tile.stillValid(p) && p.distanceToSqr(tile.getBlockPos().getX()+0.5, tile.getBlockPos().getY()+0.5, tile.getBlockPos().getZ()+0.5) < 64;
    }
    @Override public ItemStack quickMoveStack(Player player, int index){
        ItemStack ret = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if(slot!=null && slot.hasItem()){
            ItemStack stack = slot.getItem();
            ret = stack.copy();
            int tileSize = 10;
            if(index < tileSize){
                if(!this.moveItemStackTo(stack, tileSize, this.slots.size(), true)) return ItemStack.EMPTY;
            } else {
                if(TileChargerBase.isItemFuel(stack)){
                    if(!this.moveItemStackTo(stack, 0, 1, false)) return ItemStack.EMPTY;
                } else if(stack.getCapability(net.minecraftforge.common.capabilities.ForgeCapabilities.ENERGY).isPresent()){
                    if(!this.moveItemStackTo(stack, 2, 10, false)) return ItemStack.EMPTY;
                } else {
                    return ItemStack.EMPTY;
                }
            }
            if(stack.isEmpty()) slot.set(ItemStack.EMPTY);
            else slot.setChanged();
            if(stack.getCount()==ret.getCount()) return ItemStack.EMPTY;
            slot.onTake(player, stack);
        }
        return ret;
    }
    @Override public void broadcastChanges(){
        super.broadcastChanges();
        if(tile!=null) data.set(0, tile.getChargeAmount());
    }
}
