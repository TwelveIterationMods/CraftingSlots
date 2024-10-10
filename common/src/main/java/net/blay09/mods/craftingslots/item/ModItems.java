package net.blay09.mods.craftingslots.item;

import net.blay09.mods.balm.api.item.BalmItems;
import net.blay09.mods.craftingslots.CraftingSlots;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class ModItems {
    public static Item portableCraftingTable;
    public static Item inventoryCraftingTable;

    public static void initialize(BalmItems items) {
        items.registerItem((identifier) -> portableCraftingTable = new PortableCraftingItem(defaultProperties(identifier)), id("portable_crafting"));
        items.registerItem((identifier) -> inventoryCraftingTable = new InventoryCraftingItem(defaultProperties(identifier)), id("inventory_crafting"));

        items.registerCreativeModeTab(() -> new ItemStack(ModItems.inventoryCraftingTable), id(CraftingSlots.MOD_ID));
    }

    private static ResourceLocation id(String name) {
        return ResourceLocation.fromNamespaceAndPath(CraftingSlots.MOD_ID, name);
    }

    private static Item.Properties defaultProperties(ResourceLocation identifier) {
        return new Item.Properties().setId(itemId(identifier));
    }

    private static ResourceKey<Item> itemId(ResourceLocation identifier) {
        return ResourceKey.create(Registries.ITEM, identifier);
    }
}
