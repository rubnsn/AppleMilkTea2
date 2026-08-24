package mods.defeatedcrow.client.gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import mods.defeatedcrow.common.tile.appliance.ContainerIceMaker;
public class GuiIceMaker extends AbstractContainerScreen<ContainerIceMaker> {
    private static final ResourceLocation TEX = new ResourceLocation("defeatedcrow", "textures/gui/icemakergui.png");
    public GuiIceMaker(ContainerIceMaker m, Inventory inv, Component c){
        super(m, inv, c);
        this.imageWidth = 176;
        this.imageHeight = 166;
    }
    @Override protected void renderBg(GuiGraphics g, float f, int x, int y){
        int k = (this.width - this.imageWidth) / 2;
        int l = (this.height - this.imageHeight) / 2;
        g.blit(TEX, k, l, 0, 0, this.imageWidth, this.imageHeight);
        int charge = this.menu.getCharge();
        int cook = this.menu.getCookTime();
        int i1 = charge * 16 / 127;
        if(i1>16) i1=16;
        // burn bar 14x16 at 57,36
        g.blit(TEX, k + 57, l + 36 + 16 - i1, 176, 16 - i1, 14, i1);
        int i2 = cook * 24 / 150;
        if(i2>24) i2=24;
        g.blit(TEX, k + 79, l + 34, 176, 14, i2 + 1, 16);
    }
    @Override protected void renderLabels(GuiGraphics g, int i, int j) {
        g.drawString(this.font, this.title, this.imageWidth / 2 - this.font.width(this.title) / 2, 6, 0x404040, false);
        g.drawString(this.font, this.playerInventoryTitle, 8, this.imageHeight - 96 + 2, 0x404040, false);
        // tooltip for charge bar is handled in render
    }
    @Override public void render(GuiGraphics g, int x, int y, float f){
        this.renderBackground(g);
        super.render(g, x, y, f);
        this.renderTooltip(g, x, y);
        int k = (this.width - this.imageWidth) / 2;
        int l = (this.height - this.imageHeight) / 2;
        if(x >= k+57 && x < k+71 && y >= l+36 && y < l+52){
            g.renderTooltip(this.font, Component.literal("Charge: "+this.menu.getCharge()), x, y);
        }
    }
}
