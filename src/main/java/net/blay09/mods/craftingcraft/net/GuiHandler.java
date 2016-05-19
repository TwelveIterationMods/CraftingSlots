package net.blay09.mods.craftingcraft.net;

import net.blay09.mods.craftingcraft.block.TileEntityStoneCraftingTable;
import net.blay09.mods.craftingcraft.container.ContainerPortableCrafting;
import net.blay09.mods.craftingcraft.client.gui.GuiCraftCrafting;
import net.blay09.mods.craftingcraft.client.gui.GuiInventoryCrafting;
import net.blay09.mods.craftingcraft.container.ContainerInventoryCrafting;
import net.blay09.mods.craftingcraft.container.ContainerStoneCraftingTable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

import javax.annotation.Nullable;

public class GuiHandler implements IGuiHandler {

    public static final int GUI_PORTABLE_CRAFTING = 1;
    public static final int GUI_INVENTORY_CRAFTING = 2;
    public static final int GUI_STONE_CRAFTING_TABLE = 3;

    @Override
    @Nullable
    public Object getServerGuiElement(int id, EntityPlayer entityPlayer, World world, int x, int y, int z) {
        if(id == GUI_PORTABLE_CRAFTING) {
            return new ContainerPortableCrafting(entityPlayer);
        } else if(id == GUI_INVENTORY_CRAFTING) {
            return new ContainerInventoryCrafting(entityPlayer);
        } else if(id == GUI_STONE_CRAFTING_TABLE) {
            TileEntity tileEntity = world.getTileEntity(new BlockPos(x, y, z));
            if(tileEntity instanceof TileEntityStoneCraftingTable) {
                return new ContainerStoneCraftingTable(entityPlayer, (TileEntityStoneCraftingTable) tileEntity);
            }
        }
        return null;
    }

    @Override
    @Nullable
    public Object getClientGuiElement(int id, EntityPlayer entityPlayer, World world, int x, int y, int z) {
        if(id == GUI_PORTABLE_CRAFTING) {
            return new GuiCraftCrafting(new ContainerPortableCrafting(entityPlayer));
        } else if(id == GUI_INVENTORY_CRAFTING) {
            return new GuiInventoryCrafting(new ContainerInventoryCrafting(entityPlayer));
        } else if(id == GUI_STONE_CRAFTING_TABLE) {
            TileEntity tileEntity = world.getTileEntity(new BlockPos(x, y, z));
            if(tileEntity instanceof TileEntityStoneCraftingTable) {
                return new GuiCraftCrafting(new ContainerStoneCraftingTable(entityPlayer, (TileEntityStoneCraftingTable) tileEntity));
            }
        }
        return null;
    }

}
