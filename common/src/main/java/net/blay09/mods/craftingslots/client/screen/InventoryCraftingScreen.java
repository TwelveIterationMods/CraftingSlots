package net.blay09.mods.craftingslots.client.screen;

import net.blay09.mods.craftingslots.CraftingSlots;
import net.blay09.mods.craftingslots.menu.InventoryCraftingMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.navigation.ScreenPosition;
import net.minecraft.client.gui.screens.inventory.AbstractRecipeBookScreen;
import net.minecraft.client.gui.screens.recipebook.CraftingRecipeBookComponent;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class InventoryCraftingScreen extends AbstractRecipeBookScreen<InventoryCraftingMenu> {

    private final ResourceLocation texture = ResourceLocation.fromNamespaceAndPath(CraftingSlots.MOD_ID, "textures/gui/portable_crafting.png");

    private final int actualImageWidth = 218;

    public InventoryCraftingScreen(InventoryCraftingMenu menu, Inventory playerInventory, Component displayName) {
        super(menu, new CraftingRecipeBookComponent(menu), playerInventory, displayName);
        imageHeight = 102;
    }

    @Override
    protected ScreenPosition getRecipeBookButtonPosition() {
        return new ScreenPosition(leftPos + imageWidth + 8, topPos + 8);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        super.render(guiGraphics, mouseX, mouseY, partialTicks);
        renderTooltip(guiGraphics, mouseX, mouseY);
    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int i, int j) {
        guiGraphics.drawString(font, title, 8, imageHeight - 96 + 1, 0x404040, false);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float f, int i, int j) {
        guiGraphics.blit(RenderPipelines.GUI_TEXTURED, texture, leftPos, topPos, 0, 0, actualImageWidth, imageHeight, 256, 256);
    }

}
