package net.blay09.mods.craftingslots;

import net.blay09.mods.balm.api.Balm;
import net.blay09.mods.balm.neoforge.NeoForgeLoadContext;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(CraftingSlots.MOD_ID)
public class NeoForgeCraftingSlots {
    public NeoForgeCraftingSlots(IEventBus modEventBus) {
        final var context = new NeoForgeLoadContext(modEventBus);
        Balm.initialize(CraftingSlots.MOD_ID, context, CraftingSlots::initialize);
    }
}
