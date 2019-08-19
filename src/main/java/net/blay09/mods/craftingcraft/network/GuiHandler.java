package net.blay09.mods.craftingcraft.network;

import net.blay09.mods.craftingcraft.container.ContainerPortableCrafting;
import net.blay09.mods.craftingcraft.client.gui.PortableCraftingScreen;
import net.blay09.mods.craftingcraft.client.gui.InventoryCraftingScreen;
import net.blay09.mods.craftingcraft.container.ContainerInventoryCrafting;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

import javax.annotation.Nullable;

public class GuiHandler implements IGuiHandler {

    public static final int GUI_PORTABLE_CRAFTING = 1;
    public static final int GUI_INVENTORY_CRAFTING = 2;

    @Override
    @Nullable
    public Object getServerGuiElement(int id, EntityPlayer entityPlayer, World world, int x, int y, int z) {
        if(id == GUI_PORTABLE_CRAFTING) {
            return new ContainerPortableCrafting(entityPlayer);
        } else if(id == GUI_INVENTORY_CRAFTING) {
            return new ContainerInventoryCrafting(entityPlayer);
        }
        return null;
    }

    @Override
    @Nullable
    public Object getClientGuiElement(int id, EntityPlayer entityPlayer, World world, int x, int y, int z) {
        if(id == GUI_PORTABLE_CRAFTING) {
            return new PortableCraftingScreen(new ContainerPortableCrafting(entityPlayer));
        } else if(id == GUI_INVENTORY_CRAFTING) {
            return new InventoryCraftingScreen(new ContainerInventoryCrafting(entityPlayer));
        }
        return null;
    }

}
