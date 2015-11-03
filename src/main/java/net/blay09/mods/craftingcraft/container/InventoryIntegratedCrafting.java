package net.blay09.mods.craftingcraft.container;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;

public class InventoryIntegratedCrafting extends InventoryCrafting {

    private final InventoryPlayer inventoryPlayer;

    public InventoryIntegratedCrafting(Container container, InventoryPlayer inventoryPlayer) {
        super(container, 3, 3);
        this.inventoryPlayer = inventoryPlayer;
    }

    @Override
    public ItemStack getStackInSlot(int i) {
        return inventoryPlayer.getStackInSlot(getInventorySlot(i));
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int i) {
        return getStackInSlot(i);
    }

    @Override
    public ItemStack decrStackSize(int i, int count) {
        ItemStack oldStack = inventoryPlayer.getStackInSlot(getInventorySlot(i));
        ItemStack itemStack = oldStack;
        if (itemStack != null) {
            if (itemStack.stackSize <= count) {
                inventoryPlayer.setInventorySlotContents(getInventorySlot(i), null);
                return itemStack;
            } else {
                itemStack = itemStack.splitStack(count);
                if (oldStack.stackSize == 0) {
                    inventoryPlayer.setInventorySlotContents(getInventorySlot(i), null);
                }
                return itemStack;
            }
        } else {
            return null;
        }
    }

    @Override
    public void setInventorySlotContents(int i, ItemStack itemStack) {
        inventoryPlayer.setInventorySlotContents(getInventorySlot(i), itemStack);
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
