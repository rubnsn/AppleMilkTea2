package mods.defeatedcrow.client.gui;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.network.chat.Component;
import net.minecraft.world.inventory.AbstractContainerMenu;
public class GuiBatBox extends AbstractContainerScreen<AbstractContainerMenu> {
    public GuiBatBox(AbstractContainerMenu m, Inventory inv, Component c) { super(m, inv, c); }
    @Override protected void renderBg(net.minecraft.client.gui.GuiGraphics g, float f, int i, int j) {}
}
