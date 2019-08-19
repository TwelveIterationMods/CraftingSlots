package net.blay09.mods.craftingcraft.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class PortableCraftingScreen extends ContainerScreen {

    private static final ResourceLocation texture = new ResourceLocation("textures/gui/container/crafting_table.png");

    public PortableCraftingScreen(Container container) {
        super(container);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        renderHoveredToolTip(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        fontRenderer.drawString(I18n.format("container.crafting"), 28, 6, 4210752);
        fontRenderer.drawString(I18n.format("container.inventory"), 8, ySize - 96 + 2, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float delta, int mouseX, int mouseY) {
        GL11.glColor4f(1f, 1f, 1f, 1f);
        mc.getTextureManager().bindTexture(texture);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
    }

}
