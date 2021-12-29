package net.blay09.mods.craftingcraft;

import net.blay09.mods.balm.api.Balm;
import net.blay09.mods.balm.api.client.BalmClient;
import net.blay09.mods.craftingcraft.client.CraftingCraftClient;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;

@Mod(CraftingCraft.MOD_ID)
public class ForgeCraftingCraft {
    public ForgeCraftingCraft() {
        Balm.initialize(CraftingCraft.MOD_ID, CraftingCraft::initialize);
        DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> BalmClient.initialize(CraftingCraft.MOD_ID, CraftingCraftClient::initialize));
    }
}
