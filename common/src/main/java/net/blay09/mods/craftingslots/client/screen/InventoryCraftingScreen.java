package net.blay09.mods.craftingslots.client.screen;

import net.blay09.mods.craftingslots.CraftingSlots;
import net.blay09.mods.craftingslots.menu.InventoryCraftingMenu;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.navigation.ScreenPosition;
import net.minecraft.client.gui.screens.inventory.AbstractRecipeBookScreen;
import net.minecraft.client.gui.screens.recipebook.CraftingRecipeBookComponent;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;

public class InventoryCraftingScreen extends AbstractRecipeBookScreen<InventoryCraftingMenu> {

    private final Identifier texture = Identifier.fromNamespaceAndPath(CraftingSlots.MOD_ID, "textures/gui/portable_crafting.png");

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
    protected void extractLabels(GuiGraphicsExtractor guiGraphics, int i, int j) {
        guiGraphics.text(font, title, 8, imageHeight - 96 + 1, 0xFF404040, false);
    }

    @Override
    public void extractBackground(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
        super.extractBackground(graphics, mouseX, mouseY, a);
        graphics.blit(RenderPipelines.GUI_TEXTURED, texture, leftPos, topPos, 0, 0, actualImageWidth, imageHeight, 256, 256);
    }

    @Override
    protected boolean hasClickedOutside(double x, double y, int leftPos, int topPos) {
        final var isOutside = x < (double) leftPos || y < (double) topPos || x >= (double) (leftPos + actualImageWidth) || y >= (double) (topPos + this.imageHeight);
        return isOutside && super.hasClickedOutside(x, y, leftPos, topPos);
    }
}
