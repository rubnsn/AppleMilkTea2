package mods.defeatedcrow.handler;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

import mods.defeatedcrow.common.config.DCsConfig;

/**
 * 1.20.1 Util - config helpers and misc.
 * Original 1.7.10 used World+Block+int x,y,z+Item, now Level+BlockPos+BlockState.
 */
public class Util {

    private Util() {}

    public static int getCupRender() {
        int l = DCsConfig.setCupTexture;
        if (l < 0) l = 1;
        else if (l > 3) l = 3;
        return l;
    }

    public static int getTeppannReadyTime() {
        int l = DCsConfig.teppannReadyTime;
        if (l < 0) l = 1;
        else if (l > 60) l = 60;
        return l;
    }

    public static int getCupStacksize() {
        int l = DCsConfig.cupStackSize;
        if (l <= 1) l = 1;
        else if (l <= 3) l = 3;
        else l = 8;
        return l;
    }

    public static int getHamaguriChanceValue() {
        int l = DCsConfig.clamChanceValue;
        if (l < 0) l = 1;
        else if (l > 100) l = 100;
        return l;
    }

    public static int getPrincessChanceValue() {
        int l = DCsConfig.princessChanceValue;
        if (l < 0) l = 1;
        else if (l > 100) l = 100;
        return l;
    }

    public static String getTexturePass() {
        int l = DCsConfig.setAltTexturePass - 1;
        if (l < 0) l = 0;
        else if (l > 2) l = 2;
        String[] passes = {"defeatedcrow:textures/", "defeatedcrow:textures/alt/", "defeatedcrow:textures/hd/"};
        return passes[l];
    }

    public static boolean isPlayerInDebugMode(Player player) {
        // TODO: check debugPass vs player name when DCsConfig.debugPass is set
        return false;
    }

    // World helpers - 1.20.1 Level + BlockPos
    public static boolean isTimeDay(Level level) {
        long t = level.getDayTime() % 24000L;
        t = (t + 6000) % 24000;
        int hour = (int)(t / 1000);
        return hour > 5 && hour < 18;
    }
}
