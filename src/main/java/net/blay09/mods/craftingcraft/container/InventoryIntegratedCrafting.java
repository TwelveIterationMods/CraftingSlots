package net.blay09.mods.craftingcraft.container;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;

public class InventoryIntegratedCrafting extends CraftingInventory {

    private final Container eventHandler;
    private final PlayerInventory playerInventory;

    public InventoryIntegratedCrafting(Container eventHandler, PlayerInventory playerInventory) {
        super(eventHandler, 3, 3);
        this.eventHandler = eventHandler;
        this.playerInventory = playerInventory;
    }

    @Override
    public ItemStack getStackInSlot(int i) {
        return playerInventory.getStackInSlot(getInventorySlot(i));
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        return playerInventory.removeStackFromSlot(getInventorySlot(index));
    }

    @Override
    public ItemStack decrStackSize(int i, int count) {
        ItemStack oldStack = playerInventory.getStackInSlot(getInventorySlot(i));
        ItemStack itemStack = oldStack;
        if (!itemStack.isEmpty()) {
            if (itemStack.getCount() <= count) {
                playerInventory.setInventorySlotContents(getInventorySlot(i), ItemStack.EMPTY);
                eventHandler.onCraftMatrixChanged(this);
                return itemStack;
            } else {
                itemStack = itemStack.split(count);
                if (oldStack.isEmpty()) {
                    playerInventory.setInventorySlotContents(getInventorySlot(i), ItemStack.EMPTY);
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
        playerInventory.setInventorySlotContents(getInventorySlot(i), itemStack);
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
