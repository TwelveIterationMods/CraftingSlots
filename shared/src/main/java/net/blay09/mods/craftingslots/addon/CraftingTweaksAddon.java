package net.blay09.mods.craftingslots.addon;

import net.blay09.mods.craftingslots.CraftingSlots;
import net.blay09.mods.craftingslots.container.InventoryCraftingMenu;
import net.blay09.mods.craftingslots.container.PortableCraftingMenu;
import net.blay09.mods.craftingtweaks.api.*;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;

public class CraftingTweaksAddon implements CraftingGridProvider {

    private static final GridClearHandler<AbstractContainerMenu> inventoryCraftingClearHandler = (grid, player, menu, forced) -> {
        int start = grid.getGridStartSlot(player, menu);
        int size = grid.getGridSize(player, menu);
        for (int i = start; i < start + size; i++) {
            menu.quickMoveStack(player, i);
            if (forced && menu.slots.get(i).hasItem()) {
                player.drop(menu.slots.get(i).getItem(), false);
                menu.slots.get(i).set(ItemStack.EMPTY);
            }
        }
    };

    public CraftingTweaksAddon() {
        CraftingTweaksAPI.registerCraftingGridProvider(this);
    }

    @Override
    public String getModId() {
        return CraftingSlots.MOD_ID;
    }

    @Override
    public boolean requiresServerSide() {
        return false;
    }

    @Override
    public boolean handles(AbstractContainerMenu menu) {
        return menu instanceof PortableCraftingMenu || menu instanceof InventoryCraftingMenu;
    }

    @Override
    public void buildCraftingGrids(CraftingGridBuilder builder, AbstractContainerMenu menu) {
        if (menu instanceof PortableCraftingMenu) {
            builder.addGrid(1, 9);
        } else if (menu instanceof InventoryCraftingMenu) {
            builder.addGrid(1, 9)
                    .clearHandler(inventoryCraftingClearHandler)
                    .setButtonPosition(TweakType.Rotate, 119, 2)
                    .setButtonPosition(TweakType.Balance, 137, 2)
                    .setButtonPosition(TweakType.Clear, 155, 2);
        }
    }
}
