package net.blay09.mods.craftingcraft.container;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.CraftResultInventory;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.CraftingResultSlot;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class InventoryCraftingContainer extends CraftingContainer {

    public static final INamedContainerProvider provider = new INamedContainerProvider() {
        @Override
        public ITextComponent getDisplayName() {
            return new TranslationTextComponent("container.craftingcraft.inventory_crafting");
        }

        @Override
        public Container createMenu(int windowId, PlayerInventory playerInventory, PlayerEntity playerEntity) {
            return new InventoryCraftingContainer(windowId, playerInventory);
        }
    };

    private final CraftResultInventory craftResult = new CraftResultInventory();

    private CraftingInventory craftMatrix;

    public InventoryCraftingContainer(int windowId, PlayerInventory playerInventory) {
        super(ModContainers.inventoryCrafting, windowId, playerInventory);
        IInventory inventoryPlayer = new InventoryCraftingWrapper(playerInventory, this);
        craftMatrix = new InventoryIntegratedCrafting(this, playerInventory);

        addSlot(new CraftingResultSlot(playerInventory.player, craftMatrix, craftResult, 0, 193, 38));

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

        onCraftMatrixChanged(craftMatrix);
    }

    @Override
    public void onContainerClosed(PlayerEntity playerIn) {
        super.onContainerClosed(playerIn);
        clearContainer(playerIn, playerIn.world, craftMatrix);
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return true;
    }

    @Override
    public ItemStack transferStackInSlot(PlayerEntity player, int slotIndex) {
        final int CRAFTING_GRID_START = 1;
        final int CRAFTING_GRID_END = 11;
        final int CRAFTING_RESULT_SLOT = 0;
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot = inventorySlots.get(slotIndex);
        if (slot != null && slot.getHasStack()) {
            ItemStack slotStack = slot.getStack();
            itemStack = slotStack.copy();
            if (slotIndex == CRAFTING_RESULT_SLOT) {
                slotStack.getItem().onCreated(slotStack, player.world, player);
                if (!this.mergeItemStack(slotStack, CRAFTING_GRID_END, inventorySlots.size() - 1, true)) {
                    return ItemStack.EMPTY;
                }
                slot.onSlotChange(slotStack, itemStack);
            } else if (slotIndex < CRAFTING_GRID_END) {
                if (!this.mergeItemStack(slotStack, CRAFTING_GRID_END, inventorySlots.size(), true)) {
                    return ItemStack.EMPTY;
                }
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

    @Override
    protected CraftResultInventory getCraftResult() {
        return craftResult;
    }

}
