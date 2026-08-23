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
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

/**
 * WT-A 1.20.1 mojmap migration for BlockVegiBag.
 * Original 1.7.10 logic preserved as TODO; stub compiles under Forge 47 + mojmap.
 * Properties are supplied by ModBlocks (BlockBehaviour.Properties.of()...).
 * Former BlockContainer/TileEntity logic: see Tile* migration (WT-B).
 * Textures: JSON models under assets/defeatedcrow/models/block/ + blockstates/
 */
public class BlockVegiBag extends Block implements EntityBlock {

    public BlockVegiBag(BlockBehaviour.Properties properties) {
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

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        // TODO: return new Tile* (pos, state) - requires WT-B BlockEntityType registration
        return null;
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
     * 
     * import net.minecraft.block.Block;
     * import net.minecraft.block.Block;
     * import net.minecraft.block.material.Material;
     * import net.minecraft.client.renderer.texture.BlockIconRegister;
     * import net.minecraft.creativetab.CreativeTabs;
     * import net.minecraft.entity.EntityLivingBase;
     * import net.minecraft.item.Item;
     * import net.minecraft.item.ItemStack;
     * import net.minecraft.tileentity.TileEntity;
     * import net.minecraft.util.AxisAlignedBB;
     * import net.minecraft.util.BlockTexture;
     * import net.minecraft.util.MathHelper;
     * import net.minecraft.world.IBlockAccess;
     * import net.minecraft.world.World;
     * import mods.defeatedcrow.common.tile.TileVegiBag;
     * import mods.defeatedcrow.handler.Util;
     * 
     * public class BlockVegiBag extends Block {
     * 
     *     private static final String[] bagVegi = new String[] { "_leaves", "_potato", "_carrot", "_pumpkin", "_seed",
     *         "_reed", "_cactus", "_cocoa", "_wart", "_sugar" };
     *     public static final String[] bagTexType = new String[] { "LeavesBag_T", "PotatoBag_T", "CarrotBag_T",
     *         "PumpkinBag_T", "SeedBag_T", "ReedBag_T", "CactusBag_T", "CocoaBag_T", "WartBag_T", "SugarBag_T" };
     * 
     *     
     *     private BlockTexture[] wheatBagTop;
     *     
     *     private BlockTexture wheatBagSide;
     * 
     *     public BlockVegiBag() {
     *         super(Material.wood);
     *         this.setStepSound(Block.soundTypeWood);
     *         this.setHardness(0.1F);
     *         this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
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
     *         int i = par2;
     *         if (i > 9) i = 9;
     *         if (par1 == 1) {
     *             return this.wheatBagTop[i];
     *         } else if (par1 == 0) {
     *             return this.blockIcon;
     *         } else {
     *             return this.wheatBagSide;
     *         }
     * 
     *     }
     * 
     *     @Override
     *     public int damageDropped(int par1) {
     *         return par1;
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
     * ... (full original retained in git history: git show HEAD:"src/main/java/mods/defeatedcrow/common/block/container/BlockVegiBag.java")
     */
}
