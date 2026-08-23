package mods.defeatedcrow.client.gui;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

import mods.defeatedcrow.common.tile.appliance.ContainerIceMaker;
import mods.defeatedcrow.common.tile.appliance.TileIceMaker;

/**
 * 1.20.1 screen for the Ice Maker (was {@code GuiContainer}).
 *
 * <p>
 * Migration assumptions (owner: WT-B / common-tile):
 * <ul>
 * <li>{@code ContainerIceMaker} extends {@code AbstractContainerMenu} with a network constructor
 * {@code (int id, Inventory inv, FriendlyByteBuf buf)} reading the backing {@code BlockPos}.</li>
 * <li>{@code ContainerIceMaker#getTile()} exposes the backing {@code TileIceMaker}.</li>
 * <li>Screen registration (bootstrap / ModMenuTypes territory, NOT done here):
 * {@code MenuScreens.register(ModMenuTypes.ICE_MAKER.get(), GuiIceMaker::new);} inside
 * {@code FMLClientSetupEvent#enqueueWork}.</li>
 * </ul>
 */
public class GuiIceMaker extends AbstractContainerScreen<ContainerIceMaker> {

    private static final ResourceLocation TEXTURE = new ResourceLocation("defeatedcrow", "textures/gui/icemakergui.png");

    private final TileIceMaker tileentity;

    public GuiIceMaker(ContainerIceMaker menu, Inventory playerInv, Component title) {
        super(menu, playerInv, title);
        this.tileentity = menu.getTile();
        this.imageWidth = 176;
        this.imageHeight = 166;
        this.titleLabelY = 6;
        this.inventoryLabelY = this.imageHeight - 94;
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(graphics);
        super.render(graphics, mouseX, mouseY, partialTick);

        // チャージゲージのマウスオーバー
        boolean b1 = this.isHovering(11, 26, 12, 27, mouseX, mouseY);
        if (b1) {
            int charge = this.tileentity.getChargeAmount();
            graphics.renderTooltip(this.font, Component.literal("Ice Charge Amount : " + charge), mouseX, mouseY);
        }
        this.renderTooltip(graphics, mouseX, mouseY);
    }

    @Override
    protected void renderBg(GuiGraphics graphics, float partialTick, int mouseX, int mouseY) {
        // かまど描画処理
        int k = this.leftPos;
        int l = this.topPos;
        graphics.blit(TEXTURE, k, l, 0, 0, this.imageWidth, this.imageHeight);

        int i1 = this.tileentity.getBurnTimeRemainingScaled(16);
        if (i1 > 0) {
            graphics.blit(TEXTURE, k + 57, l + 36 + 16 - i1, 176, 16 - i1, 14, i1);
        }

        int i2 = this.tileentity.getCookProgressScaled(24);
        if (i2 > 0) {
            graphics.blit(TEXTURE, k + 79, l + 34, 176, 14, i2 + 1, 16);
        }
    }

}
