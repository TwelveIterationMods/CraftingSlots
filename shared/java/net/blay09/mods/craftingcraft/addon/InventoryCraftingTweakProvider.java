package net.blay09.mods.craftingcraft.addon;

import net.blay09.mods.craftingcraft.container.InventoryCraftingMenu;
import net.blay09.mods.craftingtweaks.api.CraftingTweaksAPI;
import net.blay09.mods.craftingtweaks.api.DefaultProviderV2;
import net.blay09.mods.craftingtweaks.api.TweakProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

import java.util.function.Consumer;

public class InventoryCraftingTweakProvider implements TweakProvider<InventoryCraftingMenu> {

    private DefaultProviderV2 defaultProvider = CraftingTweaksAPI.createDefaultProviderV2();

    @Override
    public boolean load() {
        return true;
    }

    @Override
    public boolean requiresServerSide() {
        return false;
    }

    @Override
    public int getCraftingGridStart(Player player, InventoryCraftingMenu menu, int id) {
        return 1;
    }

    @Override
    public int getCraftingGridSize(Player player, InventoryCraftingMenu menu, int id) {
        return 9;
    }

    @Override
    public void clearGrid(Player player, InventoryCraftingMenu menu, int id, boolean forced) {
        int start = getCraftingGridStart(player, menu, id);
        int size = getCraftingGridSize(player, menu, id);
        for (int i = start; i < start + size; i++) {
            menu.quickMoveStack(player, i);
            if (forced && menu.slots.get(i).hasItem()) {
                player.drop(menu.slots.get(i).getItem(), false);
                menu.slots.get(i).set(ItemStack.EMPTY);
            }
        }
    }

    @Override
    public void rotateGrid(Player player, InventoryCraftingMenu menu, int id, boolean counterClockwise) {
        defaultProvider.rotateGrid(this, id, player, menu, counterClockwise);
    }

    @Override
    public void balanceGrid(Player player, InventoryCraftingMenu menu, int id) {
        defaultProvider.balanceGrid(this, id, player, menu);
    }

    @Override
    public void spreadGrid(Player player, InventoryCraftingMenu menu, int id) {
        defaultProvider.spreadGrid(this, id, player, menu);
    }

    @Override
    public boolean canTransferFrom(Player player, InventoryCraftingMenu menu, int i, Slot slot) {
        return defaultProvider.canTransferFrom(player, menu, slot);
    }

    @Override
    public boolean transferIntoGrid(Player player, InventoryCraftingMenu menu, int id, Slot slot) {
        return defaultProvider.transferIntoGrid(this, id, player, menu, slot);
    }

    @Override
    public ItemStack putIntoGrid(Player player, InventoryCraftingMenu menu, int id, ItemStack itemStack, int index) {
        return defaultProvider.putIntoGrid(this, id, player, menu, itemStack, index);
    }

    @Override
    public Container getCraftMatrix(Player player, InventoryCraftingMenu menu, int id) {
        return menu.slots.get(getCraftingGridStart(player, menu, id)).container;
    }

    @Override
    public void initGui(AbstractContainerScreen<InventoryCraftingMenu> containerScreen, Consumer<AbstractWidget> addWidgetFunc) {
        int craftingGridStart = getCraftingGridStart(Minecraft.getInstance().player, containerScreen.getMenu(), 0);
        Slot firstSlot = containerScreen.getMenu().getSlot(craftingGridStart);
        addWidgetFunc.accept(CraftingTweaksAPI.createRotateButtonRelative(0, containerScreen, firstSlot.x, firstSlot.y - 18));
        addWidgetFunc.accept(CraftingTweaksAPI.createBalanceButtonRelative(0, containerScreen, firstSlot.x + 18, firstSlot.y - 18));
        addWidgetFunc.accept(CraftingTweaksAPI.createClearButtonRelative(0, containerScreen, firstSlot.x + 36, firstSlot.y - 18));
    }

    @Override
    public String getModId() {
        return "craftingcraft";
    }

}
