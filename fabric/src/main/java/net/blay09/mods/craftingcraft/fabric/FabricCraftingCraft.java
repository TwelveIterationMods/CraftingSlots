package net.blay09.mods.craftingcraft.fabric;

import net.blay09.mods.balm.api.Balm;
import net.blay09.mods.craftingcraft.CraftingCraft;
import net.fabricmc.api.ModInitializer;

public class FabricCraftingCraft implements ModInitializer {
    @Override
    public void onInitialize() {
        Balm.initialize(CraftingCraft.MOD_ID, CraftingCraft::initialize);
    }
}
