package mods.defeatedcrow.event;

import java.util.ArrayList;
import java.util.Iterator;

import net.minecraft.world.level.material.MapColor;
// Material removed in 1.20.1 - use BlockState properties
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.util.Mth;
import net.minecraft.core.Direction;
import net.minecraftforge.event.entity.living.LivingEvent;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import mods.defeatedcrow.api.potion.PotionImmunityBase;
import mods.defeatedcrow.api.potion.PotionLivingBase;
import mods.defeatedcrow.common.AMTLogger;
import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.common.item.magic.ItemPrincessClam;
import mods.defeatedcrow.handler.Coord;
import mods.defeatedcrow.handler.CoordListRegister;
import mods.defeatedcrow.network.DCsNetworkHandler;
import mods.defeatedcrow.network.MessageCharmWarp;

public class DCsLivingEvent {

    private boolean keyDown = false;

    @SubscribeEvent
    public void onLivingUpdate(LivingEvent.LivingUpdateEvent event) {
        Entity entity = event.entity;

        // やっつけ仕事なので、プレイヤーを常時監視して、抵抗力ポーションが働いているかを見張っている
        if ((entity instanceof Player)) {
            Player player = (Player) event.entity;

            ArrayList<PotionEffect> immunities = new ArrayList<PotionEffect>();

            if (player != null) {
                // PotionEffectのリスト
                Iterator iterator = player.getActivePotionEffects()
                    .iterator();

                while (iterator.hasNext()) {
                    PotionEffect effect = (PotionEffect) iterator.next();

                    int id = effect.getPotionID();
                    Potion potion = Potion.potionTypes[id];

                    if (potion != null && potion instanceof PotionImmunityBase) {
                        immunities.add(effect);
                    }
                }

                for (PotionEffect eff : immunities) {
                    Potion potion = Potion.potionTypes[eff.getPotionID()];
                    int amp = eff.getAmplifier();
                    int dur = eff.getDuration();

                    if (potion != null && potion instanceof PotionImmunityBase) {
                        PotionImmunityBase immunity = (PotionImmunityBase) potion;

                        if (immunity.preventPotion(amp, immunity.id, player)) {
                            AMTLogger.debugInfo("Succeeded to prevent bad status by PotionImmunity effect.");
                        }
                    }
                }

                if (player.level.isClientSide && !keyDown) {
                    if (DCsAppleMilk.proxy.isWarpKeyDown()) {
                        keyDown = true;
                        ItemStack charm = null;
                        boolean warp = false;
                        int limit = -1;
                        int x = 0;
                        int y = 0;
                        int z = 0;
                        int dim = 0;

                        for (int i = 0; i < 9; i++) {
                            ItemStack check = player.inventory.getStackInSlot(i);
                            if (check != null && check.getItem() != null
                                && check.getItem() == DCsAppleMilk.princessClam) {
                                charm = check;
                                break;
                            }
                        }

                        if (charm != null) {
                            if (charm.getItemDamage() == 3) {
                                CompoundTag nbt = charm.getTagCompound();
                                if (nbt != null && nbt.hasKey("DCsCharm")) {
                                    byte mode = nbt.getByte("DCsCharm");
                                    if (nbt.hasKey("DClimit")) {
                                        int lim = nbt.getInteger("DClimit");
                                    }
                                    if (mode == 1) {
                                        String name = nbt.getString("DCtargetName");
                                        Player target = player.level.getPlayerEntityByName(name);
                                        if (target != null) {
                                            int X = Mth.floor_double(target.posX);
                                            int Y = Mth.floor_double(target.posY) - 1;
                                            int Z = Mth.floor_double(target.posZ);
                                            String DimName = target.level.provider.getDimensionName();

                                            for (int ix = 0; ix < 3; ix++) {
                                                for (int iz = 0; iz < 3; iz++) {
                                                    if (player.level.isAirBlock(X + ix - 1, Y + 1, Z + iz - 1)
                                                        && player.level.isAirBlock(X + ix - 1, Y + 2, Z + iz - 1)
                                                        && player.level.isSideSolid(
                                                            X + ix - 1,
                                                            Y,
                                                            Z + iz - 1,
                                                            Direction.UP)) {
                                                        x = X + ix - 1;
                                                        z = Z + iz - 1;
                                                        y = Y;
                                                        warp = target.level.provider.getDimensionName()
                                                            .equalsIgnoreCase(
                                                                player.level.provider.getDimensionName());
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    if (mode == 2) {
                                        x = nbt.getInteger("DCposX");
                                        y = nbt.getInteger("DCposY");
                                        z = nbt.getInteger("DCposZ");
                                        dim = nbt.getInteger("DCdim");
                                        if (player.level.provider.dimensionId == dim
                                            && player.level.isSideSolid(x, y, z, Direction.UP)) {
                                            warp = true;
                                        }
                                    }
                                }

                            } else if (charm.getItemDamage() == 4) {
                                int X = Mth.floor_double(player.posX);
                                int Y = Mth.floor_double(player.posY);
                                int Z = Mth.floor_double(player.posZ);

                                for (int i = 1; i < 128; i++) {
                                    if (Y + i < 1 || Y + i > 255) {
                                        break;
                                    }

                                    if (ItemPrincessClam.moonCanWarp(player.level, X, Y + i, Z)
                                        && player.level.isAirBlock(X, Y + i + 2, Z)) {
                                        if (player.level.isAirBlock(X, Y + i + 1, Z)) {
                                            y = Y + i;
                                            warp = true;
                                            x = X;
                                            z = Z;
                                            break;
                                        } else if (player.level.getBlock(X, Y + i + 1, Z)
                                            .getMaterial() == /*/*Material*/ water*/ net.minecraft.world.level.material.Fluids.WATER
                                            || player.level.getBlock(X, Y + i + 1, Z)
                                                .getMaterial() == /*Material*/ plants
                                            || player.level.getBlock(X, Y + i + 1, Z)
                                                .getMaterial() == /*Material*/ snow) {
                                                    y = Y + i;
                                                    warp = true;
                                                    x = X;
                                                    z = Z;
                                                    break;
                                                }
                                    }
                                }

                            }

                            if (warp) {
                                AMTLogger.debugInfo("Warp pos : " + x + ", " + y + ", " + z);
                                DCsNetworkHandler.INSTANCE.sendToServer(new MessageCharmWarp(x, y, z));
                            }
                        }
                    }
                }

                if (keyDown && !DCsAppleMilk.proxy.isWarpKeyDown()) {
                    keyDown = false;
                }
            }

        }

        // こちらはEntityLivingBaseの監視用
        if ((entity instanceof LivingEntity)) {
            LivingEntity living = (LivingEntity) event.entity;

            ArrayList<PotionEffect> potions = new ArrayList<PotionEffect>();

            if (living != null && !living.level.isClientSide) {

                boolean f = true;
                if (living instanceof LivingEntity && ((LivingEntity) living).hasCustomNameTag()) {

                } else {
                    if (living instanceof Enemy) {
                        f = false;
                    } else if (living.riddenByEntity != null && living.riddenByEntity instanceof Enemy) {
                        f = false;
                    } else if (living.ridingEntity != null && living.ridingEntity instanceof Enemy) {
                        f = false;
                    }
                }

                if (!f) {
                    int x = Mth.floor_double(living.posX);
                    int y = Mth.floor_double(living.posY);
                    int z = Mth.floor_double(living.posZ);
                    int cX = x >> 4;
                    int cZ = z >> 4;
                    Coord cood = new Coord(cX, cZ, living.level.provider.dimensionId);
                    if (CoordListRegister.isCoodIncluded(cood)) {
                        if (living.riddenByEntity != null) {
                            living.riddenByEntity.discard();
                        }
                        if (living.ridingEntity != null) {
                            living.ridingEntity.discard();
                        }
                        living.discard();
                    } else {
                        f = true;
                    }
                }

                if (f) {
                    // PotionEffectのリスト
                    Iterator iterator = living.getActivePotionEffects()
                        .iterator();

                    while (iterator.hasNext()) {
                        PotionEffect effect = (PotionEffect) iterator.next();

                        int id = effect.getPotionID();
                        Potion potion = Potion.potionTypes[id];

                        if (potion != null && potion instanceof PotionLivingBase) {
                            potions.add(effect);
                        }

                        if (potion != null && potion.id == potion.jump.id) {
                            living.fallDistance = 0.0F;
                        }

                        if (living.ridingEntity != null && living.ridingEntity instanceof LivingEntity) {
                            LivingEntity riding = (LivingEntity) event.entity.ridingEntity;
                            if (potion != null) {
                                riding.addPotionEffect(effect);
                            }
                        }

                    }

                    for (PotionEffect eff : potions) {
                        Potion potion = Potion.potionTypes[eff.getPotionID()];
                        int amp = eff.getAmplifier();
                        int dur = eff.getDuration();

                        if (potion != null && potion instanceof PotionLivingBase) {
                            PotionLivingBase immunity = (PotionLivingBase) potion;

                            if (immunity.formPotionEffect(amp, immunity.id, living)) {
                                AMTLogger.debugInfo("Succeeded to form effect of PotionLivingBase.");
                            }
                        }
                    }
                }
            }
        }
    }
}
