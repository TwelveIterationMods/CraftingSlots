package net.blay09.mods.craftingcraft.container;

import net.blay09.mods.balm.api.DeferredObject;
import net.blay09.mods.balm.api.menu.BalmMenus;
import net.blay09.mods.craftingcraft.CraftingCraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.MenuType;

public class ModMenus {
    public static DeferredObject<MenuType<PortableCraftingMenu>> portableCrafting;
    public static DeferredObject<MenuType<InventoryCraftingMenu>> inventoryCrafting;

    public static void initialize(BalmMenus menus) {
        portableCrafting = menus.registerMenu(id("portable_crafting"), (syncId, inventory, buf) -> new PortableCraftingMenu(syncId, inventory));
        inventoryCrafting = menus.registerMenu(id("inventory_crafting"), (syncId, inventory, buf) -> new InventoryCraftingMenu(syncId, inventory));
    }

    private static ResourceLocation id(String name) {
        return new ResourceLocation(CraftingCraft.MOD_ID, name);
    }

}
