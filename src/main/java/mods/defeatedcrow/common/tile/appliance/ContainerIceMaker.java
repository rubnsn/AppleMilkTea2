package mods.defeatedcrow.common.tile.appliance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
public class ContainerIceMaker extends AbstractContainerMenu {
    public final TileIceMaker tile;
    private final ContainerData data;
    public ContainerIceMaker(int id, Inventory inv){ this(id, inv, null); }
    public ContainerIceMaker(int id, Inventory inv, TileIceMaker t){
        super(mods.defeatedcrow.common.registry.ModMenuTypes.ICE_MAKER.get(), id);
        this.tile=t;
        // data sync for cookTime and chargeAmount
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
            // tile slots: 0 input 56,17 ; 1 fuel 56,53 ; 2 output 112,35 ; 3 container 140,35
            this.addSlot(new Slot(t, 0, 56, 17));
            this.addSlot(new Slot(t, 1, 56, 53));
            this.addSlot(new Slot(t, 2, 112, 35) { @Override public boolean mayPlace(ItemStack s){ return false; } });
            this.addSlot(new Slot(t, 3, 140, 35) { @Override public boolean mayPlace(ItemStack s){ return false; } });
        } else {
            // client dummy slots
            for(int i=0;i<4;i++) this.addSlot(new Slot(new net.minecraft.world.SimpleContainer(4), i, 56 + (i%2)*56, 17 + (i/2)*18));
        }
        // player inventory 3 rows
        for(int i=0;i<3;i++){
            for(int j=0;j<9;j++){
                this.addSlot(new Slot(inv, j + i*9 + 9, 8 + j*18, 84 + i*18));
            }
        }
        for(int i=0;i<9;i++){
            this.addSlot(new Slot(inv, i, 8 + i*18, 142));
        }
    }
    @Override public boolean stillValid(Player p){
        if(tile==null) return false;
        return tile.stillValid(p) && p.distanceToSqr(tile.getBlockPos().getX()+0.5, tile.getBlockPos().getY()+0.5, tile.getBlockPos().getZ()+0.5) < 64;
    }
    public int getCookTime(){ return data.get(0); }
    public int getCharge(){ return data.get(1); }
    @Override public ItemStack quickMoveStack(Player player, int index){
        ItemStack ret = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if(slot!=null && slot.hasItem()){
            ItemStack stack = slot.getItem();
            ret = stack.copy();
            int tileSize = 4;
            if(index < tileSize){
                if(!this.moveItemStackTo(stack, tileSize, this.slots.size(), true)) return ItemStack.EMPTY;
            } else {
                if(TileIceMaker.isItemFuel(stack)){
                    if(!this.moveItemStackTo(stack, 1, 2, false)){
                        if(!this.moveItemStackTo(stack, 0, 1, false)) return ItemStack.EMPTY;
                    }
                } else {
                    if(!this.moveItemStackTo(stack, 0, 1, false)) return ItemStack.EMPTY;
                }
            }
            if(stack.isEmpty()) slot.set(ItemStack.EMPTY);
            else slot.setChanged();
            if(stack.getCount() == ret.getCount()) return ItemStack.EMPTY;
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
