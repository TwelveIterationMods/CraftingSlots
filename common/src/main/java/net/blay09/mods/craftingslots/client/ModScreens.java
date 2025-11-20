package net.blay09.mods.craftingslots.client;

import net.blay09.mods.balm.client.gui.screens.inventory.BalmMenuScreenRegistrar;
import net.blay09.mods.craftingslots.client.screen.InventoryCraftingScreen;
import net.blay09.mods.craftingslots.client.screen.PortableCraftingScreen;
import net.blay09.mods.craftingslots.menu.ModMenus;

public class ModScreens {
    public static void initialize(BalmMenuScreenRegistrar screens) {
        screens.register(ModMenus.portableCrafting, PortableCraftingScreen::new);
        screens.register(ModMenus.inventoryCrafting, InventoryCraftingScreen::new);
    }
}
