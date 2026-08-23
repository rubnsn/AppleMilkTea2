package mods.defeatedcrow.client.gui;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.level.material.FluidStack;

import mods.defeatedcrow.common.config.PropertyHandler;
import mods.defeatedcrow.common.tile.appliance.ContainerEvaporator;
import mods.defeatedcrow.common.tile.appliance.TileEvaporator;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;

/**
 * 1.20.1 screen for the Evaporator (was {@code GuiContainer}).
 *
 * <p>
 * Migration assumptions (owner: WT-B / common-tile):
 * <ul>
 * <li>{@code ContainerEvaporator} extends {@code AbstractContainerMenu} with a network constructor
 * {@code (int id, Inventory inv, FriendlyByteBuf buf)} reading the backing {@code BlockPos}.</li>
 * <li>{@code ContainerEvaporator#getTile()} exposes the backing {@code TileEvaporator}, whose
 * {@code productTank} / {@code getFluidAmountScaled(int)} remain accessible client-side.</li>
 * <li>Screen registration (bootstrap / ModMenuTypes territory, NOT done here):
 * {@code MenuScreens.register(ModMenuTypes.EVAPORATOR.get(), GuiEvaporator::new);} inside
 * {@code FMLClientSetupEvent#enqueueWork}.</li>
 * </ul>
 */
public class GuiEvaporator extends AbstractContainerScreen<ContainerEvaporator> {

    private static final ResourceLocation TEXTURE = new ResourceLocation("defeatedcrow", "textures/gui/evaporatorgui.png");

    private final TileEvaporator tileentity;

    public GuiEvaporator(ContainerEvaporator menu, Inventory playerInv, Component title) {
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

        // 液体情報
        boolean b2 = this.isHovering(141, 13, 16, 41, mouseX, mouseY);
        if (b2) {
            List<Component> list2 = new ArrayList<Component>();
            FluidStack fluid = this.tileentity.productTank.getFluid();
            if (!fluid.isEmpty()) {
                list2.add(Component.literal("Fluid : " + fluid.getDisplayName().getString()));
                list2.add(Component.literal("Amount : " + fluid.getAmount()));
            }
            if (!list2.isEmpty()) {
                graphics.renderComponentTooltip(this.font, list2, mouseX, mouseY);
            }
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
            graphics.blit(TEXTURE, k + 77, l + 18, 176, 0, i2 + 1, 16);
        }

        int i3 = this.tileentity.getCookProgressScaled(16);
        if (i3 > 0) {
            graphics.blit(TEXTURE, k + 57, l + 35, 201, 0, 15, i3);
        }

        drawFluid(
            graphics,
            this.tileentity.productTank.getFluid(),
            this.tileentity.getFluidAmountScaled(41),
            k + 141,
            l + 13,
            16,
            41);
    }

    /**
     * Original code was made by Shift02.
     * 1.20.1: legacy icon/tessellator drawing and raw GL state calls are replaced by the still-fluid
     * sprite of
     * {@link IClientFluidTypeExtensions} drawn via {@link GuiGraphics#blit} (blaze3d managed).
     */
    private static void drawFluid(GuiGraphics graphics, FluidStack fluid, int level, int x, int y, int width,
        int height) {
        if (fluid == null || fluid.isEmpty()) {
            return;
        }

        IClientFluidTypeExtensions renderProps = IClientFluidTypeExtensions.of(fluid.getFluid());
        ResourceLocation stillTex = renderProps.getStillTexture(fluid);
        if (stillTex == null) {
            return;
        }
        int tint = renderProps.getTintColor(fluid);

        float red = ((tint >> 16 & 255) / 255.0F);
        float green = ((tint >> 8 & 255) / 255.0F);
        float blue = ((tint & 255) / 255.0F);
        float alpha = tint == -1 ? 1.0F : ((tint >>> 24) / 255.0F);
        if (alpha <= 0.0F) {
            alpha = 1.0F;
        }

        int heiR = Math.min(level, height);
        int yR = y + (height - heiR);

        graphics.setColor(red, green, blue, alpha);
        for (int i = 0; i < width; i += 16) {
            for (int j = 0; j < heiR; j += 16) {
                int widL = Math.min(width - i, 16);
                int heiL = Math.min(heiR - j, 16);
                graphics.blit(stillTex, x + i, yR + j, 0, 0.0F, 0.0F, widL, heiL, 16, 16);
            }
        }
        graphics.setColor(1.0F, 1.0F, 1.0F, 1.0F);
    }

}
