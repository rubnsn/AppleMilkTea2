package mods.defeatedcrow.event;

import java.util.ArrayList;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.Level;
import net.minecraftforge.event.entity.living.LivingDropsEvent;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import mods.defeatedcrow.common.AMTLogger;
import mods.defeatedcrow.common.DCsAppleMilk;

public class EntityMoreDropEvent {

    @SubscribeEvent
    public void EntityDropEvent(LivingDropsEvent event) {
        LivingEntity entity = event.entityLiving;
        DamageSource thisSource = event.source;
        ArrayList<ItemEntity> items = event.drops;
        ItemStack hold = event.entityLiving.getHeldItem();

        // 以下、死んだモブの位置情報
        Level world = entity.level;
        double posX = entity.posX;
        double posY = entity.posY;
        double posZ = entity.posZ;

        if (!(entity instanceof Player) && thisSource instanceof EntityDamageSource) {
            EntityDamageSource entityDamage = (EntityDamageSource) thisSource;
            Entity destroyer = entityDamage.getEntity();

            /**
             * EntityPlayerによる攻撃の時に判定する。
             * 間接攻撃でも大丈夫だと思う
             */
            if (destroyer instanceof Player) {
                Item radenID = DCsAppleMilk.princessClam;
                Player player = (Player) destroyer;

                int flowerCount = 0;
                int butterflyCount = 0;

                for (int i = 0; i < player.inventory.mainInventory.length; i++) {
                    ItemStack item = player.inventory.getStackInSlot(i);
                    if (item != null && item.getItem() != null && item.getItem() == radenID) {
                        if (item.getItemDamage() == 1) {
                            flowerCount++;
                        } else if (item.getItemDamage() == 2) {
                            butterflyCount++;
                        }
                    }
                }

                if (flowerCount > 0) {
                    for (ItemEntity get : items) {
                        if (get == null || get.getEntityItem() == null) continue;
                        if (hold != null && hold.getItem() != null && hold.isItemEqual(get.getEntityItem())) continue;

                        if (get.getEntityItem()
                            .getItem()
                            .isDamageable() && !DCsAppleMilk.debugMode) {
                            int dam = get.getEntityItem()
                                .getItemDamage();
                            dam -= world.rand.nextInt(flowerCount) * 20;
                            if (dam < 0) dam = 0;
                            get.getEntityItem()
                                .setItemDamage(dam);
                        } else {
                            int count = 1 + world.rand.nextInt(flowerCount);
                            get.getEntityItem().stackSize += count;
                            AMTLogger.debugInfo(
                                "increase drops :" + get.getEntityItem()
                                    .getDisplayName() + " +" + count);
                        }
                    }
                }

                if (butterflyCount > 0) {
                    int count = 5 * butterflyCount;
                    int exp = 1 + world.rand.nextInt(count);
                    AMTLogger.debugInfo("raden (exp)" + exp);
                    world.spawnEntityInWorld(new ExperienceOrb(world, posX, posY, posZ, exp));
                }
            }
        }
    }

}
