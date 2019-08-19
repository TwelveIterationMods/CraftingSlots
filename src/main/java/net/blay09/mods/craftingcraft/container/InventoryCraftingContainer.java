package net.blay09.mods.craftingcraft.container;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.CraftResultInventory;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.CraftingResultSlot;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;

public class InventoryCraftingContainer extends Container implements ContainerWithCraftMatrix {

    private final PlayerEntity entityPlayer;
    private CraftingInventory craftMatrix;
    private IInventory craftResult = new CraftResultInventory();

    public InventoryCraftingContainer(int windowId, PlayerInventory playerInventory) {
        super(ModContainers.inventoryCrafting, windowId);
        this.entityPlayer = playerInventory.player;
        IInventory inventoryPlayer = new InventoryCraftingWrapper(entityPlayer.inventory, this);
        craftMatrix = new InventoryIntegratedCrafting(this, entityPlayer.inventory);

        // Crafting Matrix
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                addSlot(new Slot(inventoryPlayer, (j + 6) + i * 9 + 9, 119 + j * 18, 20 + i * 18));
            }
        }

        // Inventory
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 6; j++) {
                addSlot(new Slot(inventoryPlayer, j + i * 9 + 9, 8 + j * 18, 20 + i * 18));
            }
        }

        // Hotbar
        for (int i = 0; i < 9; i++) {
            int x = 8 + i * 18;
            if (i >= 6) {
                x += 3;
            }
            addSlot(new Slot(inventoryPlayer, i, x, 78));
        }

        addSlot(new CraftingResultSlot(entityPlayer, craftMatrix, craftResult, 0, 193, 38));

        onCraftMatrixChanged(craftMatrix);
    }

    @Override
    public void onCraftMatrixChanged(IInventory p_75130_1_) {
        craftResult.setInventorySlotContents(0, CraftingManager.findMatchingResult(craftMatrix, entityPlayer.world));
    }

    @Override
    public ItemStack transferStackInSlot(PlayerEntity player, int slotIndex) {
        final int CRAFTING_GRID_START = 0;
        final int CRAFTING_GRID_END = 9;
        final int CRAFTING_RESULT_SLOT = 36;
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot = inventorySlots.get(slotIndex);
        if (slot != null && slot.getHasStack()) {
            ItemStack slotStack = slot.getStack();
            itemStack = slotStack.copy();
            if (slotIndex < CRAFTING_GRID_END) {
                if (!this.mergeItemStack(slotStack, CRAFTING_GRID_END, inventorySlots.size() - 1, true)) {
                    return ItemStack.EMPTY;
                }
            } else if (slotIndex == CRAFTING_RESULT_SLOT) {
                slotStack.getItem().onCreated(slotStack, player.world, player);
                if (!this.mergeItemStack(slotStack, CRAFTING_GRID_END, inventorySlots.size() - 1, true)) {
                    return ItemStack.EMPTY;
                }
                slot.onSlotChange(slotStack, itemStack);
            } else if (!this.mergeItemStack(slotStack, CRAFTING_GRID_START, CRAFTING_GRID_END, false)) {
                return ItemStack.EMPTY;
            }

            if (slotStack.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }

            if (slotStack.getCount() == itemStack.getCount()) {
                return ItemStack.EMPTY;
            }

            ItemStack resultStack = slot.onTake(player, slotStack);
            if (slotIndex == CRAFTING_RESULT_SLOT) {
                player.dropItem(resultStack, false);
            }
        }
        return itemStack;
    }

    @Override
    public boolean canMergeSlot(ItemStack itemStack, Slot slot) {
        return slot.inventory != craftResult && super.canMergeSlot(itemStack, slot);
    }

    @Override
    public CraftingInventory getCraftMatrix() {
        return craftMatrix;
    }
}
