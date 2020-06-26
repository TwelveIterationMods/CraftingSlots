package net.blay09.mods.craftingcraft.client.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.blay09.mods.craftingcraft.CraftingCraft;
import net.blay09.mods.craftingcraft.container.InventoryCraftingContainer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class InventoryCraftingScreen extends ContainerScreen<InventoryCraftingContainer> {

    private final ResourceLocation texture = new ResourceLocation(CraftingCraft.MOD_ID, "textures/gui/portable_crafting.png");

    public InventoryCraftingScreen(InventoryCraftingContainer container, PlayerInventory playerInventory, ITextComponent displayName) {
        super(container, playerInventory, displayName);
        xSize = 218;
        ySize = 102;
    }

    @Override // render
    public void func_230430_a_(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        func_230446_a_(matrixStack); // renderBackground
        super.func_230430_a_(matrixStack, mouseX, mouseY, partialTicks);
        func_230459_a_(matrixStack, mouseX, mouseY); // renderHoveredTooltip
    }

    @Override // drawGuiContainerForegroundLayer
    protected void func_230451_b_(MatrixStack matrixStack, int mouseX, int mouseY) {
        FontRenderer font = field_230712_o_;
        font.func_238422_b_(matrixStack, field_230704_d_, 8, ySize - 96 + 1, 4210752); // drawString
    }

    @Override // drawGuiContainerBackgroundLayer
    protected void func_230450_a_(MatrixStack matrixStack, float delta, int mouseX, int mouseY) {
        RenderSystem.color4f(1f, 1f, 1f, 1f);
        Minecraft.getInstance().getTextureManager().bindTexture(texture);
        func_238474_b_(matrixStack, guiLeft, guiTop, 0, 0, xSize, ySize); // blit
    }

}
