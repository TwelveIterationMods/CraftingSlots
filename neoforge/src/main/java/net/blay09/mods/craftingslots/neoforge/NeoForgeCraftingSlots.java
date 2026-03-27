package net.blay09.mods.craftingslots.neoforge;

import net.blay09.mods.balm.Balm;
import net.blay09.mods.balm.neoforge.platform.runtime.NeoForgeLoadContext;
import net.blay09.mods.craftingslots.CraftingSlots;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;

@Mod(CraftingSlots.MOD_ID)
public class NeoForgeCraftingSlots {
    public NeoForgeCraftingSlots(ModContainer modContainer, IEventBus modEventBus) {
        final var context = new NeoForgeLoadContext(modContainer, modEventBus);
        Balm.initializeMod(CraftingSlots.MOD_ID, context, CraftingSlots::initialize);
    }
}
