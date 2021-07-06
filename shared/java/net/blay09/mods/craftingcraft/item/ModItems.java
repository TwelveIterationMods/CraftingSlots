package net.blay09.mods.craftingcraft.item;

import net.blay09.mods.craftingcraft.CraftingCraft;
import net.blay09.mods.forbic.item.ForbicItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class ModItems extends ForbicItems {
    public static final CreativeModeTab creativeModeTab = createCreativeModeTab(id(CraftingCraft.MOD_ID), () -> new ItemStack(ModItems.inventoryCraftingTable));

    public static Item portableCraftingTable = new PortableCraftingItem(itemProperties(creativeModeTab));
    public static Item inventoryCraftingTable = new InventoryCraftingItem(itemProperties(creativeModeTab));

    public static void initialize() {
        registerItem(() -> portableCraftingTable, id("portable_crafting"));
        registerItem(() -> inventoryCraftingTable, id("inventory_crafting"));
    }

    private static ResourceLocation id(String name) {
        return new ResourceLocation(CraftingCraft.MOD_ID, name);
    }

}
