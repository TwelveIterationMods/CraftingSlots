package net.blay09.mods.craftingcraft.item;

import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

public class ModItems {
    public static Item portableCraftingTable;
    public static Item inventoryCraftingTable;

    public static void register(IForgeRegistry<Item> registry) {
        registry.registerAll(
                portableCraftingTable = new PortableCraftingItem().setRegistryName(PortableCraftingItem.name),
                inventoryCraftingTable = new InventoryCraftingItem().setRegistryName(InventoryCraftingItem.name)
        );
    }

}
