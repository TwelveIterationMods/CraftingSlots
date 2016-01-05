package net.blay09.mods.craftingcraft.addon;

import net.blay09.mods.craftingcraft.container.ContainerInventoryCrafting;
import net.blay09.mods.craftingtweaks.api.CraftingTweaksAPI;
import net.blay09.mods.craftingtweaks.api.DefaultProvider;
import net.blay09.mods.craftingtweaks.api.TweakProvider;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

import java.util.List;

public class InventoryCraftingTweakProvider implements TweakProvider {

    private DefaultProvider defaultProvider = CraftingTweaksAPI.createDefaultProvider();

    @Override
    public boolean load() {
        return true;
    }

    @Override
    public void clearGrid(EntityPlayer entityPlayer, Container container, int id) {
        IInventory craftMatrix = ((ContainerInventoryCrafting) container).getCraftMatrix();
        for(int i = 0; i < 9; i++) {
            ItemStack itemStack = craftMatrix.getStackInSlot(i);
            if(itemStack != null) {
                container.transferStackInSlot(entityPlayer, i);
            }
        }
    }

    @Override
    public void rotateGrid(EntityPlayer entityPlayer, Container container, int id) {
        defaultProvider.rotateGrid(entityPlayer, container, ((ContainerInventoryCrafting) container).getCraftMatrix());
    }

    @Override
    public void balanceGrid(EntityPlayer entityPlayer, Container container, int id) {
        defaultProvider.balanceGrid(entityPlayer, container, ((ContainerInventoryCrafting) container).getCraftMatrix());
    }

    @Override
    public ItemStack transferIntoGrid(EntityPlayer entityPlayer, Container container, int i, ItemStack itemStack) {
        return defaultProvider.transferIntoGrid(entityPlayer, container, ((ContainerInventoryCrafting) container).getCraftMatrix(), itemStack);
    }

    @Override
    public ItemStack putIntoGrid(EntityPlayer entityPlayer, Container container, int i, ItemStack itemStack, int slotNumber) {
        return defaultProvider.putIntoGrid(entityPlayer, container, ((ContainerInventoryCrafting) container).getCraftMatrix(), itemStack, slotNumber);
    }

    @Override
    public IInventory getCraftMatrix(EntityPlayer entityPlayer, Container container, int i) {
        return ((ContainerInventoryCrafting) container).getCraftMatrix();
    }

    @Override
    public void initGui(GuiContainer guiContainer, List<GuiButton> list) {
        list.add(CraftingTweaksAPI.createRotateButton(0, guiContainer.width / 2 + 10, guiContainer.height / 2 - 49));
        list.add(CraftingTweaksAPI.createBalanceButton(0, guiContainer.width / 2 + 28, guiContainer.height / 2 - 49));
        list.add(CraftingTweaksAPI.createClearButton(0, guiContainer.width / 2 + 46, guiContainer.height / 2 - 49));
    }

    @Override
    public boolean areHotkeysEnabled(EntityPlayer entityPlayer, Container container) {
        return true;
    }

    @Override
    public String getModId() {
        return "craftingcraft";
    }

}
