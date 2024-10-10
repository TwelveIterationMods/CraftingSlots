package net.blay09.mods.craftingslots.container;

import net.minecraft.core.NonNullList;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.entity.player.StackedItemContents;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.TransientCraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingInput;

import java.util.Iterator;
import java.util.List;

public class InventoryCraftingContainer extends TransientCraftingContainer {

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
    public boolean isEmpty() {
        for (int i = 0; i < 9; i++) {
            if (!playerInventory.getItem(getInventorySlot(i)).isEmpty()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public List<ItemStack> getItems() {
        final var items = NonNullList.withSize(getContainerSize(), ItemStack.EMPTY);
        for (int i = 0; i < 9; i++) {
            items.set(i, getItem(i));
        }
        return items;
    }

    @Override
    public void fillStackedContents(StackedItemContents stackedContents) {
        for (int i = 0; i < getContainerSize(); i++) {
            final var itemStack = getItem(i);
            stackedContents.accountSimpleStack(itemStack);
        }
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
