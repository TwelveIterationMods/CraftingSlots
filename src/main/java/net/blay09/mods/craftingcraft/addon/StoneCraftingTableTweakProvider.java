package net.blay09.mods.craftingcraft.addon;

import net.blay09.mods.craftingcraft.container.ContainerStoneCraftingTable;
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

public class StoneCraftingTableTweakProvider implements TweakProvider {

    private DefaultProvider defaultProvider = CraftingTweaksAPI.createDefaultProvider();

    @Override
    public boolean load() {
        return true;
    }

    @Override
    public void clearGrid(EntityPlayer entityPlayer, Container container, int i) {
        defaultProvider.clearGrid(entityPlayer, container, ((ContainerStoneCraftingTable) container).getCraftMatrix());
    }

    @Override
    public void rotateGrid(EntityPlayer entityPlayer, Container container, int i) {
        defaultProvider.rotateGrid(entityPlayer, container, ((ContainerStoneCraftingTable) container).getCraftMatrix());
    }

    @Override
    public void balanceGrid(EntityPlayer entityPlayer, Container container, int i) {
        defaultProvider.balanceGrid(entityPlayer, container, ((ContainerStoneCraftingTable) container).getCraftMatrix());
    }

    @Override
    public ItemStack transferIntoGrid(EntityPlayer entityPlayer, Container container, int i, ItemStack itemStack) {
        return defaultProvider.transferIntoGrid(entityPlayer, container, ((ContainerStoneCraftingTable) container).getCraftMatrix(), itemStack);
    }

    @Override
    public ItemStack putIntoGrid(EntityPlayer entityPlayer, Container container, int i, ItemStack itemStack, int slotNumber) {
        return defaultProvider.putIntoGrid(entityPlayer, container, ((ContainerStoneCraftingTable) container).getCraftMatrix(), itemStack, slotNumber);
    }

    @Override
    public IInventory getCraftMatrix(EntityPlayer entityPlayer, Container container, int i) {
        return ((ContainerStoneCraftingTable) container).getCraftMatrix();
    }

    @Override
    public void initGui(GuiContainer guiContainer, List<GuiButton> list) {
        list.add(CraftingTweaksAPI.createRotateButton(0, guiContainer.width / 2 - 77, guiContainer.height / 2 - 66));
        list.add(CraftingTweaksAPI.createBalanceButton(0, guiContainer.width / 2 - 77, guiContainer.height / 2 - 48));
        list.add(CraftingTweaksAPI.createClearButton(0, guiContainer.width / 2 - 77, guiContainer.height / 2 - 30));
    }

    @Override
    public String getModId() {
        return "craftingcraft";
    }

}
