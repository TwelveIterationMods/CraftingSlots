package net.blay09.mods.craftingcraft.client;

import net.blay09.mods.craftingcraft.client.screen.InventoryCraftingScreen;
import net.blay09.mods.craftingcraft.client.screen.PortableCraftingScreen;
import net.blay09.mods.craftingcraft.container.ModMenus;
import net.blay09.mods.forbic.client.ForbicModScreens;

public class ModScreens extends ForbicModScreens {
    public static void initialize() {
        register(ModMenus.portableCrafting.get(), PortableCraftingScreen::new);
        register(ModMenus.inventoryCrafting.get(), InventoryCraftingScreen::new);
    }
}
