package net.blay09.mods.craftingcraft;

import net.fabricmc.api.ModInitializer;

public class FabricCraftingCraft implements ModInitializer {
    @Override
    public void onInitialize() {
        CraftingCraft.initialize();
    }
}
