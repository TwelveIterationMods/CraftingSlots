package net.blay09.mods.craftingcraft.addon;

import codechicken.nei.recipe.DefaultOverlayHandler;
import net.blay09.mods.craftingcraft.container.InventoryCraftingWrapper;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Slot;

public class InventoryCraftingOverlayHandler extends DefaultOverlayHandler {

    public InventoryCraftingOverlayHandler(int offsetX, int offsetY) {
        super(offsetX, offsetY);
    }

    @Override
    public boolean canMoveFrom(Slot slot, GuiContainer gui) {
        return slot.inventory instanceof InventoryCraftingWrapper && slot.slotNumber >= 9;
    }
}
