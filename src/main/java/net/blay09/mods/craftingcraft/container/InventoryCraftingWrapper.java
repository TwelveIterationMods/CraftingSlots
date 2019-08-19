package net.blay09.mods.craftingcraft.container;

import net.minecraft.inventory.CraftingInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;

public class InventoryCraftingWrapper extends CraftingInventory {

    private final IInventory inventory;
    private final Container eventHandler;

    public InventoryCraftingWrapper(IInventory inventory, Container eventHandler) {
        super(eventHandler, 3, 3);
        this.inventory = inventory;
        this.eventHandler = eventHandler;
    }

    @Override
    public int getSizeInventory() {
        return inventory.getSizeInventory();
    }

    @Override
    public ItemStack getStackInSlot(int i) {
        return inventory.getStackInSlot(i);
    }

    @Override
    public ItemStack decrStackSize(int i, int count) {
        ItemStack itemStack = inventory.decrStackSize(i, count);
        eventHandler.onCraftMatrixChanged(this);
        return itemStack;
    }

    @Override
    public void setInventorySlotContents(int i, ItemStack itemStack) {
        inventory.setInventorySlotContents(i, itemStack);
        eventHandler.onCraftMatrixChanged(this);
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        return inventory.removeStackFromSlot(index);
    }

    @Override
    public int getInventoryStackLimit() {
        return inventory.getInventoryStackLimit();
    }

    @Override
    public void markDirty() {
        inventory.markDirty();
    }

    @Override
    public boolean isItemValidForSlot(int i, ItemStack itemStack) {
        return inventory.isItemValidForSlot(i, itemStack);
    }

    @Override
    public void clear() {
        inventory.clear();
    }

}
