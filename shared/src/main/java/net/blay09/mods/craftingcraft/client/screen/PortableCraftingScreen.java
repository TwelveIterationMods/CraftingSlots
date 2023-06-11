package net.blay09.mods.craftingcraft.client.screen;

import net.blay09.mods.craftingcraft.container.PortableCraftingMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class PortableCraftingScreen extends AbstractContainerScreen<PortableCraftingMenu> {

    private static final ResourceLocation texture = new ResourceLocation("textures/gui/container/crafting_table.png");

    public PortableCraftingScreen(PortableCraftingMenu container, Inventory playerInventory, Component displayName) {
        super(container, playerInventory, displayName);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        renderBackground(guiGraphics);
        super.render(guiGraphics, mouseX, mouseY, partialTicks);
        renderTooltip(guiGraphics, mouseX, mouseY);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float f, int i, int j) {
        guiGraphics.setColor(1f, 1f, 1f, 1f);
        guiGraphics.blit(texture, leftPos, topPos, 0, 0, imageWidth, imageHeight);
    }

}
