package net.blay09.mods.craftingslots.client;

import net.blay09.mods.balm.api.client.screen.BalmScreens;
import net.blay09.mods.craftingslots.client.screen.InventoryCraftingScreen;
import net.blay09.mods.craftingslots.client.screen.PortableCraftingScreen;
import net.blay09.mods.craftingslots.menu.ModMenus;

import static net.blay09.mods.craftingslots.CraftingSlots.id;

public class ModScreens {
    public static void initialize(BalmScreens screens) {
        screens.registerScreen(id("portable_crafting"), ModMenus.portableCrafting::get, PortableCraftingScreen::new);
        screens.registerScreen(id("inventory_crafting"), ModMenus.inventoryCrafting::get, InventoryCraftingScreen::new);
    }
}
