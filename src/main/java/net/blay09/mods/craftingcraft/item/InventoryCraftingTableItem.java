package net.blay09.mods.craftingcraft.item;

import net.blay09.mods.craftingcraft.CraftingCraft;
import net.blay09.mods.craftingcraft.network.GuiHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class InventoryCraftingTableItem extends PortableCraftingTableItem {

    public static final String name = "inventory_crafting_table";
    public static final ResourceLocation registryName = new ResourceLocation(CraftingCraft.MOD_ID, name);

    @Override
    protected void openPortableCrafting(PlayerEntity entityPlayer, ItemStack itemStack) {
        entityPlayer.openGui(CraftingCraft.instance, GuiHandler.GUI_INVENTORY_CRAFTING, entityPlayer.world, (int) entityPlayer.posX, (int) entityPlayer.posY, (int) entityPlayer.posZ);
    }

}
