package net.blay09.mods.craftingslots.client;

import net.blay09.mods.balm.api.client.BalmClient;
import net.blay09.mods.balm.neoforge.NeoForgeLoadContext;
import net.blay09.mods.craftingslots.CraftingSlots;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(value = CraftingSlots.MOD_ID, dist = Dist.CLIENT)
public class NeoForgeCraftingSlotsClient {
    public NeoForgeCraftingSlotsClient(IEventBus modEventBus) {
        final var context = new NeoForgeLoadContext(modEventBus);
        BalmClient.initialize(CraftingSlots.MOD_ID, context, CraftingSlotsClient::initialize);
    }
}
