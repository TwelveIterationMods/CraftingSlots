package net.blay09.mods.craftingcraft;

import net.blay09.mods.balm.api.Balm;
import net.fabricmc.api.ModInitializer;

public class FabricCraftingCraft implements ModInitializer {
    @Override
    public void onInitialize() {
        Balm.initialize(CraftingCraft.MOD_ID, CraftingCraft::initialize);
    }
}
