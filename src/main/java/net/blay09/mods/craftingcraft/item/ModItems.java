package net.blay09.mods.craftingcraft.item;

import net.blay09.mods.craftingcraft.item.InventoryCraftingTableItem;
import net.blay09.mods.craftingcraft.item.PortableCraftingTableItem;
import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

public class ModItems {
    public static Item portableCraftingTable;
    public static Item inventoryCraftingTable;

    public static void register(IForgeRegistry<Item> registry) {
        registry.registerAll(
                portableCraftingTable = new PortableCraftingTableItem().setRegistryName(PortableCraftingTableItem.name),
                inventoryCraftingTable = new InventoryCraftingTableItem().setRegistryName(InventoryCraftingTableItem.name)
        );
    }

}
