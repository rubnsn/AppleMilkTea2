package mods.defeatedcrow.common.block;

import static net.minecraftforge.common.util.ForgeDirection.*;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.BlockIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockTexture;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.handler.Util;

/*
 * meta 0-3 向き情報
 */
public class BlockWoodPanel extends Block {

    
    private BlockTexture[] cover;
    
    private BlockTexture inner;

    public BlockWoodPanel() {
        super(Material.wood);
        this.setStepSound(Block.soundTypeWood);
        this.setHardness(0.2F);
        this.setResistance(1.0F);
        this.setTickRandomly(true);
    }

    public int damageDropped(int par1) {
        return 0;
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public int getRenderType() {
        return DCsAppleMilk.modelWoodPanel;
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4) {
        this.setBlockBoundsBasedOnState(par1World, par2, par3, par4);
        return super.getCollisionBoundingBoxFromPool(par1World, par2, par3, par4);
    }

    
    public AxisAlignedBB getSelectedBoundingBoxFromPool(World par1World, int par2, int par3, int par4) {
        this.setBlockBoundsBasedOnState(par1World, par2, par3, par4);
        return super.getSelectedBoundingBoxFromPool(par1World, par2, par3, par4);
    }

    public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
        this.thisBoundingBox(par1IBlockAccess.getBlockMetadata(par2, par3, par4));
    }

    public void thisBoundingBox(int par1) {
        int meta = par1 & 3;
        int dirMeta = meta + 2;
        ForgeDirection dir = ForgeDirection.getOrientation(dirMeta);
        float f = 0.5F;

        if (dir == NORTH) this.setBlockBounds(0.0F, 0.0F, f, 1.0F, 1.0F, 1.0F);
        else if (dir == SOUTH) this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, f);
        else if (dir == WEST) this.setBlockBounds(0.0F, 0.0F, 0.0F, f, 1.0F, 1.0F);
        else if (dir == EAST) this.setBlockBounds(f, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
    }

    public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side) {
        return side != UP && side != DOWN;
    }

    @Override
    public Item getItemDropped(int metadata, Random rand, int fortune) {
        return Item.getItemFromBlock(this);
    }

    
    public BlockTexture getBlockTexture(int par1, int par2) {
        return par1 > 3 ? inner : cover[par1];
    }

    @Override
    
    public void registerBlockTextures(BlockIconRegister par1IconRegister) {
        this.cover = new BlockTexture[4];

        this.blockIcon = par1IconRegister.registerIcon(Util.getTexturePassNoAlt() + "woodpanel_3");
        this.cover[0] = par1IconRegister.registerIcon(Util.getTexturePassNoAlt() + "woodpanel");
        this.cover[1] = par1IconRegister.registerIcon(Util.getTexturePassNoAlt() + "woodpanel_2");
        this.cover[2] = par1IconRegister.registerIcon(Util.getTexturePassNoAlt() + "woodpanel_2");
        this.cover[3] = par1IconRegister.registerIcon(Util.getTexturePassNoAlt() + "woodpanel_3");
        this.inner = par1IconRegister.registerIcon(Util.getTexturePassNoAlt() + "woodpanel_inner");
    }

}
