package mods.defeatedcrow.handler;

import net.minecraft.world.level.Level;

/**
 * 1.20.1 TimeHandler - level time utilities.
 * Original 1.7.10 used World.getWorldInfo().getWorldTime(), now Level.getDayTime().
 */
public class TimeHandler {

    private TimeHandler() {}

    public static long time(Level level) {
        return level.getDayTime() % 24000L;
    }

    public static long totalTime(Level level) {
        return level.getDayTime();
    }

    public static boolean isDayTime(Level level) {
        int t = currentTime(level);
        return t > 5 && t < 18;
    }

    public static int currentTime(Level level) {
        long time = time(level);
        time += 6000;
        if (time > 24000) time -= 24000;
        return (int) (time / 1000);
    }

    public static int getSeason(Level level) {
        long day = (totalTime(level) / 24000L) + 1;
        int season = (int) (((day - 1) / 30) & 3);
        return season;
    }

    public static int getDay(Level level) {
        long day = (totalTime(level) / 24000L) + 1;
        if (day > Integer.MAX_VALUE) day -= Integer.MAX_VALUE;
        return (int) day;
    }
}
