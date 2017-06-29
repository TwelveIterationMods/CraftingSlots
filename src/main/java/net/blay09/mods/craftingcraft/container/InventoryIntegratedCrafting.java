package net.blay09.mods.craftingcraft.container;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;

public class InventoryIntegratedCrafting extends InventoryCrafting {

    private final Container eventHandler;
    private final InventoryPlayer inventoryPlayer;

    public InventoryIntegratedCrafting(Container eventHandler, InventoryPlayer inventoryPlayer) {
        super(eventHandler, 3, 3);
        this.eventHandler = eventHandler;
        this.inventoryPlayer = inventoryPlayer;
    }

    @Override
    public ItemStack getStackInSlot(int i) {
        return inventoryPlayer.getStackInSlot(getInventorySlot(i));
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        return inventoryPlayer.removeStackFromSlot(getInventorySlot(index));
    }

    @Override
    public ItemStack decrStackSize(int i, int count) {
        ItemStack oldStack = inventoryPlayer.getStackInSlot(getInventorySlot(i));
        ItemStack itemStack = oldStack;
        if (!itemStack.isEmpty()) {
            if (itemStack.getCount() <= count) {
                inventoryPlayer.setInventorySlotContents(getInventorySlot(i), ItemStack.EMPTY);
                eventHandler.onCraftMatrixChanged(this);
                return itemStack;
            } else {
                itemStack = itemStack.splitStack(count);
                if (oldStack.isEmpty()) {
                    inventoryPlayer.setInventorySlotContents(getInventorySlot(i), ItemStack.EMPTY);
                }
                eventHandler.onCraftMatrixChanged(this);
                return itemStack;
            }
        } else {
            return ItemStack.EMPTY;
        }
    }

    @Override
    public void setInventorySlotContents(int i, ItemStack itemStack) {
        inventoryPlayer.setInventorySlotContents(getInventorySlot(i), itemStack);
        eventHandler.onCraftMatrixChanged(this);
    }

    public int getInventorySlot(int i) {
        if (i < 3) {
            return 6 + i + 9;
        } else if (i < 6) {
            return 15 + i - 3 + 9;
        } else if (i < 9) {
            return 24 + i - 6 + 9;
        }
        return 0;
    }
}
