package net.blay09.mods.craftingslots.client.screen;

import net.blay09.mods.craftingslots.container.PortableCraftingMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class PortableCraftingScreen extends AbstractContainerScreen<PortableCraftingMenu> {

    private static final ResourceLocation texture = ResourceLocation.withDefaultNamespace("textures/gui/container/crafting_table.png");

    public PortableCraftingScreen(PortableCraftingMenu container, Inventory playerInventory, Component displayName) {
        super(container, playerInventory, displayName);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        super.render(guiGraphics, mouseX, mouseY, partialTicks);
        renderTooltip(guiGraphics, mouseX, mouseY);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float f, int i, int j) {
        guiGraphics.blit(RenderType::guiTextured, texture, leftPos, topPos, 0, 0, imageWidth, imageHeight, 256, 256);
    }

}
