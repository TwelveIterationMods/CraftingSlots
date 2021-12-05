package net.blay09.mods.craftingcraft.container;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;

public class InventoryCraftingContainer extends CraftingContainer {

    private final AbstractContainerMenu menu;
    private final Inventory playerInventory;

    public InventoryCraftingContainer(AbstractContainerMenu menu, Inventory playerInventory) {
        super(menu, 3, 3);
        this.menu = menu;
        this.playerInventory = playerInventory;
    }

    @Override
    public ItemStack removeItemNoUpdate(int i) {
        return playerInventory.removeItemNoUpdate(getInventorySlot(i));
    }

    @Override
    public ItemStack getItem(int i) {
        return playerInventory.getItem(getInventorySlot(i));
    }

    @Override
    public void setItem(int i, ItemStack itemStack) {
        playerInventory.setItem(getInventorySlot(i), itemStack);
        menu.slotsChanged(this);
    }

    @Override
    public ItemStack removeItem(int i, int count) {
        ItemStack oldStack = playerInventory.getItem(getInventorySlot(i));
        ItemStack itemStack = oldStack;
        if (!itemStack.isEmpty()) {
            if (itemStack.getCount() <= count) {
                playerInventory.setItem(getInventorySlot(i), ItemStack.EMPTY);
                menu.slotsChanged(this);
                return itemStack;
            } else {
                itemStack = itemStack.split(count);
                if (oldStack.isEmpty()) {
                    playerInventory.setItem(getInventorySlot(i), ItemStack.EMPTY);
                }
                menu.slotsChanged(this);
                return itemStack;
            }
        } else {
            return ItemStack.EMPTY;
        }
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
