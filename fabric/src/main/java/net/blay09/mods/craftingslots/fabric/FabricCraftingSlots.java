package net.blay09.mods.craftingslots.fabric;

import net.blay09.mods.balm.Balm;
import net.blay09.mods.balm.fabric.platform.runtime.FabricLoadContext;
import net.blay09.mods.craftingslots.CraftingSlots;
import net.fabricmc.api.ModInitializer;

public class FabricCraftingSlots implements ModInitializer {
    @Override
    public void onInitialize() {
        Balm.initializeMod(CraftingSlots.MOD_ID, FabricLoadContext.INSTANCE, CraftingSlots::initialize);
    }
}
