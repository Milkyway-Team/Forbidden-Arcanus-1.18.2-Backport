package com.stal111.forbidden_arcanus.client.gui.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.stal111.forbidden_arcanus.ForbiddenArcanus;
import com.stal111.forbidden_arcanus.common.block.entity.clibano.ClibanoMainBlockEntity;
import com.stal111.forbidden_arcanus.common.block.entity.clibano.ResiduesStorage;
import com.stal111.forbidden_arcanus.common.inventory.clibano.ClibanoMenu;
import com.stal111.forbidden_arcanus.util.backports.Components;
import net.minecraft.Util;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Clibano Screen <br>
 * Forbidden Arcanus - com.stal111.forbidden_arcanus.client.gui.screen.ClibanoScreen
 *
 * @author stal111
 * @version 1.18.2 - 2.1.0
 * @since 2022-05-25
 */
public class ClibanoScreen extends AbstractContainerScreen<ClibanoMenu> {
    
    private static final ResourceLocation TEXTURES = new ResourceLocation(ForbiddenArcanus.MOD_ID, "textures/gui/container/clibano_combustion.png");
    
    public ClibanoScreen(ClibanoMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title);
        this.imageHeight += 7;
        this.titleLabelY -= 2;
        this.inventoryLabelY += 9;
    }
    
    @Override
    public void render(@Nonnull PoseStack poseStack, int mouseX, int mouseY, float partialTick) {
        super.render(poseStack, mouseX, mouseY, partialTick);
        
        this.renderTooltip(poseStack, mouseX, mouseY);
    }
    
    @Override
    protected void renderBg(@Nonnull PoseStack poseStack, float partialTick, int mouseX, int mouseY) {
        this.renderBackground(poseStack);
        
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURES);
        
        this.blit(poseStack, this.getGuiLeft(), this.getGuiTop(), 0, 0, this.getXSize(), this.getYSize());
        
        // Cooking Indicators
        if (this.menu.getCookingDuration().getFirst() != 0) {
            int xSize = Math.toIntExact(Math.round(22.0F * this.menu.getCookingProgress().getFirst() / this.menu.getCookingDuration().getFirst()));
            
            this.blit(poseStack, this.getGuiLeft() + 83, this.getGuiTop() + 34, 176, 32, xSize, 7);
        }
        
        if (this.menu.getCookingDuration().getSecond() != 0) {
            int xSize = Math.toIntExact(Math.round(22.0F * this.menu.getCookingProgress().getSecond() / this.menu.getCookingDuration().getSecond()));
            
            this.blit(poseStack, this.getGuiLeft() + 83, this.getGuiTop() + 43, 176, 41, xSize, 7);
        }
        
        // Soul Indicator
        if (this.menu.isSoulActive()) {
            int ySize = Math.toIntExact(Math.round(18.0F * this.menu.getSoulDuration() / ClibanoMainBlockEntity.SOUL_DURATION));
            
            this.blit(poseStack, this.getGuiLeft() + 52, this.getGuiTop() + 38 + 18 - ySize, 233, 18 - ySize, 18, ySize);
        }
        
        // Flame
        if (this.menu.getBurnDuration() != 0) {
            int ySize = Math.toIntExact(Math.round(15.0F * this.menu.getBurnTime() / this.menu.getBurnDuration()));
            
            int uOffset = 179 + 19 * this.menu.getFireType();
            
            this.blit(poseStack, this.getGuiLeft() + 55, this.getGuiTop() + 39 + 15 - ySize, uOffset, 1 + 15 - ySize, 12, ySize);
        }
        
        // Residue Bar
        if (this.menu.getResidueFullness() != 0) {
            int xSize = Math.toIntExact(Math.round(26.0F * this.menu.getResidueFullness() / ResiduesStorage.MAX_AMOUNT));
            
            this.blit(poseStack, this.getGuiLeft() + 111, this.getGuiTop() + 58, 179, 21, xSize, 7);
        }
    }
    
    @Override
    protected void renderTooltip(@Nonnull PoseStack poseStack, int x, int y) {
        super.renderTooltip(poseStack, x, y);
        
        int posX = x - this.getGuiLeft();
        int posY = y - this.getGuiTop();
        
        if (posY >= 58 && posY <= 64 && posX >= 111 && posX <= 136) {
            List<Component> textComponents = Util.make(new ArrayList<>(), list -> {
                list.add(Components.translatable("gui.forbidden_arcanus.clibano.residue_fullness").append(": " + this.menu.getResidueFullness() + "/" + ResiduesStorage.MAX_AMOUNT));
                list.add(Components.empty());
            });
            
            this.menu.getResidueData().forEach((residueType, integer) -> {
                if (integer == 0) {
                    return;
                }
                textComponents.add(residueType.getComponent().append(": " + integer));
            });
            
            if (textComponents.size() == 2) {
                textComponents.remove(1);
            }
            
            this.renderTooltip(poseStack, textComponents, Optional.empty(), x, y);
        }
    }
}
