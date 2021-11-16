package net.blay09.mods.craftingcraft.client;

import net.blay09.mods.balm.api.client.BalmClient;
import net.blay09.mods.craftingcraft.CraftingCraft;

public class CraftingCraftClient {
    public static void initialize() {
        ModScreens.initialize(BalmClient.getScreens());
        ModKeyBindings.initialize(BalmClient.getKeyMappings());
    }
}
