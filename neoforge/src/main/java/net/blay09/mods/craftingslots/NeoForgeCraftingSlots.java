package net.blay09.mods.craftingslots;

import net.blay09.mods.balm.api.Balm;
import net.blay09.mods.balm.api.client.BalmClient;
import net.blay09.mods.craftingslots.client.CraftingSlotsClient;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.DistExecutor;
import net.neoforged.fml.common.Mod;

@Mod(CraftingSlots.MOD_ID)
public class NeoForgeCraftingSlots {
    public NeoForgeCraftingSlots() {
        Balm.initialize(CraftingSlots.MOD_ID, CraftingSlots::initialize);
        DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> BalmClient.initialize(CraftingSlots.MOD_ID, CraftingSlotsClient::initialize));
    }
}
