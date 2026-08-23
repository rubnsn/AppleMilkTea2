package mods.defeatedcrow.common.block.edible;

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
 * WT-A 1.20.1 mojmap migration for EntityItemIceCream.
 * Original 1.7.10 logic preserved as TODO; stub compiles under Forge 47 + mojmap.
 * Properties are supplied by ModBlocks (BlockBehaviour.Properties.of()...).
 * Textures: JSON models under assets/defeatedcrow/models/block/ + blockstates/
 */
public class EntityItemIceCream extends Block {

    public EntityItemIceCream(BlockBehaviour.Properties properties) {
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
     * package mods.defeatedcrow.common.block.edible;
     * 
     * import java.util.ArrayList;
     * import java.util.List;
     * 
     * import net.minecraft.block.Block;
     * import net.minecraft.entity.player.EntityPlayer;
     * import net.minecraft.item.ItemStack;
     * import net.minecraft.potion.Potion;
     * import net.minecraft.potion.PotionEffect;
     * import net.minecraft.world.World;
     * import net.minecraft.world.biome.BiomeGenBase;
     * import net.minecraftforge.common.BiomeDictionary;
     * import net.minecraftforge.common.BiomeDictionary.Type;
     * import mods.defeatedcrow.api.potion.AMTPotionManager;
     * import mods.defeatedcrow.common.AchievementRegister;
     * import mods.defeatedcrow.common.DCsAppleMilk;
     * import mods.defeatedcrow.common.entity.edible.PlaceableIcecream;
     * import mods.defeatedcrow.handler.Util;
     * 
     * public class EntityItemIceCream extends EdibleEntityItemBlock2 {
     * 
     *     private static final String[] type = new String[] { "_milk", "_tea", "_greentea", "_cocoa", "_coffee", "_fruit",
     *         "_lemon", "_lime", "_tomato", "_berry", "_grape", "_mint", "_orange", "_soda" };
     * 
     *     public EntityItemIceCream(Block block) {
     *         super(block, true, true);
     *         setMaxDamage(0);
     *         setHasSubtypes(true);
     *     }
     * 
     *     @Override
     *     public String getUnlocalizedName(ItemStack par1ItemStack) {
     *         int m = (par1ItemStack.getDamageValue());
     *         if (m < 14) return super.getUnlocalizedName() + type[m];
     *         else return super.getUnlocalizedName() + m;
     *     }
     * 
     *     @Override
     *     public int[] hungerOnEaten(int meta) {
     *         return new int[] { 0, 0 };
     *     }
     * 
     *     @Override
     *     public ItemStack onEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
     *         if (!par2World.isRemote) {
     *             if (par1ItemStack.getDamageValue() == 7) { // lime
     *                 EntityItemTeaCup2.clearNegativePotion(par3EntityPlayer);
     *             }
     * 
     *             BiomeGenBase biome = Util.checkCurrentBiome(par2World, par3EntityPlayer);
     *             if (BiomeDictionary.isBiomeOfType(biome, Type.NETHER)) {
     *                 par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.fireResistance.id, 600, 0));
     *                 par3EntityPlayer.triggerAchievement(AchievementRegister.eatIcecream);
     *             } else if (BiomeDictionary.isBiomeOfType(biome, Type.COLD)) {
     *                 par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.hunger.id, 100, 0));
     *             } else if (BiomeDictionary.isBiomeOfType(biome, Type.JUNGLE)
     *                 || BiomeDictionary.isBiomeOfType(biome, Type.HOT)) {
     *                     par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.field_76443_y.id, 1, 2));
     *                 }
     * 
     *             if (par1ItemStack.getDamageValue() == 11)// mint
     *             {
     *                 BlockIceCream.increaseAmplifier(par3EntityPlayer);
     *             }
     *         }
     * 
     *         return super.onEaten(par1ItemStack, par2World, par3EntityPlayer);
     *     }
     * 
     *     @Override
     *     public ArrayList<PotionEffect> effectOnEaten(EntityPlayer player, int meta) {
     *         ArrayList<PotionEffect> ret = new ArrayList<PotionEffect>();
     *         int tick = 1800;
     *         boolean flag = false;
     *         int id[] = { Potion.fireResistance.id, 0 };
     * 
     *         if (meta == 0) {// milk
     *             id[0] = Potion.fireResistance.id;
     *         } else if (meta == 1) {// tea
     * ... (full original retained in git history: git show HEAD:"src/main/java/mods/defeatedcrow/common/block/edible/EntityItemIceCream.java")
     */
}
