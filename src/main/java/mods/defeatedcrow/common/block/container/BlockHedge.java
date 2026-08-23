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
 * WT-A 1.20.1 mojmap migration for BlockHedge.
 * Original 1.7.10 logic preserved as TODO; stub compiles under Forge 47 + mojmap.
 * Properties are supplied by ModBlocks (BlockBehaviour.Properties.of()...).
 * Textures: JSON models under assets/defeatedcrow/models/block/ + blockstates/
 */
public class BlockHedge extends Block {

    public BlockHedge(BlockBehaviour.Properties properties) {
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
     * import net.minecraft.entity.Entity;
     * import net.minecraft.entity.EntityLiving;
     * import net.minecraft.entity.EntityLivingBase;
     * import net.minecraft.entity.monster.IMob;
     * import net.minecraft.world.item.Item;
     * import net.minecraft.world.item.ItemStack;
     * import net.minecraft.util.AxisAlignedBB;
     * import net.minecraft.util.DamageSource;
     * import net.minecraft.util.BlockTexture;
     * import net.minecraft.util.MathHelper;
     * import net.minecraft.world.IBlockAccess;
     * import net.minecraft.world.level.Level;
     * import mods.defeatedcrow.common.DCsAppleMilk;
     * import mods.defeatedcrow.handler.Util;
     * 
     * public class BlockHedge extends Block {
     * 
     *     private static final String[] leaves = new String[] { "_boxwood_n", "_podocarp", "_photinia", "_snakegourd",
     *         "_osmanthus", "_boxwood_g", "_tatibana_n" };
     * 
     *     
     *     private BlockTexture[] baseTex;
     *     
     *     private BlockTexture[] leafTex;
     *     
     *     private BlockTexture tamazusaN;
     *     
     *     private BlockTexture tamazusaC;
     *     
     *     private BlockTexture tatibanaF;
     *     
     *     private BlockTexture tatibanaL;
     *     
     *     private BlockTexture boxW;
     * 
     *     public BlockHedge() {
     *         super(Material.wood);
     *         this.setStepSound(Block.soundTypeGrass);
     *         this.setHardness(0.1F);
     *         this.setTickRandomly(true);
     *     }
     * 
     *     // テクスチャの更新
     *     @Override
     *     public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random) {
     *         if (!par1World.isRemote) {
     *             int meta = par1World.getBlockMetadata(par2, par3, par4);
     *             if (meta == 3 || meta == 6) {
     *                 par1World.markBlockForUpdate(par2, par3, par4);
     *             }
     *         }
     *     }
     * 
     *     @Override
     *     public boolean isOpaqueCube() {
     *         return false;
     *     }
     * 
     *     @Override
     *     public boolean renderAsNormalBlock() {
     *         return false;
     *     }
     * 
     *     @Override
     *     
     *     public BlockTexture getBlockTexture(int par1, int par2) {
     *         int i = par2 & 7;
     *         boolean flag = par2 > 7;
     *         if (i > 7) i = 7;
     *         if (par1 == 0) {
     *             return this.leafTex[i];
     * ... (full original retained in git history: git show HEAD:"src/main/java/mods/defeatedcrow/common/block/container/BlockHedge.java")
     */
}
