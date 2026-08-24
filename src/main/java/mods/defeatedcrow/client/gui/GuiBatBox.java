package mods.defeatedcrow.client.gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import mods.defeatedcrow.common.tile.energy.ContainerBatBox;
public class GuiBatBox extends AbstractContainerScreen<ContainerBatBox> {
    private static final ResourceLocation TEX = new ResourceLocation("defeatedcrow", "textures/gui/batboxgui.png");
    public GuiBatBox(ContainerBatBox m, Inventory inv, Component c) { super(m, inv, c); this.imageWidth=176; this.imageHeight=166; }
    @Override protected void renderBg(GuiGraphics g, float f, int x, int y){
        int k = (this.width - this.imageWidth) / 2;
        int l = (this.height - this.imageHeight) / 2;
        g.blit(TEX, k, l, 0, 0, this.imageWidth, this.imageHeight);
        int charge = this.menu.getCharge();
        int max = 128000;
        int i1 = (int)((long)charge * 27 / max);
        if(i1>27) i1=27;
        g.blit(TEX, k + 11, l + 53 - i1, 176, 43 - i1, 12, i1);
    }
    @Override protected void renderLabels(GuiGraphics g, int i, int j) {
        g.drawString(this.font, this.title, this.imageWidth / 2 - this.font.width(this.title) / 2, 6, 0x404040, false);
        g.drawString(this.font, this.playerInventoryTitle, 8, this.imageHeight - 96 + 2, 0x404040, false);
    }
    @Override public void render(GuiGraphics g, int x, int y, float f){
        this.renderBackground(g);
        super.render(g, x, y, f);
        this.renderTooltip(g, x, y);
        int k = (this.width - this.imageWidth) / 2;
        int l = (this.height - this.imageHeight) / 2;
        if(x >= k+11 && x < k+23 && y >= l+26 && y < l+53){
            g.renderTooltip(this.font, Component.literal("Charge: "+this.menu.getCharge()+" / 128000"), x, y);
        }
    }
}
