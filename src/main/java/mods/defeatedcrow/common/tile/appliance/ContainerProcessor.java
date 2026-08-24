package mods.defeatedcrow.common.tile.appliance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
public class ContainerProcessor extends AbstractContainerMenu {
    public final TileProcessor tile;
    private final ContainerData data;
    public ContainerProcessor(int id, Inventory inv){ this(id, inv, null); }
    public ContainerProcessor(int id, Inventory inv, TileProcessor t){
        super(mods.defeatedcrow.common.registry.ModMenuTypes.PROCESSOR.get(), id);
        this.tile=t;
        if(t!=null){
            this.data = new SimpleContainerData(2);
            this.data.set(0, t.cookTime);
            this.data.set(1, t.chargeAmount);
            addDataSlots(this.data);
        } else {
            this.data = new SimpleContainerData(2);
            addDataSlots(this.data);
        }
        if(t!=null){
            this.addSlot(new Slot(t, 0, 9, 9));
            for(int j=0;j<3;j++){
                for(int k=0;k<3;k++){
                    this.addSlot(new Slot(t, 2 + k + j*3, 33 + k*18, 16 + j*18));
                }
            }
            this.addSlot(new Slot(t, 11, 118, 35){ @Override public boolean mayPlace(ItemStack s){ return false; }});
            this.addSlot(new Slot(t, 12, 145, 35){ @Override public boolean mayPlace(ItemStack s){ return false; }});
        } else {
            for(int i=0;i<12;i++) this.addSlot(new Slot(new net.minecraft.world.SimpleContainer(14), i, 33 + (i%3)*18, 16 + (i/3)*18));
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
    public int getCookTime(){ return data.get(0); }
    public int getCharge(){ return data.get(1); }
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
            int tileSize = 12; // 1 fuel +9 material +2 output =12 but tile has 14, we use 12 for first section
            // actual tile slots in menu: 0,9x material,2 outputs =12 slots
            if(index < tileSize){
                if(!this.moveItemStackTo(stack, tileSize, this.slots.size(), true)) return ItemStack.EMPTY;
            } else {
                if(TileProcessor.isItemFuel(stack)){
                    if(!this.moveItemStackTo(stack, 0, 1, false)) return ItemStack.EMPTY;
                } else {
                    if(!this.moveItemStackTo(stack, 1, 10, false)) return ItemStack.EMPTY;
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
        if(tile!=null){
            data.set(0, tile.cookTime);
            data.set(1, tile.chargeAmount);
        }
    }
}
