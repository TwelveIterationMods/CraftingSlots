package net.blay09.mods.craftingslots;

import net.blay09.mods.balm.api.Balm;
import net.blay09.mods.balm.api.client.BalmClient;
import net.blay09.mods.craftingslots.client.CraftingSlotsClient;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;

@Mod(CraftingSlots.MOD_ID)
public class ForgeCraftingSlots {
    public ForgeCraftingSlots() {
        Balm.initialize(CraftingSlots.MOD_ID, CraftingSlots::initialize);
        DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> BalmClient.initialize(CraftingSlots.MOD_ID, CraftingSlotsClient::initialize));
    }
}
