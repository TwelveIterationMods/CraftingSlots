package net.blay09.mods.craftingcraft.client.gui;

import com.mojang.blaze3d.platform.GlStateManager;
import net.blay09.mods.craftingcraft.CraftingCraft;
import net.blay09.mods.craftingcraft.container.InventoryCraftingContainer;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class InventoryCraftingScreen extends ContainerScreen<InventoryCraftingContainer> {

    private final ResourceLocation texture = new ResourceLocation(CraftingCraft.MOD_ID, "textures/gui/portable_crafting_table.png");

    public InventoryCraftingScreen(InventoryCraftingContainer container, PlayerInventory playerInventory, ITextComponent displayName) {
        super(container, playerInventory, displayName);
        xSize = 218;
        ySize = 102;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        minecraft.fontRenderer.drawString(I18n.format(CraftingCraft.MOD_ID + ":container.inventory_crafting_table"), 8, ySize - 96 + 1, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float delta, int mouseX, int mouseY) {
        GlStateManager.color4f(1f, 1f, 1f, 1f);
        minecraft.getTextureManager().bindTexture(texture);
        blit(guiLeft, guiTop, 0, 0, xSize, ySize);
    }

}
