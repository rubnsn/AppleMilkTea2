package mods.defeatedcrow.common.item.magic;

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

/**
 * WT-A 1.20.1 mojmap migration for ItemFossilScale.
 * Former 1.7.10 IItem with subtypes/meta -> NBT or split RegistryObject (see ModItems).
 * Textures: JSON models under assets/defeatedcrow/models/item/
 */
public class ItemFossilScale extends Item {
    public ItemFossilScale(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, Level level, java.util.List<Component> tooltip, TooltipFlag flag) {
        // TODO: restore addInformation logic
        super.appendHoverText(stack, level, tooltip, flag);
    }

    @Override
    public net.minecraft.world.InteractionResult useOn(net.minecraft.world.item.context.UseOnContext ctx) {
        // TODO: restore onItemUse logic with BlockPos/Level/Player
        return super.useOn(ctx);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        return super.use(level, player, hand);
    }

    /*
     * Original 1.7.10 source (truncated, full in git history):
     * package mods.defeatedcrow.common.item.magic;
     * 
     * import java.util.List;
     * 
     * import net.minecraft.client.renderer.texture.BlockIconRegister;
     * import net.minecraft.entity.EntityLivingBase;
     * import net.minecraft.entity.passive.EntityHorse;
     * import net.minecraft.entity.passive.EntityTameable;
     * import net.minecraft.entity.passive.EntityVillager;
     * import net.minecraft.entity.player.EntityPlayer;
     * import net.minecraft.item.EnumAction;
     * import net.minecraft.item.EnumRarity;
     * import net.minecraft.item.Item;
     * import net.minecraft.item.ItemStack;
     * import net.minecraft.util.AxisAlignedBB;
     * import net.minecraft.util.BlockTexture;
     * import net.minecraft.util.MathHelper;
     * import net.minecraft.world.World;
     * import mods.defeatedcrow.common.AMTLogger;
     * import mods.defeatedcrow.common.entity.EntityAnchorMissile;
     * 
     * public class ItemFossilScale extends Item {
     * 
     *     public ItemFossilScale() {
     *         super();
     *         this.setMaxStackSize(1);
     *         this.setMaxDamage(16);
     *         this.setNoRepair();
     *     }
     * 
     *     @Override
     *     
     *     public void registerIcons(BlockIconRegister par1IconRegister) {
     * 
     *         this.itemIcon = par1IconRegister.registerIcon("defeatedcrow:purple_scale");
     *     }
     * 
     *     // 文字色
     *     public EnumRarity getRarity(ItemStack par1ItemStack) {
     *         return EnumRarity.uncommon;
     *     }
     * 
     *     /*
     *      * 右クリック使用をやめた時に呼ばれるメソッド。右クリックを継続して押していた時間をもとに、エンティティを発射する処理を行う。
     *      * /
     *     public void onPlayerStoppedUsing(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer,
     *         int par4) {
     *         float yaw = par3EntityPlayer.rotationYaw;
     *         float pitch = par3EntityPlayer.rotationPitch;
     *         double dx = -(double) (MathHelper.sin(yaw / 180.0F * (float) Math.PI)) * 30.0D;
     *         double dz = (double) (MathHelper.cos(yaw / 180.0F * (float) Math.PI)) * 30.0D;
     *         double dy = -(double) (MathHelper.sin(pitch / 180.0F * (float) Math.PI)) * 30.0D;
     * 
     *         double minX = par3EntityPlayer.posX + Math.min(-1, dx);
     *         double minY = par3EntityPlayer.posY + Math.min(-1, dy);
     *         double minZ = par3EntityPlayer.posZ + Math.min(-1, dz);
     *         double maxX = par3EntityPlayer.posX + Math.max(1, dx);
     *         double maxY = par3EntityPlayer.posY + Math.max(1, dy);
     *         double maxZ = par3EntityPlayer.posZ + Math.max(1, dz);
     * 
     */
}
