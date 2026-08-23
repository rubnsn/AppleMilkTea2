package mods.defeatedcrow.common.block.energy;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.BlockIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.BlockTexture;
import mods.defeatedcrow.common.DCsAppleMilk;

public class BlockYuzuBat extends Block {

    
    private BlockTexture texTop;
    
    private BlockTexture texSide;

    public BlockYuzuBat() {
        super(Material.ground);
        this.setStepSound(Block.soundTypePiston);
        this.setHardness(1.0F);
        this.setResistance(2.0F);
    }

    @Override
    public Item getItemDropped(int metadata, Random rand, int fortune) {
        return Item.getItemFromBlock(this);
    }

    
    public BlockTexture getBlockTexture(int par1, int par2) {
        return par1 == 1 ? this.texTop : this.texSide;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public int getRenderType() {
        return DCsAppleMilk.modelYuzuBat;
    }

    @Override
    
    public void registerBlockTextures(BlockIconRegister par1BlockIconRegister) {
        this.blockIcon = Blocks.iron_bars.getBlockTextureFromSide(2);
        this.texTop = Blocks.iron_bars.getBlockTextureFromSide(2);
        this.texSide = par1BlockIconRegister.registerIcon("defeatedcrow:container_yuzubat_S");

    }

}
