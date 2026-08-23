package mods.defeatedcrow.client.gui;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.network.chat.Component;
import net.minecraft.world.inventory.AbstractContainerMenu;
public class GuiIceMaker extends AbstractContainerScreen<AbstractContainerMenu> {
    public GuiIceMaker(AbstractContainerMenu m, Inventory inv, Component c){ super(m, inv, c); }
    @Override protected void renderBg(net.minecraft.client.gui.GuiGraphics g, float f, int i, int j){}
}
