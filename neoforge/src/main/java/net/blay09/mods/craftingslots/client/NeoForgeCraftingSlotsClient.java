package net.blay09.mods.craftingslots.client;

import net.blay09.mods.balm.client.BalmClient;
import net.blay09.mods.balm.neoforge.platform.runtime.NeoForgeLoadContext;
import net.blay09.mods.craftingslots.CraftingSlots;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;

@Mod(value = CraftingSlots.MOD_ID, dist = Dist.CLIENT)
public class NeoForgeCraftingSlotsClient {
    public NeoForgeCraftingSlotsClient(ModContainer modContainer, IEventBus modEventBus) {
        final var context = new NeoForgeLoadContext(modContainer, modEventBus);
        BalmClient.initializeMod(CraftingSlots.MOD_ID, context, CraftingSlotsClient::initialize);
    }
}
