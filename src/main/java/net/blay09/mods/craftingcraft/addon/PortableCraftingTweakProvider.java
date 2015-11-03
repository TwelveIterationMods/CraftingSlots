package net.blay09.mods.craftingcraft.addon;

import net.blay09.mods.craftingcraft.container.ContainerPortableCrafting;
import net.blay09.mods.craftingtweaks.api.CraftingTweaksAPI;
import net.blay09.mods.craftingtweaks.api.DefaultProvider;
import net.blay09.mods.craftingtweaks.api.TweakProvider;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

import java.util.List;

public class PortableCraftingTweakProvider implements TweakProvider {

    private DefaultProvider defaultProvider = CraftingTweaksAPI.createDefaultProvider();

    @Override
    public boolean load() {
        return true;
    }

    @Override
    public void clearGrid(EntityPlayer entityPlayer, Container container, int i) {
        defaultProvider.clearGrid(entityPlayer, container, ((ContainerPortableCrafting) container).getCraftMatrix());
    }

    @Override
    public void rotateGrid(EntityPlayer entityPlayer, Container container, int i) {
        defaultProvider.rotateGrid(entityPlayer, container, ((ContainerPortableCrafting) container).getCraftMatrix());
    }

    @Override
    public void balanceGrid(EntityPlayer entityPlayer, Container container, int i) {
        defaultProvider.balanceGrid(entityPlayer, container, ((ContainerPortableCrafting) container).getCraftMatrix());
    }

    @Override
    public void initGui(GuiContainer guiContainer, List list) {
        list.add(CraftingTweaksAPI.createRotateButton(0, guiContainer.width / 2 - 77, guiContainer.height / 2 - 66));
        list.add(CraftingTweaksAPI.createBalanceButton(0, guiContainer.width / 2 - 77, guiContainer.height / 2 - 48));
        list.add(CraftingTweaksAPI.createClearButton(0, guiContainer.width / 2 - 77, guiContainer.height / 2 - 30));
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
