package mods.defeatedcrow.client.gui;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

import mods.defeatedcrow.common.config.PropertyHandler;
import mods.defeatedcrow.common.tile.appliance.ContainerAdvProcessor;
import mods.defeatedcrow.common.tile.appliance.TileAdvProcessor;

/**
 * 1.20.1 screen for the Advanced Processor / Jaw Crusher (was {@code GuiContainer}).
 *
 * <p>
 * Migration assumptions (owner: WT-B / common-tile):
 * <ul>
 * <li>{@code ContainerAdvProcessor} extends {@code AbstractContainerMenu} with a network constructor
 * {@code (int id, Inventory inv, FriendlyByteBuf buf)} reading the backing {@code BlockPos}.</li>
 * <li>{@code ContainerAdvProcessor#getTile()} exposes the backing {@code TileAdvProcessor}.</li>
 * <li>Screen registration (bootstrap / ModMenuTypes territory, NOT done here):
 * {@code MenuScreens.register(ModMenuTypes.ADV_PROCESSOR.get(), GuiAdvProcessor::new);} inside
 * {@code FMLClientSetupEvent#enqueueWork}.</li>
 * </ul>
 */
public class GuiAdvProcessor extends AbstractContainerScreen<ContainerAdvProcessor> {

    private static final ResourceLocation TEXTURE = new ResourceLocation("defeatedcrow", "textures/gui/jawcrushergui.png");

    protected final TileAdvProcessor tileentity;

    public GuiAdvProcessor(ContainerAdvProcessor menu, Inventory playerInv, Component title) {
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
            List<Component> list1 = new ArrayList<Component>();
            list1.add(Component.literal("Charge Amount : " + charge + "/" + this.tileentity.getMaxChargeAmount()));

            if (hasShiftDown()) { // shiftキー押下時
                int vsRF = charge * PropertyHandler.rateRF();
                int vsEU = charge * PropertyHandler.rateEU();
                int vsGF = charge * PropertyHandler.rateGF();
                list1.add(Component.literal(" - " + vsRF + "/" + this.tileentity.getMaxChargeAmount()
                    * PropertyHandler.rateRF() + " RF"));
                list1.add(Component.literal(" - " + vsEU + "/" + this.tileentity.getMaxChargeAmount()
                    * PropertyHandler.rateEU() + " EU"));
                list1.add(Component.literal(" - " + vsGF + "/" + this.tileentity.getMaxChargeAmount()
                    * PropertyHandler.rateGF() + " GF"));
            } else {
                list1.add(Component.literal("LShift: Expand tooltip.").withStyle(ChatFormatting.ITALIC));
            }
            graphics.renderComponentTooltip(this.font, list1, mouseX, mouseY);
        }
        this.renderTooltip(graphics, mouseX, mouseY);
    }

    @Override
    protected void renderBg(GuiGraphics graphics, float partialTick, int mouseX, int mouseY) {
        // かまど描画処理
        int k = this.leftPos;
        int l = this.topPos;
        graphics.blit(TEXTURE, k, l, 0, 0, this.imageWidth, this.imageHeight);

        int i1 = this.tileentity.getBurnTimeRemainingScaled(27);
        if (i1 > 0) {
            graphics.blit(TEXTURE, k + 11, l + 53 - i1, 176, 43 - i1, 12, i1);
        }

        int i2 = this.tileentity.getCookProgressScaled(24);
        if (i2 > 0) {
            graphics.blit(TEXTURE, k + 88, l + 43, 176, 0, i2 + 1, 16);
        }
    }

}
