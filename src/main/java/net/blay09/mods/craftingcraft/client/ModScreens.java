package net.blay09.mods.craftingcraft.client;

import net.blay09.mods.craftingcraft.client.gui.InventoryCraftingScreen;
import net.blay09.mods.craftingcraft.client.gui.PortableCraftingScreen;
import net.blay09.mods.craftingcraft.container.ModContainers;
import net.minecraft.client.gui.ScreenManager;

public class ModScreens {
    public static void register() {
        ScreenManager.registerFactory(ModContainers.portableCrafting, PortableCraftingScreen::new);
        ScreenManager.registerFactory(ModContainers.inventoryCrafting, InventoryCraftingScreen::new);
    }
}
