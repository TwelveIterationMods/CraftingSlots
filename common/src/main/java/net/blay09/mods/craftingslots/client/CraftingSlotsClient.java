package net.blay09.mods.craftingslots.client;

import net.blay09.mods.balm.api.client.BalmClient;

public class CraftingSlotsClient {
    public static void initialize() {
        ModScreens.initialize(BalmClient.getScreens());
        ModKeyBindings.initialize(BalmClient.getKeyMappings());
    }
}
