package mods.defeatedcrow.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

/**
 * WT-A 1.20.1 mojmap migration for BlockChalcedony.
 * V1 Variant: EnumProperty COLOR 4種 (0 default chalcedony, 1 orange, 2 white, 3 black) + translucent。
 * 1.7.10: color[4] getIcon MathHelper.clamp_int(par2 0-3), registerIcon chalcedony / _orange / _white / _black。
 */
public class BlockChalcedony extends Block {

    public enum ChalcedonyColor implements StringRepresentable {
        DEFAULT("default"), ORANGE("orange"), WHITE("white"), BLACK("black");
        private final String name; ChalcedonyColor(String n){this.name=n;}
        @Override public String getSerializedName(){return name;}
    }
    public static final EnumProperty<ChalcedonyColor> COLOR = EnumProperty.create("color", ChalcedonyColor.class);

    public BlockChalcedony(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(COLOR, ChalcedonyColor.DEFAULT));
    }

    @Override protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> b){ b.add(COLOR); }

    @Override public BlockState getStateForPlacement(BlockPlaceContext ctx){
        ItemStack stack = ctx.getItemInHand();
        if (stack.hasTag() && stack.getTag()!=null && stack.getTag().contains("BlockStateTag")){
            var tag = stack.getTag().getCompound("BlockStateTag");
            if (tag.contains("color")){
                String s = tag.getString("color");
                for (ChalcedonyColor c: ChalcedonyColor.values()) if (c.getSerializedName().equals(s)) return this.defaultBlockState().setValue(COLOR, c);
            }
        }
        return this.defaultBlockState();
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

    // 1.20.1 use: no inventory - decorative, pass through to item
    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        return InteractionResult.PASS;
    }

    @Override
    public void appendHoverText(ItemStack stack, BlockGetter level, java.util.List<Component> tooltip, TooltipFlag flag) {
        // TODO: restore addInformation logic with Component.translatable
        super.appendHoverText(stack, level, tooltip, flag);
    }

    /*
     * Original 1.7.10 source (kept for reference, SJIS -> UTF-8):
     * package mods.defeatedcrow.common.block;
     * 
     * import java.util.List;
     * import java.util.Random;
     * 
     * import net.minecraft.world.level.block.Block;
     * import net.minecraft.world.level.block.BlockBreakable;
     * import net.minecraft.world.level.material.MapColor;
     * import net.minecraft.client.renderer.texture.BlockIconRegister;
     * import net.minecraft.world.item.CreativeModeTab;
     * import net.minecraft.world.item.Item;
     * import net.minecraft.world.item.ItemStack;
     * import net.minecraft.src.*;
     * import net.minecraft.util.BlockTexture;
     * import net.minecraft.util.MathHelper;
     * import net.minecraft.world.IBlockAccess;
     * 
     * public class BlockChalcedony extends BlockBreakable {
     * 
     *     
     *     private BlockTexture[] color;
     * 
     *     public BlockChalcedony(Material material, boolean flag) {
     *         super("defeatedcrow:chalcedony", material, flag);
     *         this.setHardness(1.5F);
     *         this.setResistance(2.0F);
     *         this.setStepSound(Block.soundTypeStone);
     *         this.setLightLevel(0.0F);
     *     }
     * 
     *     @Override
     *     public Item getItemDropped(int metadata, Random rand, int fortune) {
     *         return Item.getItemFromBlock(this);
     *     }
     * 
     *     public int damageDropped(int par1) {
     *         return par1;
     *     }
     * 
     *     
     *     public int getRenderBlockPass() {
     *         return 1;
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
     *     public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
     *         return super.shouldSideBeRendered(par1IBlockAccess, par2, par3, par4, 1 - par5);
     *     }
     * 
     *     public int getMobilityFlag() {
     *         return 0;
     *     }
     * 
     *     
     *     public BlockTexture getBlockTexture(int par1, int par2) {
     *         int i = MathHelper.clamp_int(par2, 0, 3);
     *         return color[i];
     *     }
     * 
     *     @Override
     *     
     *     public void getSubBlocks(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
     *         for (int i = 0; i < 4; ++i) {
     *             par3List.add(new ItemStack(this, 1, i));
     *         }
     *     }
     * 
     *     @Override
     *     
     *     public void registerBlockTextures(BlockIconRegister par1IconRegister) {
     *         this.color = new BlockTexture[4];
     * ... (full original retained in git history: git show HEAD:"src/main/java/mods/defeatedcrow/common/block/BlockChalcedony.java")
     */
}
