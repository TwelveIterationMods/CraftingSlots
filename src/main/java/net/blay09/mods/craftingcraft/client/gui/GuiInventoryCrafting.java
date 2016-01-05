package net.blay09.mods.craftingcraft.client.gui;

import net.blay09.mods.craftingcraft.CraftingCraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GuiInventoryCrafting extends GuiContainer {

    private final ResourceLocation texture = new ResourceLocation(CraftingCraft.MOD_ID, "textures/gui/portableCraftingTable.png");

    public GuiInventoryCrafting(Container container) {
        super(container);
        xSize = 218;
        ySize = 102;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        fontRendererObj.drawString(I18n.format(CraftingCraft.MOD_ID + ":container.inventoryCraftingTable"), 8, ySize - 96 + 1, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float delta, int mouseX, int mouseY) {
        GL11.glColor4f(1f, 1f, 1f, 1f);
        mc.getTextureManager().bindTexture(texture);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
    }

}
