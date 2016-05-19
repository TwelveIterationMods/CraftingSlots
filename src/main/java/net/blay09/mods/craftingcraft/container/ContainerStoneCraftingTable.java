package net.blay09.mods.craftingcraft.container;

import net.blay09.mods.craftingcraft.block.TileEntityStoneCraftingTable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;

public class ContainerStoneCraftingTable extends Container {

    private final EntityPlayer entityPlayer;
    private final InventoryCrafting inventoryCrafting;
    private final IInventory craftResult = new InventoryCraftResult();

    public ContainerStoneCraftingTable(EntityPlayer entityPlayer, TileEntityStoneCraftingTable tileEntity) {
        this.entityPlayer = entityPlayer;
        inventoryCrafting = new ItemHandlerCrafting(tileEntity.getItemHandler(), this);

        addSlotToContainer(new SlotCrafting(entityPlayer, inventoryCrafting, craftResult, 0, 124, 35));

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                addSlotToContainer(new Slot(inventoryCrafting, j + i * 3, 30 + j * 18, 17 + i * 18));
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                addSlotToContainer(new Slot(entityPlayer.inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
        for (int i = 0; i < 9; i++) {
            addSlotToContainer(new Slot(entityPlayer.inventory, i, 8 + i * 18, 142));
        }

        onCraftMatrixChanged(inventoryCrafting);
    }

    @Override
    public void onCraftMatrixChanged(IInventory inventory) {
        craftResult.setInventorySlotContents(0, CraftingManager.getInstance().findMatchingRecipe(inventoryCrafting, entityPlayer.worldObj));
    }

    @Override
    public boolean canInteractWith(EntityPlayer entityPlayer) {
        return true;
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer entityPlayer, int i) {
        ItemStack itemStack = null;
        Slot slot = this.inventorySlots.get(i);

        if (slot != null && slot.getHasStack()) {
            ItemStack slotStack = slot.getStack();
            //noinspection ConstantConditions
            itemStack = slotStack.copy();

            if (i == 0) {
                if (!this.mergeItemStack(slotStack, 10, 46, true)) {
                    return null;
                }

                slot.onSlotChange(slotStack, itemStack);
            } else if (i >= 10 && i < 37) {
                if (!this.mergeItemStack(slotStack, 37, 46, false)) {
                    return null;
                }
            } else if (i >= 37 && i < 46) {
                if (!this.mergeItemStack(slotStack, 10, 37, false)) {
                    return null;
                }
            } else if (!this.mergeItemStack(slotStack, 10, 46, false)) {
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
    public boolean canMergeSlot(ItemStack itemStack, Slot slot) {
        return slot.inventory != craftResult && super.canMergeSlot(itemStack, slot);
    }

}
