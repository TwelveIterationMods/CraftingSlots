package net.blay09.mods.craftcraft.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;

public class InventoryCraftingWrapper extends InventoryCrafting {

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
    public ItemStack getStackInSlotOnClosing(int i) {
        return inventory.getStackInSlotOnClosing(i);
    }

    @Override
    public void setInventorySlotContents(int i, ItemStack itemStack) {
        inventory.setInventorySlotContents(i, itemStack);
        eventHandler.onCraftMatrixChanged(this);
    }

    @Override
    public String getInventoryName() {
        return inventory.getInventoryName();
    }

    @Override
    public boolean hasCustomInventoryName() {
        return inventory.hasCustomInventoryName();
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
    public boolean isUseableByPlayer(EntityPlayer entityPlayer) {
        return inventory.isUseableByPlayer(entityPlayer);
    }

    @Override
    public void openInventory() {
        inventory.openInventory();
    }

    @Override
    public void closeInventory() {
        inventory.closeInventory();
    }

    @Override
    public boolean isItemValidForSlot(int i, ItemStack itemStack) {
        return inventory.isItemValidForSlot(i, itemStack);
    }

}
