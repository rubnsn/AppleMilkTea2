package mods.defeatedcrow.common.block.plants;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.BlockIconRegister;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockTexture;
import net.minecraft.util.MathHelper;

public class ItemTeaSapling extends ItemBlock {

    private static final String[] type = new String[] { "_tea", "_cassis", "_camellia" };

    
    private BlockTexture iconItemType[];

    public ItemTeaSapling(Block block) {
        super(block);
        setMaxDamage(0);
        setHasSubtypes(true);

    }

    @Override
    public String getUnlocalizedName(ItemStack par1ItemStack) {
        int m = (par1ItemStack.getItemDamage());
        if (m < 3) return super.getUnlocalizedName() + type[m];
        else return super.getUnlocalizedName() + m;
    }

    @Override
    public int getMetadata(int par1) {
        return par1;
    }

    @Override
    
    public BlockTexture getBlockTextureFromDamage(int par1) {
        int j = MathHelper.clamp_int(par1, 0, 2);
        return this.field_150939_a.getBlockTexture(0, par1);
    }

    @Override
    
    public void registerIcons(BlockIconRegister par1IconRegister) {
        this.iconItemType = new BlockTexture[3];

        for (int i = 0; i < 3; ++i) {
            this.iconItemType[i] = par1IconRegister.registerIcon("defeatedcrow:sapling" + type[i]);
        }
    }

}
