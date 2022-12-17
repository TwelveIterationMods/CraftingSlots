package net.blay09.mods.craftingcraft.item;

import net.blay09.mods.balm.api.item.BalmItems;
import net.blay09.mods.craftingcraft.CraftingCraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class ModItems {
    public static Item portableCraftingTable;
    public static Item inventoryCraftingTable;

    public static void initialize(BalmItems items) {
        items.registerItem(() -> portableCraftingTable = new PortableCraftingItem(items.itemProperties()), id("portable_crafting"));
        items.registerItem(() -> inventoryCraftingTable = new InventoryCraftingItem(items.itemProperties()), id("inventory_crafting"));

        items.registerCreativeModeTab(id(CraftingCraft.MOD_ID), () -> new ItemStack(ModItems.inventoryCraftingTable));
    }

    private static ResourceLocation id(String name) {
        return new ResourceLocation(CraftingCraft.MOD_ID, name);
    }

}
