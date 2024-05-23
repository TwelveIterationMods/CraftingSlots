package net.blay09.mods.craftingslots.fabric.client;

import net.blay09.mods.balm.api.EmptyLoadContext;
import net.blay09.mods.balm.api.client.BalmClient;
import net.blay09.mods.craftingslots.CraftingSlots;
import net.blay09.mods.craftingslots.client.CraftingSlotsClient;
import net.fabricmc.api.ClientModInitializer;

public class FabricCraftingSlotsClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        BalmClient.initialize(CraftingSlots.MOD_ID, EmptyLoadContext.INSTANCE, CraftingSlotsClient::initialize);
    }
}
