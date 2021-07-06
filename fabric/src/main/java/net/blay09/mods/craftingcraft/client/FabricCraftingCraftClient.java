package net.blay09.mods.craftingcraft.client;

import net.fabricmc.api.ClientModInitializer;

public class FabricCraftingCraftClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        CraftingCraftClient.initialize();
    }
}
