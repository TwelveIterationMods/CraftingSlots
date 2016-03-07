package net.blay09.mods.craftingcraft.addon;

import net.blay09.mods.craftingcraft.container.ContainerInventoryCrafting;
import net.blay09.mods.craftingtweaks.api.CraftingTweaksAPI;
import net.blay09.mods.craftingtweaks.api.DefaultProviderV2;
import net.blay09.mods.craftingtweaks.api.TweakProvider;
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
    public int getCraftingGridStart(EntityPlayer entityPlayer, Container container, int i) {
        return 0;
    }

    @Override
    public int getCraftingGridSize(EntityPlayer entityPlayer, Container container, int i) {
        return 9;
    }

    @Override
    public void clearGrid(EntityPlayer entityPlayer, Container container, int id, boolean forced) {
        IInventory craftMatrix = ((ContainerInventoryCrafting) container).getCraftMatrix();
        for(int i = 0; i < 9; i++) {
            ItemStack itemStack = craftMatrix.getStackInSlot(i);
            if(itemStack != null) {
                container.transferStackInSlot(entityPlayer, i);
                itemStack = craftMatrix.getStackInSlot(i);
                if(forced && itemStack != null) {
                    entityPlayer.dropPlayerItemWithRandomChoice(itemStack, false);
                    craftMatrix.setInventorySlotContents(i, null);
                }
            }
        }
    }

    @Override
    public void rotateGrid(EntityPlayer entityPlayer, Container container, int id, boolean counterClockwise) {
        defaultProvider.rotateGrid(this, id, entityPlayer, container, counterClockwise);
    }

    @Override
    public void balanceGrid(EntityPlayer entityPlayer, Container container, int id) {
        defaultProvider.balanceGrid(this, id, entityPlayer, container);
    }

    @Override
    public void spreadGrid(EntityPlayer entityPlayer, Container container, int id) {
        defaultProvider.spreadGrid(this, id, entityPlayer, container);
    }

    @Override
    public boolean canTransferFrom(EntityPlayer entityPlayer, Container container, int id, Slot slot) {
        return defaultProvider.canTransferFrom(entityPlayer, container, slot);
    }

    @Override
    public boolean transferIntoGrid(EntityPlayer entityPlayer, Container container, int id, Slot slot) {
        return defaultProvider.transferIntoGrid(this, id, entityPlayer, container, slot);
    }

    @Override
    public ItemStack putIntoGrid(EntityPlayer entityPlayer, Container container, int id, ItemStack itemStack, int slotNumber) {
        return defaultProvider.putIntoGrid(this, id, entityPlayer, container, itemStack, slotNumber);
    }

    @Override
    public IInventory getCraftMatrix(EntityPlayer entityPlayer, Container container, int id) {
        return ((Slot) container.inventorySlots.get(getCraftingGridStart(entityPlayer, container, id))).inventory;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void initGui(GuiContainer guiContainer, List list) {
        list.add(CraftingTweaksAPI.createRotateButton(0, guiContainer.width / 2 + 10, guiContainer.height / 2 - 49));
        list.add(CraftingTweaksAPI.createBalanceButton(0, guiContainer.width / 2 + 28, guiContainer.height / 2 - 49));
        list.add(CraftingTweaksAPI.createClearButton(0, guiContainer.width / 2 + 46, guiContainer.height / 2 - 49));
    }

    @Override
    public String getModId() {
        return "craftingcraft";
    }

}
