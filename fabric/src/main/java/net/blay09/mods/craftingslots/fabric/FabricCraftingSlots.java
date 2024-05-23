package net.blay09.mods.craftingslots.fabric;

import net.blay09.mods.balm.api.Balm;
import net.blay09.mods.balm.api.EmptyLoadContext;
import net.blay09.mods.craftingslots.CraftingSlots;
import net.fabricmc.api.ModInitializer;

public class FabricCraftingSlots implements ModInitializer {
    @Override
    public void onInitialize() {
        Balm.initialize(CraftingSlots.MOD_ID, EmptyLoadContext.INSTANCE, CraftingSlots::initialize);
    }
}
