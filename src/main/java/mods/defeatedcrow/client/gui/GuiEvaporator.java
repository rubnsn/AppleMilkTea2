package mods.defeatedcrow.client.gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import mods.defeatedcrow.common.tile.appliance.ContainerEvaporator;
public class GuiEvaporator extends AbstractContainerScreen<ContainerEvaporator> {
    private static final ResourceLocation TEX = new ResourceLocation("defeatedcrow", "textures/gui/evaporatorgui.png");
    public GuiEvaporator(ContainerEvaporator m, Inventory inv, Component c) { super(m, inv, c); this.imageWidth=176; this.imageHeight=166; }
    @Override protected void renderBg(GuiGraphics g, float f, int x, int y){
        int k = (this.width - this.imageWidth) / 2;
        int l = (this.height - this.imageHeight) / 2;
        g.blit(TEX, k, l, 0, 0, this.imageWidth, this.imageHeight);
        int cook = this.menu.getCookTime();
        // progress at 77,18 horizontal 24px, and vertical at 57,35 15px
        int i2 = cook * 24 / 100;
        if(i2>24) i2=24;
        g.blit(TEX, k + 77, l + 18, 176, 0, i2 + 1, 16);
        int i3 = cook * 16 / 100;
        if(i3>16) i3=16;
        g.blit(TEX, k + 57, l + 35, 201, 0, 15, i3);
        // charge bar at 11,26 vertical 27
        // Evaporator tile currently has no charge field, but we treat cook as charge for now
        // draw empty bar placeholder
    }
    @Override protected void renderLabels(GuiGraphics g, int i, int j) {
        g.drawString(this.font, this.title, this.imageWidth / 2 - this.font.width(this.title) / 2, 6, 0x404040, false);
        g.drawString(this.font, this.playerInventoryTitle, 8, this.imageHeight - 96 + 2, 0x404040, false);
    }
    @Override public void render(GuiGraphics g, int x, int y, float f){
        this.renderBackground(g);
        super.render(g, x, y, f);
        this.renderTooltip(g, x, y);
    }
}
