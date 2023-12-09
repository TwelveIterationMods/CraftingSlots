package net.blay09.mods.craftingcraft;

import net.blay09.mods.balm.api.Balm;
import net.blay09.mods.balm.api.client.BalmClient;
import net.blay09.mods.craftingcraft.client.CraftingCraftClient;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.DistExecutor;
import net.neoforged.fml.common.Mod;

@Mod(CraftingCraft.MOD_ID)
public class NeoForgeCraftingCraft {
    public NeoForgeCraftingCraft() {
        Balm.initialize(CraftingCraft.MOD_ID, CraftingCraft::initialize);
        DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> BalmClient.initialize(CraftingCraft.MOD_ID, CraftingCraftClient::initialize));
    }
}
