package net.blay09.mods.craftcraft.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;

public class ContainerInventoryCrafting extends Container {

    private final EntityPlayer entityPlayer;
    private InventoryCrafting craftMatrix;
    private IInventory craftResult = new InventoryCraftResult();

    public ContainerInventoryCrafting(EntityPlayer entityPlayer) {
        this.entityPlayer = entityPlayer;
        IInventory inventoryPlayer = new InventoryCraftingWrapper(entityPlayer.inventory, this);
        craftMatrix = new InventoryIntegratedCrafting(this, entityPlayer.inventory);

        // Crafting Matrix
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                addSlotToContainer(new Slot(inventoryPlayer, (j + 6) + i * 9 + 9, 119 + j * 18, 20 + i * 18));
            }
        }

        // Inventory
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 6; j++) {
                addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9, 8 + j * 18, 20 + i * 18));
            }
        }

        // Hotbar
        for (int i = 0; i < 9; i++) {
            int x = 8 + i * 18;
            if (i >= 6) {
                x += 3;
            }
            addSlotToContainer(new Slot(inventoryPlayer, i, x, 78));
        }

        addSlotToContainer(new SlotCrafting(entityPlayer, craftMatrix, craftResult, 0, 193, 38));

        onCraftMatrixChanged(craftMatrix);
    }


    @Override
    public boolean canInteractWith(EntityPlayer entityPlayer) {
        return true;
    }

    @Override
    public void onCraftMatrixChanged(IInventory p_75130_1_) {
        craftResult.setInventorySlotContents(0, CraftingManager.getInstance().findMatchingRecipe(craftMatrix, entityPlayer.worldObj));
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slotIndex) {
        ItemStack itemStack = null;
        Slot slot = (Slot) inventorySlots.get(slotIndex);
        if (slot != null && slot.getHasStack()) {
            ItemStack slotStack = slot.getStack();
            itemStack = slotStack.copy();
            if (slotIndex < 9) {
                if (!this.mergeItemStack(slotStack, 9, inventorySlots.size() - 1, true)) {
                    return null;
                }
            } else if (slotIndex == 36) {
                if (!this.mergeItemStack(slotStack, 9, inventorySlots.size() - 1, true)) {
                    return null;
                }
                slot.onSlotChange(slotStack, itemStack);
            } else if (slotIndex >= 27) {
                if (!this.mergeItemStack(slotStack, 9, inventorySlots.size() - 10, true)) {
                    return null;
                }
            } else if (!this.mergeItemStack(slotStack, 27, inventorySlots.size() - 1, false)) {
                return null;
            }

            if (slotStack.stackSize == 0) {
                slot.putStack(null);
            } else {
                slot.onSlotChanged();
            }

            if (slotStack.stackSize == itemStack.stackSize) {
                return null;
            }

            slot.onPickupFromSlot(entityPlayer, slotStack);
        }
        return itemStack;
    }

    @Override
    public boolean func_94530_a(ItemStack itemStack, Slot slot) {
        return slot.inventory != craftResult && super.func_94530_a(itemStack, slot);
    }

    public InventoryCrafting getCraftMatrix() {
        return craftMatrix;
    }
}
