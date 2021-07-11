package net.blay09.mods.craftingcraft.container;

import net.blay09.mods.balm.menu.BalmMenuProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.Container;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;

public class InventoryCraftingMenu extends CustomCraftingMenu {

    public static final MenuProvider provider = new BalmMenuProvider() {
        @Override
        public Component getDisplayName() {
            return new TranslatableComponent("container.craftingcraft.inventory_crafting");
        }

        @Override
        public AbstractContainerMenu createMenu(int windowId, Inventory playerInventory, Player playerEntity) {
            return new InventoryCraftingMenu(windowId, playerInventory);
        }
    };

    private final ResultContainer craftResult = new ResultContainer();
    private final CraftingContainer craftMatrix;

    public InventoryCraftingMenu(int windowId, Inventory playerInventory) {
        super(ModMenus.inventoryCrafting.get(), windowId, playerInventory);
        Container portableCraftingInventory = new PortableCraftingContainer(playerInventory, this);
        craftMatrix = new InventoryCraftingContainer(this, playerInventory);

        addSlot(new ResultSlot(playerInventory.player, craftMatrix, craftResult, 0, 193, 38));

        // Crafting Matrix
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                addSlot(new Slot(portableCraftingInventory, (j + 6) + i * 9 + 9, 119 + j * 18, 20 + i * 18));
            }
        }

        // Inventory
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 6; j++) {
                addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 20 + i * 18));
            }
        }

        // Hotbar
        for (int i = 0; i < 9; i++) {
            int x = 8 + i * 18;
            if (i >= 6) {
                x += 3;
            }
            addSlot(new Slot(playerInventory, i, x, 78));
        }

        slotsChanged(craftMatrix);
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }

    @Override
    public ItemStack quickMoveStack(Player player, int slotIndex) {
        final int CRAFTING_GRID_START = 1;
        final int CRAFTING_GRID_END = 10;
        final int CRAFTING_RESULT_SLOT = 0;
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot = slots.get(slotIndex);
        if (slot != null && slot.hasItem()) {
            ItemStack slotStack = slot.getItem();
            itemStack = slotStack.copy();
            if (slotIndex == CRAFTING_RESULT_SLOT) {
                slotStack.getItem().onCraftedBy(slotStack, player.level, player);
                if (!this.moveItemStackTo(slotStack, CRAFTING_GRID_END, slots.size() - 1, true)) {
                    return ItemStack.EMPTY;
                }
                slot.onQuickCraft(slotStack, itemStack);
            } else if (slotIndex < CRAFTING_GRID_END) {
                if (!this.moveItemStackTo(slotStack, CRAFTING_GRID_END, slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(slotStack, CRAFTING_GRID_START, CRAFTING_GRID_END, false)) {
                return ItemStack.EMPTY;
            }

            if (slotStack.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (slotStack.getCount() == itemStack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(player, slotStack);
            if (slotIndex == CRAFTING_RESULT_SLOT) {
                player.drop(slotStack, false);
            }
        }
        return itemStack;
    }

    @Override
    public boolean canTakeItemForPickAll(ItemStack itemStack, Slot slot) {
        return slot.container != craftResult && super.canTakeItemForPickAll(itemStack, slot);
    }

    @Override
    public CraftingContainer getCraftMatrix() {
        return craftMatrix;
    }

    @Override
    protected ResultContainer getCraftResult() {
        return craftResult;
    }

}
