package mods.defeatedcrow.common.block.energy;

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
 * WT-A 1.20.1 mojmap migration for BlockRedGel.
 * Original 1.7.10 logic preserved as TODO; stub compiles under Forge 47 + mojmap.
 * Properties are supplied by ModBlocks (BlockBehaviour.Properties.of()...).
 * Textures: JSON models under assets/defeatedcrow/models/block/ + blockstates/
 */
public class BlockRedGel extends Block {

    public BlockRedGel(BlockBehaviour.Properties properties) {
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
     * package mods.defeatedcrow.common.block.energy;
     * 
     * import java.util.Random;
     * 
     * import net.minecraft.block.Block;
     * import net.minecraft.block.material.Material;
     * import net.minecraft.client.renderer.texture.BlockIconRegister;
     * import net.minecraft.entity.player.EntityPlayer;
     * import net.minecraft.init.Blocks;
     * import net.minecraft.item.Item;
     * import net.minecraft.item.ItemStack;
     * import net.minecraft.util.AxisAlignedBB;
     * import net.minecraft.util.BlockTexture;
     * import net.minecraft.world.IBlockAccess;
     * import net.minecraft.world.World;
     * import net.minecraftforge.common.util.ForgeDirection;
     * import mods.defeatedcrow.common.AMTLogger;
     * import mods.defeatedcrow.handler.Util;
     * 
     * /*
     *  * 貼るタイプの赤石。
     *  * 赤石と違って、どんな状態でもテクスチャは変わらず、また出力方向も常に対角以外の5面である。
     *  * 右クリックでON・OFFを切り替える。
     *  * 向きはメタデータで格納。0-6:6面分
     *  * /
     * public class BlockRedGel extends Block {
     * 
     *     
     *     private BlockTexture texSide;
     * 
     *     public BlockRedGel() {
     *         super(Material.circuits);
     *         this.setStepSound(Block.soundTypePiston);
     *         this.setTickRandomly(true);
     *     }
     * 
     *     @Override
     *     public Item getItemDropped(int metadata, Random rand, int fortune) {
     *         return Item.getItemFromBlock(this);
     *     }
     * 
     *     @Override
     *     public int damageDropped(int p_149692_1_) {
     *         return 0;
     *     }
     * 
     *     /* 以下はレンダー用のメソッド群 * /
     * 
     *     // 当たり判定はなし
     *     @Override
     *     public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
     *         return null;
     *     }
     * 
     *     // 光透過関係
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
     *     // アイテムアイコン用ボックス
     *     @Override
     *     public void setBlockBoundsForItemRender() {
     *         float f = 0.35F;
     *         float f1 = 0.35F;
     *         float f2 = 0.35F;
     *         this.setBlockBounds(0.5F - f, 0.5F - f1, 0.5F - f2, 0.5F + f, 0.5F + f1, 0.5F + f2);
     *     }
     * 
     *     // アイコンの特殊動作。固形ブロックに張り付いている時、そのブロックの外見に偽装する。
     *     @Override
     *     
     *     public BlockTexture getBlockTexture(IBlockAccess world, int x, int y, int z, int side) {
     *         int meta = world.getBlockMetadata(x, y, z);
     *         int m = meta & 7;
     * ... (full original retained in git history: git show HEAD:"src/main/java/mods/defeatedcrow/common/block/energy/BlockRedGel.java")
     */
}
