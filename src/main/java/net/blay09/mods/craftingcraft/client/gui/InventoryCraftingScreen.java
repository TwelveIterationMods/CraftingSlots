package net.blay09.mods.craftingcraft.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import net.blay09.mods.craftingcraft.CraftingCraft;
import net.blay09.mods.craftingcraft.container.InventoryCraftingContainer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.resources.I18n;
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

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        renderBackground();
        super.render(mouseX, mouseY, partialTicks);
        renderHoveredToolTip(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        font.drawString(I18n.format("container.craftingcraft.inventory_crafting"), 8, ySize - 96 + 1, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float delta, int mouseX, int mouseY) {
        RenderSystem.color4f(1f, 1f, 1f, 1f);
        Minecraft.getInstance().getTextureManager().bindTexture(texture);
        blit(guiLeft, guiTop, 0, 0, xSize, ySize);
    }

}
