package net.blay09.mods.craftingslots.client.screen;

import net.blay09.mods.craftingslots.CraftingSlots;
import net.blay09.mods.craftingslots.container.InventoryCraftingMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class InventoryCraftingScreen extends AbstractContainerScreen<InventoryCraftingMenu> {

    private final ResourceLocation texture = ResourceLocation.fromNamespaceAndPath(CraftingSlots.MOD_ID, "textures/gui/portable_crafting.png");

    public InventoryCraftingScreen(InventoryCraftingMenu container, Inventory playerInventory, Component displayName) {
        super(container, playerInventory, displayName);
        imageWidth = 218;
        imageHeight = 102;
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
        guiGraphics.setColor(1f, 1f, 1f, 1f);
        guiGraphics.blit(texture, leftPos, topPos, 0, 0, imageWidth, imageHeight);
    }

}
