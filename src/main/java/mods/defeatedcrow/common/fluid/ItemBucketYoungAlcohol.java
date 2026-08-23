package mods.defeatedcrow.common.fluid;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.Level;

public class ItemBucketYoungAlcohol extends Item {

    public ItemBucketYoungAlcohol() {
        super();
        this.setContainerItem(Items.bucket);
        setMaxDamage(0);
        setHasSubtypes(true);
    }

    @Override
    public String getUnlocalizedName(ItemStack par1ItemStack) {
        int m = (par1ItemStack.getItemDamage());
        return super.getUnlocalizedName() + "_" + m;
    }

    @Override
    public int getMetadata(int par1) {
        return par1;
    }

    @Override
        public void registerIcons(/*migrated*/Register par1IconRegister) {
        this.itemIcon = par1IconRegister.registerIcon("defeatedcrow:bucket_youngAlcohol");
    }

    @Override
        public void getSubItems(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        for (int i = 0; i < 5; i++) {
            par3List.add(new ItemStack(this, 1, i));
        }
    }

    @Override
    public ItemStack onItemRightClick(ItemStack p_77659_1_, Level p_77659_2_, EntityPlayer p_77659_3_) {
        return p_77659_1_;
    }

}
