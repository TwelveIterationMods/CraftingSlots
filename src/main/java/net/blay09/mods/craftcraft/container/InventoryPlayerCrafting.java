package net.blay09.mods.craftcraft.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class InventoryPlayerCrafting implements IInventory {

    private final InventoryPlayer inventoryPlayer;
    private final Container eventHandler;

    public InventoryPlayerCrafting(InventoryPlayer inventoryPlayer, Container eventHandler) {
        this.inventoryPlayer = inventoryPlayer;
        this.eventHandler = eventHandler;
    }

    @Override
    public int getSizeInventory() {
        return inventoryPlayer.getSizeInventory();
    }

    @Override
    public ItemStack getStackInSlot(int i) {
        return inventoryPlayer.getStackInSlot(i);
    }

    @Override
    public ItemStack decrStackSize(int i, int count) {
        ItemStack itemStack = inventoryPlayer.decrStackSize(i, count);
        eventHandler.onCraftMatrixChanged(this);
        return itemStack;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int i) {
        return inventoryPlayer.getStackInSlotOnClosing(i);
    }

    @Override
    public void setInventorySlotContents(int i, ItemStack itemStack) {
        inventoryPlayer.setInventorySlotContents(i, itemStack);
        eventHandler.onCraftMatrixChanged(this);
    }

    @Override
    public String getInventoryName() {
        return inventoryPlayer.getInventoryName();
    }

    @Override
    public boolean hasCustomInventoryName() {
        return inventoryPlayer.hasCustomInventoryName();
    }

    @Override
    public int getInventoryStackLimit() {
        return inventoryPlayer.getInventoryStackLimit();
    }

    @Override
    public void markDirty() {
        inventoryPlayer.markDirty();
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer entityPlayer) {
        return inventoryPlayer.isUseableByPlayer(entityPlayer);
    }

    @Override
    public void openInventory() {
        inventoryPlayer.openInventory();
    }

    @Override
    public void closeInventory() {
        inventoryPlayer.closeInventory();
    }

    @Override
    public boolean isItemValidForSlot(int i, ItemStack itemStack) {
        return inventoryPlayer.isItemValidForSlot(i, itemStack);
    }

}
