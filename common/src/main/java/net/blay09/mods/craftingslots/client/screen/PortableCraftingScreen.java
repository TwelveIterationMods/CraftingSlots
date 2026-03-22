package net.blay09.mods.craftingslots.client.screen;

import net.blay09.mods.craftingslots.menu.PortableCraftingMenu;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.navigation.ScreenPosition;
import net.minecraft.client.gui.screens.inventory.AbstractRecipeBookScreen;
import net.minecraft.client.gui.screens.recipebook.CraftingRecipeBookComponent;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;

public class PortableCraftingScreen extends AbstractRecipeBookScreen<PortableCraftingMenu> {

    private static final Identifier texture = Identifier.withDefaultNamespace("textures/gui/container/crafting_table.png");

    public PortableCraftingScreen(PortableCraftingMenu menu, Inventory playerInventory, Component displayName) {
        super(menu, new CraftingRecipeBookComponent(menu), playerInventory, displayName);
    }

    @Override
    protected ScreenPosition getRecipeBookButtonPosition() {
        return new ScreenPosition(leftPos + imageWidth - 25, topPos + 5);
    }

    @Override
    public void render(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float partialTicks) {
        super.render(guiGraphics, mouseX, mouseY, partialTicks);
        renderTooltip(guiGraphics, mouseX, mouseY);
    }

    @Override
    protected void renderBg(GuiGraphicsExtractor guiGraphics, float f, int i, int j) {
        guiGraphics.blit(RenderPipelines.GUI_TEXTURED, texture, leftPos, topPos, 0, 0, imageWidth, imageHeight, 256, 256);
    }

}
