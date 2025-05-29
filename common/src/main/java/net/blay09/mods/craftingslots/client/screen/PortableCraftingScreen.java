package net.blay09.mods.craftingslots.client.screen;

import net.blay09.mods.craftingslots.menu.PortableCraftingMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.navigation.ScreenPosition;
import net.minecraft.client.gui.screens.inventory.AbstractRecipeBookScreen;
import net.minecraft.client.gui.screens.recipebook.CraftingRecipeBookComponent;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class PortableCraftingScreen extends AbstractRecipeBookScreen<PortableCraftingMenu> {

    private static final ResourceLocation texture = ResourceLocation.withDefaultNamespace("textures/gui/container/crafting_table.png");

    public PortableCraftingScreen(PortableCraftingMenu menu, Inventory playerInventory, Component displayName) {
        super(menu, new CraftingRecipeBookComponent(menu), playerInventory, displayName);
    }

    @Override
    protected ScreenPosition getRecipeBookButtonPosition() {
        return new ScreenPosition(leftPos + imageWidth - 25, topPos + 5);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        super.render(guiGraphics, mouseX, mouseY, partialTicks);
        renderTooltip(guiGraphics, mouseX, mouseY);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float f, int i, int j) {
        guiGraphics.blit(RenderPipelines.GUI_TEXTURED, texture, leftPos, topPos, 0, 0, imageWidth, imageHeight, 256, 256);
    }

}
