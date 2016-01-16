package net.blay09.mods.craftingcraft.addon;

import net.blay09.mods.craftingtweaks.api.CraftingTweaksAPI;
import net.blay09.mods.craftingtweaks.api.DefaultProviderV2;
import net.blay09.mods.craftingtweaks.api.TweakProvider;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import java.util.List;

public class InventoryCraftingTweakProvider implements TweakProvider {

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
    public int getCraftingGridStart(int id) {
        return 0;
    }

    @Override
    public int getCraftingGridSize(int id) {
        return 9;
    }

    @Override
    public void clearGrid(EntityPlayer entityPlayer, Container container, int id) {
        defaultProvider.clearGrid(this, id, entityPlayer, container, false);
    }

    @Override
    public void rotateGrid(EntityPlayer entityPlayer, Container container, int id) {
        defaultProvider.rotateGrid(this, id, entityPlayer, container);
    }

    @Override
    public void balanceGrid(EntityPlayer entityPlayer, Container container, int id) {
        defaultProvider.balanceGrid(this, id, entityPlayer, container);
    }

    @Override
    public boolean canTransferFrom(EntityPlayer entityPlayer, Container container, int i, Slot slot) {
        return defaultProvider.canTransferFrom(entityPlayer, container, slot);
    }

    @Override
    public boolean transferIntoGrid(EntityPlayer entityPlayer, Container container, int id, Slot slot) {
        return defaultProvider.transferIntoGrid(this, id, entityPlayer, container, slot);
    }

    @Override
    public ItemStack putIntoGrid(EntityPlayer entityPlayer, Container container, int id, ItemStack itemStack, int index) {
        return defaultProvider.putIntoGrid(this, id, entityPlayer, container, itemStack, index);
    }

    @Override
    public IInventory getCraftMatrix(EntityPlayer entityPlayer, Container container, int id) {
        return container.inventorySlots.get(getCraftingGridStart(id)).inventory;
    }

    @Override
    public void initGui(GuiContainer guiContainer, List<GuiButton> list) {
        list.add(CraftingTweaksAPI.createRotateButton(0, guiContainer.width / 2 + 10, guiContainer.height / 2 - 49));
        list.add(CraftingTweaksAPI.createBalanceButton(0, guiContainer.width / 2 + 28, guiContainer.height / 2 - 49));
        list.add(CraftingTweaksAPI.createClearButton(0, guiContainer.width / 2 + 46, guiContainer.height / 2 - 49));
    }

    @Override
    public String getModId() {
        return "craftingcraft";
    }

}
