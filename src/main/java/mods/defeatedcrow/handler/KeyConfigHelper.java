package mods.defeatedcrow.handler;

import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * 1.20.1 KeyConfigHelper - key binding codes.
 * Original 1.7.10 used Minecraft.getMinecraft().gameSettings.keyBind*, now Options.
 */
@OnlyIn(Dist.CLIENT)
public class KeyConfigHelper {

    private KeyConfigHelper() {}

    public static int getJumpKey() { return Minecraft.getInstance().options.keyJump.getKey().getValue(); }
    public static int getFowardKey() { return Minecraft.getInstance().options.keyUp.getKey().getValue(); }
    public static int getBackKey() { return Minecraft.getInstance().options.keyDown.getKey().getValue(); }
    public static int getLeftKey() { return Minecraft.getInstance().options.keyLeft.getKey().getValue(); }
    public static int getRightKey() { return Minecraft.getInstance().options.keyRight.getKey().getValue(); }
    public static int getSneakKey() { return Minecraft.getInstance().options.keyShift.getKey().getValue(); }
}
