package mods.defeatedcrow.common.block.plants;

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
 * WT-A 1.20.1 mojmap migration for BlockYuzuLeaves.
 * Original 1.7.10 logic preserved as TODO; stub compiles under Forge 47 + mojmap.
 * Properties are supplied by ModBlocks (BlockBehaviour.Properties.of()...).
 * Textures: JSON models under assets/defeatedcrow/models/block/ + blockstates/
 */
public class BlockYuzuLeaves extends Block {

    public BlockYuzuLeaves(BlockBehaviour.Properties properties) {
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
     * package mods.defeatedcrow.common.block.plants;
     * 
     * import java.util.ArrayList;
     * import java.util.Calendar;
     * import java.util.List;
     * import java.util.Random;
     * 
     * import net.minecraft.block.Block;
     * import net.minecraft.block.BlockLeavesBase;
     * import net.minecraft.block.material.Material;
     * import net.minecraft.client.renderer.texture.BlockIconRegister;
     * import net.minecraft.creativetab.CreativeTabs;
     * import net.minecraft.entity.item.EntityItem;
     * import net.minecraft.entity.player.EntityPlayer;
     * import net.minecraft.entity.player.InventoryPlayer;
     * import net.minecraft.inventory.IInventory;
     * import net.minecraft.item.Item;
     * import net.minecraft.item.ItemStack;
     * import net.minecraft.util.BlockTexture;
     * import net.minecraft.world.IBlockAccess;
     * import net.minecraft.world.World;
     * import net.minecraftforge.common.IShearable;
     * import net.minecraftforge.common.MinecraftForge;
     * import mods.defeatedcrow.api.plants.IRightClickHarvestable;
     * import mods.defeatedcrow.api.plants.PlantsClickEvent;
     * import mods.defeatedcrow.common.AchievementRegister;
     * import mods.defeatedcrow.common.DCsAppleMilk;
     * import mods.defeatedcrow.handler.Util;
     * 
     * /**
     *  * 柚子の葉ブロック。 <br>
     *  * メタデータ&3が、0:採取後の葉、1:自然生成時の葉、2:花つき、3:実つき。 <br>
     *  * 右クリックで実を採取したあと、メタデータが0になることで再採集までのインターバルを作る。
     *  * /
     * public class BlockYuzuLeaves extends BlockLeavesBase implements IShearable, IRightClickHarvestable {
     * 
     *     int[] around;
     *     
     *     protected int graphicsLevel;
     *     protected BlockTexture[] leavesIcon;
     *     private static final String[] type = new String[] { "yuzu" };
     * 
     *     public BlockYuzuLeaves() {
     *         super(Material.leaves, false);
     *         this.setTickRandomly(true);
     *         this.setCreativeTab(DCsAppleMilk.applemilk);
     *         this.setHardness(0.1F);
     *         this.setLightOpacity(1);
     *         this.setStepSound(soundTypeGrass);
     * 
     *         this.field_150121_P = true;
     *     }
     * 
     *     @Override
     *     
     *     public void getSubBlocks(Item p_149666_1_, CreativeTabs p_149666_2_, List p_149666_3_) {
     *         p_149666_3_.add(new ItemStack(p_149666_1_, 1, 3));
     *     }
     * 
     *     @Override
     *     
     *     public void registerBlockTextures(BlockIconRegister par1IconRegister) {
     *         this.blockIcon = par1IconRegister.registerIcon(Util.getTexturePassNoAlt() + "leaves_yuzu_0");
     *         this.leavesIcon = new BlockTexture[3];
     * 
     *         for (int i = 0; i < 3; ++i) {
     *             if (i == 2 && DCsAppleMilk.CAL.get(Calendar.MONTH) == 3 && DCsAppleMilk.CAL.get(Calendar.DATE) == 1) {
     *                 this.leavesIcon[i] = par1IconRegister
     *                     .registerIcon(Util.getTexturePassNoAlt() + "leaves_yuzu_" + i + "_4_1");
     *             } else {
     *                 this.leavesIcon[i] = par1IconRegister.registerIcon(Util.getTexturePassNoAlt() + "leaves_yuzu_" + i);
     *             }
     *         }
     * 
     *     }
     * 
     *     @Override
     *     public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer,
     *         int par6, float par7, float par8, float par9) {
     *         ItemStack itemstack = par5EntityPlayer.inventory.getCurrentItem();
     * ... (full original retained in git history: git show HEAD:"src/main/java/mods/defeatedcrow/common/block/plants/BlockYuzuLeaves.java")
     */
}
