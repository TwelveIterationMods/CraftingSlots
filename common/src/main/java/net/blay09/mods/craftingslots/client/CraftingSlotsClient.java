package net.blay09.mods.craftingslots.client;

import net.blay09.mods.balm.client.BalmClientRegistrars;

public class CraftingSlotsClient {
    public static void initialize(BalmClientRegistrars registrars) {
        registrars.menuScreens(ModScreens::initialize);
        ModKeyBindings.initialize();
    }
}
