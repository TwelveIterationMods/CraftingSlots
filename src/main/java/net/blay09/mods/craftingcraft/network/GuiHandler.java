package net.blay09.mods.craftingcraft.network;

import net.blay09.mods.craftingcraft.container.PortableCraftingContainer;
import net.blay09.mods.craftingcraft.client.gui.PortableCraftingScreen;
import net.blay09.mods.craftingcraft.client.gui.InventoryCraftingScreen;
import net.blay09.mods.craftingcraft.container.InventoryCraftingContainer;
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
            return new PortableCraftingContainer(entityPlayer);
        } else if(id == GUI_INVENTORY_CRAFTING) {
            return new InventoryCraftingContainer(entityPlayer);
        }
        return null;
    }

    @Override
    @Nullable
    public Object getClientGuiElement(int id, EntityPlayer entityPlayer, World world, int x, int y, int z) {
        if(id == GUI_PORTABLE_CRAFTING) {
            return new PortableCraftingScreen(new PortableCraftingContainer(entityPlayer));
        } else if(id == GUI_INVENTORY_CRAFTING) {
            return new InventoryCraftingScreen(new InventoryCraftingContainer(entityPlayer));
        }
        return null;
    }

}
