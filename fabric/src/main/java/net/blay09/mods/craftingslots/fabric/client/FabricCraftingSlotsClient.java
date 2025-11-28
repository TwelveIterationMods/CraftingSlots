package net.blay09.mods.craftingslots.fabric.client;

import net.blay09.mods.balm.client.BalmClient;
import net.blay09.mods.balm.fabric.platform.runtime.FabricLoadContext;
import net.blay09.mods.craftingslots.CraftingSlots;
import net.blay09.mods.craftingslots.client.CraftingSlotsClient;
import net.fabricmc.api.ClientModInitializer;

public class FabricCraftingSlotsClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        BalmClient.initializeMod(CraftingSlots.MOD_ID, FabricLoadContext.INSTANCE, CraftingSlotsClient::initialize);
    }
}
