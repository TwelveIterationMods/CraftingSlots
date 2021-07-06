package net.blay09.mods.craftingcraft.container;

import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.Set;

public class PortableCraftingContainer extends CraftingContainer {

    private final Container container;
    private final AbstractContainerMenu menu;

    public PortableCraftingContainer(Container container, AbstractContainerMenu menu) {
        super(menu, 3, 3);
        this.container = container;
        this.menu = menu;
    }

    @Override
    public int getContainerSize() {
        return container.getContainerSize();
    }

    @Override
    public ItemStack getItem(int i) {
        return container.getItem(i);
    }

    @Override
    public ItemStack removeItemNoUpdate(int i) {
        return container.removeItemNoUpdate(i);
    }

    @Override
    public ItemStack removeItem(int i, int count) {
        ItemStack itemStack = container.removeItem(i, count);
        menu.slotsChanged(this);
        return itemStack;
    }

    @Override
    public void setItem(int i, ItemStack itemStack) {
        container.setItem(i, itemStack);
        menu.slotsChanged(this);
    }

    @Override
    public void setChanged() {
        container.setChanged();
    }

    @Override
    public void clearContent() {
        container.clearContent();
    }

    @Override
    public boolean canPlaceItem(int i, ItemStack itemStack) {
        return container.canPlaceItem(i, itemStack);
    }

    @Override
    public boolean isEmpty() {
        return container.isEmpty();
    }

    @Override
    public boolean stillValid(Player player) {
        return container.stillValid(player);
    }

    @Override
    public int getMaxStackSize() {
        return container.getMaxStackSize();
    }

    @Override
    public int countItem(Item item) {
        return container.countItem(item);
    }

    @Override
    public void startOpen(Player player) {
        container.startOpen(player);
    }

    @Override
    public void stopOpen(Player player) {
        container.stopOpen(player);
    }

    @Override
    public boolean hasAnyOf(Set<Item> set) {
        return container.hasAnyOf(set);
    }
}
