package net.blay09.mods.craftingcraft.container;

import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;

public class ItemHandlerCrafting extends InventoryCrafting {

    private final ItemStackHandler itemHandler;
    private final Container eventHandler;

    public ItemHandlerCrafting(ItemStackHandler itemHandler, Container eventHandler) {
        super(eventHandler, 3, 3);
        this.itemHandler = itemHandler;
        this.eventHandler = eventHandler;
    }

    @Override
    public int getSizeInventory() {
        return itemHandler.getSlots();
    }

    @Override
    public ItemStack getStackInSlot(int i) {
        if(i < 0 || i >= itemHandler.getSlots()) {
            return null; // For recipes doing it wrong, just return null instead of crashing in ItemStackHandler.validateSlotIndex
        }
        return itemHandler.getStackInSlot(i);
    }

    @Override
    public ItemStack decrStackSize(int i, int count) {
        ItemStack itemStack = itemHandler.extractItem(i, count, false);
        eventHandler.onCraftMatrixChanged(this);
        return itemStack;
    }

    @Override
    public void setInventorySlotContents(int i, @Nullable ItemStack itemStack) {
        itemHandler.setStackInSlot(i, itemStack);
        eventHandler.onCraftMatrixChanged(this);
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        ItemStack itemStack = itemHandler.getStackInSlot(index);
        itemHandler.setStackInSlot(index, null);
        return itemStack;
    }

    @Override
    public void clear() {
        for(int i = 0; i < itemHandler.getSlots(); i++) {
            itemHandler.setStackInSlot(i, null);
        }
    }

}
