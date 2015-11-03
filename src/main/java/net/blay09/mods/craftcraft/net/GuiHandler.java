package net.blay09.mods.craftcraft.net;

import cpw.mods.fml.common.network.IGuiHandler;
import net.blay09.mods.craftcraft.block.TileEntityStoneCraftingTable;
import net.blay09.mods.craftcraft.container.ContainerPortableCrafting;
import net.blay09.mods.craftcraft.client.GuiCraftCrafting;
import net.blay09.mods.craftcraft.client.GuiInventoryCrafting;
import net.blay09.mods.craftcraft.container.ContainerInventoryCrafting;
import net.blay09.mods.craftcraft.container.ContainerStoneCraftingTable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class GuiHandler implements IGuiHandler {

    public static final int GUI_PORTABLE_CRAFTING = 1;
    public static final int GUI_INVENTORY_CRAFTING = 2;
    public static final int GUI_STONE_CRAFTING_TABLE = 3;

    @Override
    public Object getServerGuiElement(int id, EntityPlayer entityPlayer, World world, int x, int y, int z) {
        if(id == GUI_PORTABLE_CRAFTING) {
            return new ContainerPortableCrafting(entityPlayer);
        } else if(id == GUI_INVENTORY_CRAFTING) {
            return new ContainerInventoryCrafting(entityPlayer);
        } else if(id == GUI_STONE_CRAFTING_TABLE) {
            TileEntity tileEntity = world.getTileEntity(x, y, z);
            if(tileEntity instanceof TileEntityStoneCraftingTable) {
                return new ContainerStoneCraftingTable(entityPlayer, (TileEntityStoneCraftingTable) tileEntity);
            }
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int id, EntityPlayer entityPlayer, World world, int x, int y, int z) {
        if(id == GUI_PORTABLE_CRAFTING) {
            return new GuiCraftCrafting(new ContainerPortableCrafting(entityPlayer));
        } else if(id == GUI_INVENTORY_CRAFTING) {
            return new GuiInventoryCrafting(new ContainerInventoryCrafting(entityPlayer));
        } else if(id == GUI_STONE_CRAFTING_TABLE) {
            TileEntity tileEntity = world.getTileEntity(x, y, z);
            if(tileEntity instanceof TileEntityStoneCraftingTable) {
                return new GuiCraftCrafting(new ContainerStoneCraftingTable(entityPlayer, (TileEntityStoneCraftingTable) tileEntity));
            }
        }
        return null;
    }

}
