package mods.defeatedcrow.common.block.container;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

/**
 * WT-A 1.20.1 mojmap migration for BlockWoodBox.
 * Original 1.7.10 logic preserved as TODO; stub compiles under Forge 47 + mojmap.
 * Properties are supplied by ModBlocks (BlockBehaviour.Properties.of()...).
 * Textures: JSON models under assets/defeatedcrow/models/block/ + blockstates/
 */
public class BlockWoodBox extends Block {

    public BlockWoodBox(BlockBehaviour.Properties properties) {
        super(properties);
    }

    // 1.20.1: VoxelShape replaces AxisAlignedBB / setBlockBounds / getSelectedBoundingBox
    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext ctx) {
        return Shapes.block(); // TODO: restore original bounds via Block.box() per meta/state
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext ctx) {
        return getShape(state, level, pos, ctx);
    }

    // 1.7.10 onBlockActivated -> 1.20.1 use (BlockPos + BlockHitResult)
    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        // TODO: restore original onBlockActivated logic
        // Original used: world.getBlockMetadata(x,y,z), player.inventory, MinecraftForge.EVENT_BUS.post(AMTBlockRightClickEvent)
        // Migration: use state, level.getBlockEntity(pos), player.getItemInHand(hand), Component
        return InteractionResult.PASS;
    }

    @Override
    public void appendHoverText(ItemStack stack, BlockGetter level, java.util.List<Component> tooltip, TooltipFlag flag) {
        // TODO: restore addInformation logic with Component.translatable
        super.appendHoverText(stack, level, tooltip, flag);
    }

    /*
     * Original 1.7.10 source (kept for reference, SJIS -> UTF-8):
     * package mods.defeatedcrow.common.block.container;
     * 
     * import java.util.List;
     * import java.util.Random;
     * 
     * import net.minecraft.world.level.block.Block;
     * import net.minecraft.world.level.material.MapColor;
     * import net.minecraft.client.renderer.texture.BlockIconRegister;
     * import net.minecraft.world.item.CreativeModeTab;
     * import net.minecraft.world.item.Item;
     * import net.minecraft.world.item.ItemStack;
     * import net.minecraft.util.BlockTexture;
     * import mods.defeatedcrow.handler.Util;
     * 
     * public class BlockWoodBox extends Block {
     * 
     *     private static final String[] boxType = new String[] { "_oak", "_spruse", "_birch", "_jungle", "_rubber", "_great",
     *         "_silver", "_force", "_sakura", "_momizi", "_JPcedar", "_darkoak", "_acacia" };
     * 
     *     
     *     private BlockTexture[] boxTex;
     *     
     *     private BlockTexture[] boxSideTex;
     * 
     *     public BlockWoodBox() {
     *         super(Material.wood);
     *         this.setStepSound(Block.soundTypeWood);
     *         this.setHardness(0.1F);
     *     }
     * 
     *     @Override
     *     public int damageDropped(int par1) {
     *         return par1;
     *     }
     * 
     *     @Override
     *     
     *     public BlockTexture getBlockTexture(int par1, int par2) {
     *         int i = par2;
     *         if (i > 12) i = 12;
     *         if (par1 == 4 || par1 == 5) {
     *             return this.boxSideTex[i];
     *         } else {
     *             return this.boxTex[i];
     *         }
     *     }
     * 
     *     @Override
     *     
     *     public void getSubBlocks(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
     *         par3List.add(new ItemStack(par1, 1, 0));
     *         par3List.add(new ItemStack(par1, 1, 1));
     *         par3List.add(new ItemStack(par1, 1, 2));
     *         par3List.add(new ItemStack(par1, 1, 3));
     *         par3List.add(new ItemStack(par1, 1, 4));
     *         par3List.add(new ItemStack(par1, 1, 5));
     *         par3List.add(new ItemStack(par1, 1, 6));
     *         par3List.add(new ItemStack(par1, 1, 7));
     *         par3List.add(new ItemStack(par1, 1, 8));
     *         par3List.add(new ItemStack(par1, 1, 9));
     *         par3List.add(new ItemStack(par1, 1, 10));
     *         par3List.add(new ItemStack(par1, 1, 11));
     *         par3List.add(new ItemStack(par1, 1, 12));
     *     }
     * 
     *     @Override
     *     public Item getItemDropped(int metadata, Random rand, int fortune) {
     *         return Item.getItemFromBlock(this);
     *     }
     * 
     *     @Override
     *     
     *     public void registerBlockTextures(BlockIconRegister par1IconRegister) {
     *         this.boxTex = new BlockTexture[13];
     *         this.boxSideTex = new BlockTexture[13];
     * 
     *         for (int i = 0; i < 13; ++i) {
     *             this.boxTex[i] = par1IconRegister.registerIcon(Util.getTexturePassNoAlt() + "WoodBox" + boxType[i]);
     *             this.boxSideTex[i] = par1IconRegister.registerIcon(Util.getTexturePassNoAlt() + "WoodBoxside" + boxType[i]);
     *         }
     * ... (full original retained in git history: git show HEAD:"src/main/java/mods/defeatedcrow/common/block/container/BlockWoodBox.java")
     */
}
