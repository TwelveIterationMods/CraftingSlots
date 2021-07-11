package net.blay09.mods.craftingcraft.client;

import net.blay09.mods.balm.client.screen.BalmScreens;
import net.blay09.mods.craftingcraft.client.screen.InventoryCraftingScreen;
import net.blay09.mods.craftingcraft.client.screen.PortableCraftingScreen;
import net.blay09.mods.craftingcraft.container.ModMenus;

public class ModScreens extends BalmScreens {
    public static void initialize() {
        registerScreen(ModMenus.portableCrafting.get(), PortableCraftingScreen::new);
        registerScreen(ModMenus.inventoryCrafting.get(), InventoryCraftingScreen::new);
    }
}
