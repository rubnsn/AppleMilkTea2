package mods.defeatedcrow.common.item.magic;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import java.util.function.Consumer;

/**
 * WT-A 1.20.1 mojmap migration for ItemDebugArm.
 * Former 1.7.10 IItem with subtypes/meta -> NBT or split RegistryObject (see ModItems).
 * Textures: JSON models under assets/defeatedcrow/models/item/
 */
public class ItemDebugArm extends Item {
    public ItemDebugArm(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, Level level, java.util.List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, level, tooltip, flag);
    }

    @Override
    public net.minecraft.world.InteractionResult useOn(net.minecraft.world.item.context.UseOnContext ctx) {
        return super.useOn(ctx);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        return super.use(level, player, hand);
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            private mods.defeatedcrow.client.item.BEWLR_EightEyesArm renderer;
            @Override
            public net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer getCustomRenderer() {
                if (renderer == null) {
                    renderer = new mods.defeatedcrow.client.item.BEWLR_EightEyesArm(Minecraft.getInstance(), Minecraft.getInstance().getEntityModels());
                }
                return renderer;
            }
        });
    }

    /*
     * Original 1.7.10 source (truncated, full in git history):
     * package mods.defeatedcrow.common.item.magic;
     * 
     * import java.util.List;
     * 
     * import net.minecraft.world.level.block.Block;
     * import net.minecraft.client.renderer.texture.BlockIconRegister;
     * import net.minecraft.entity.Entity;
     * import net.minecraft.entity.EntityLivingBase;
     * import net.minecraft.world.entity.player.Player;
     * import net/minecraft/init/Blocks;
     * import net.minecraft.item.EnumAction;
     * import net.minecraft.item.EnumRarity;
     * import net.minecraft.world.item.Item;
     * import net.minecraft.world.item.ItemDye;
     * import net.minecraft.world.item.ItemStack;
     * import net.minecraft.nbt.CompoundTag;
     * import net.minecraft.world.level.block.entity.BlockEntity;
     * import net.minecraft.util.MathHelper;
     * import net.minecraft.world.level.Level;
     * import net.minecraftforge.common.util.ForgeDirection;
     * import mods.defeatedcrow.api.energy.IBattery;
     * import mods.defeatedcrow.common.DCsAppleMilk;
     * import mods.defeatedcrow.common.tile.TileBrewingBarrel;
     * import mods.defeatedcrow.common.tile.appliance.MachineBase;
     * import mods.defeatedcrow.common.tile.energy.TileChargerDevice;
     * import mods.defeatedcrow.plugin.cofh.RFDeviceHandler;
     * 
     * public class ItemDebugArm extends Item implements IBattery {
     * 
     *     public ItemDebugArm() {
     *         super();
     *         this.setMaxStackSize(1);
     *         this.setMaxDamage(0);
     *         this.setNoRepair();
     * 
     *         if (DCsAppleMilk.debugMode) {
     *             this.setCreativeTab(DCsAppleMilk.applemilkMagic);
     *         }
     *     }
     * 
     *     // 右クリック効果
     *     @Override
     *     public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4,
     *         int par5, int par6, int par7, float par8, float par9, float par10) {
     *         Block block = par3World.getBlock(par4, par5, par6);
     *         TileEntity tile = par3World.getTileEntity(par4, par5, par6);
     *         int meta = par3World.getBlockMetadata(par4, par5, par6);
     * 
     *         if (par2EntityPlayer == null) return false;
     *         boolean se = false;
     * 
     *         // charge
     *         if (tile instanceof MachineBase) {
     *             MachineBase machine = (MachineBase) tile;
     *             if (!machine.isFullCharged()) {
     *                 int max = machine.getMaxChargeAmount();
     *                 int ret = machine.getChargeAmount() + 800;
     *                 ret = MathHelper.clamp_int(ret, 0, max);
     *                 machine.setChargeAmount(ret);
     *                 se = true;
     */
}