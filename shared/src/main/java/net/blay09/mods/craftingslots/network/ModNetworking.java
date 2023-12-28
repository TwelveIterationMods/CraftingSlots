package net.blay09.mods.craftingslots.network;

import net.blay09.mods.balm.api.Balm;
import net.blay09.mods.craftingslots.CraftingSlots;
import net.minecraft.resources.ResourceLocation;

public class ModNetworking {

    public static void initialize() {
        Balm.getNetworking().registerServerboundPacket(id("portable_crafting"), PortableCraftingMessage.class, PortableCraftingMessage::encode, PortableCraftingMessage::decode, PortableCraftingMessage::handle);
    }

    private static ResourceLocation id(String name) {
        return new ResourceLocation(CraftingSlots.MOD_ID, name);
    }
}
