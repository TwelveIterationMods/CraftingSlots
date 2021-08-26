package net.blay09.mods.craftingcraft.client;

import net.blay09.mods.balm.api.client.screen.BalmScreens;
import net.blay09.mods.craftingcraft.client.screen.InventoryCraftingScreen;
import net.blay09.mods.craftingcraft.client.screen.PortableCraftingScreen;
import net.blay09.mods.craftingcraft.container.ModMenus;

public class ModScreens {
    public static void initialize(BalmScreens screens) {
        screens.registerScreen(ModMenus.portableCrafting.get(), PortableCraftingScreen::new);
        screens.registerScreen(ModMenus.inventoryCrafting.get(), InventoryCraftingScreen::new);
    }
}
