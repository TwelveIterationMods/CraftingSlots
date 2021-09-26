package net.blay09.mods.craftingcraft.item;

import net.blay09.mods.craftingcraft.container.InventoryCraftingMenu;
import net.minecraft.world.MenuProvider;

public class InventoryCraftingItem extends PortableCraftingItem {

    public static final String name = "inventory_crafting";

    public InventoryCraftingItem(Properties properties) {
        super(properties);
    }

    @Override
    protected MenuProvider getCraftingContainerProvider() {
        return InventoryCraftingMenu.provider;
    }

}
