package net.blay09.mods.craftcraft.net;

import cpw.mods.fml.common.network.IGuiHandler;
import net.blay09.mods.craftcraft.container.ContainerPortableCrafting;
import net.blay09.mods.craftcraft.client.GuiPortableCrafting;
import net.blay09.mods.craftcraft.client.GuiInventoryCrafting;
import net.blay09.mods.craftcraft.container.ContainerInventoryCrafting;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class GuiHandler implements IGuiHandler {

    public static final int GUI_PORTABLE_CRAFTING = 1;
    public static final int GUI_INVENTORY_CRAFTING = 2;

    @Override
    public Object getServerGuiElement(int id, EntityPlayer entityPlayer, World world, int x, int y, int z) {
        if(id == GUI_PORTABLE_CRAFTING) {
            return new ContainerPortableCrafting(entityPlayer);
        } else if(id == GUI_INVENTORY_CRAFTING) {
            return new ContainerInventoryCrafting(entityPlayer);
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int id, EntityPlayer entityPlayer, World world, int x, int y, int z) {
        if(id == GUI_PORTABLE_CRAFTING) {
            return new GuiPortableCrafting(new ContainerPortableCrafting(entityPlayer));
        } else if(id == GUI_INVENTORY_CRAFTING) {
            return new GuiInventoryCrafting(new ContainerInventoryCrafting(entityPlayer));
        }
        return null;
    }

}
