package net.blay09.mods.craftingcraft.client.gui;

import net.blay09.mods.craftingcraft.container.ContainerWithCraftMatrix;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiButtonImage;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.recipebook.GuiRecipeBook;
import net.minecraft.client.gui.recipebook.IRecipeShownListener;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.util.ResourceLocation;

import java.io.IOException;

/**
 * Tried to add support for Vanilla's recipe book button, but their system is badly coded and it's not worth fixing it really
 */
public abstract class GuiCraftingBase extends GuiContainer implements IRecipeShownListener {

	private static final ResourceLocation CRAFTING_TABLE_GUI_TEXTURES = new ResourceLocation("textures/gui/container/crafting_table.png");

	private final GuiRecipeBook recipeBookGui = new GuiRecipeBook();
	private GuiButtonImage recipeButton;
	private boolean widthTooNarrow;

	public GuiCraftingBase(Container container) {
		super(container);
	}

	@Override
	public void initGui() {
		super.initGui();
		widthTooNarrow = width < 500;
		recipeBookGui.init(width, height, mc, widthTooNarrow, inventorySlots, ((ContainerWithCraftMatrix) inventorySlots).getCraftMatrix());
		guiLeft = recipeBookGui.updateScreenPosition(widthTooNarrow, width, xSize);
		recipeButton = new GuiButtonImage(10, guiLeft + xSize - 25, guiTop + 5, 20, 18, 0, 168, 19, CRAFTING_TABLE_GUI_TEXTURES);
		buttonList.add(recipeButton);
	}

	@Override
	public void updateScreen() {
		super.updateScreen();
		recipeBookGui.tick();
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		drawDefaultBackground();

		if (recipeBookGui.isVisible() && widthTooNarrow) {
			drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY);
			recipeBookGui.render(mouseX, mouseY, partialTicks);
		} else {
			recipeBookGui.render(mouseX, mouseY, partialTicks);
			super.drawScreen(mouseX, mouseY, partialTicks);
			recipeBookGui.renderGhostRecipe(guiLeft, guiTop, true, partialTicks);
		}

		renderHoveredToolTip(mouseX, mouseY);

		recipeBookGui.renderTooltip(guiLeft, guiTop, mouseX, mouseY);
	}

	@Override
	protected boolean isPointInRegion(int rectX, int rectY, int rectWidth, int rectHeight, int pointX, int pointY) {
		return (!this.widthTooNarrow || !this.recipeBookGui.isVisible()) && super.isPointInRegion(rectX, rectY, rectWidth, rectHeight, pointX, pointY);
	}

	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		if (!this.recipeBookGui.mouseClicked(mouseX, mouseY, mouseButton)) {
			if (!this.widthTooNarrow || !this.recipeBookGui.isVisible()) {
				super.mouseClicked(mouseX, mouseY, mouseButton);
			}
		}
	}

	@Override
	protected boolean hasClickedOutside(int x, int y, int left, int top) {
		boolean flag = x < left || y < top || x >= left + xSize || y >= top + ySize;
		return recipeBookGui.hasClickedOutside(x, y, guiLeft, guiTop, xSize, ySize) && flag;
	}

	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		if (button == recipeButton) {
			recipeBookGui.initVisuals(widthTooNarrow, ((ContainerWithCraftMatrix) inventorySlots).getCraftMatrix());
			recipeBookGui.toggleVisibility();
			guiLeft = recipeBookGui.updateScreenPosition(widthTooNarrow, width, xSize);
			recipeButton.setPosition(guiLeft + xSize - 25, guiTop + 5);
		}
	}

	@Override
	protected void keyTyped(char typedChar, int keyCode) throws IOException {
		if (!recipeBookGui.keyPressed(typedChar, keyCode)) {
			super.keyTyped(typedChar, keyCode);
		}
	}

	@Override
	protected void handleMouseClick(Slot slotIn, int slotId, int mouseButton, ClickType type) {
		super.handleMouseClick(slotIn, slotId, mouseButton, type);
		recipeBookGui.slotClicked(slotIn);
	}

	@Override
	public void recipesUpdated() {
		recipeBookGui.recipesUpdated();
	}

	@Override
	public void onGuiClosed() {
		recipeBookGui.removed();
		super.onGuiClosed();
	}
}
