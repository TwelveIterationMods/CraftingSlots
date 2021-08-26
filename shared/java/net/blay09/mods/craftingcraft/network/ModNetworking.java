package net.blay09.mods.craftingcraft.network;

import net.blay09.mods.balm.api.Balm;
import net.blay09.mods.craftingcraft.CraftingCraft;
import net.minecraft.resources.ResourceLocation;

public class ModNetworking {

    public static void initialize() {
        Balm.getNetworking().registerServerboundPacket(id("portable_crafting"), PortableCraftingMessage.class, PortableCraftingMessage::encode, PortableCraftingMessage::decode, PortableCraftingMessage::handle);
    }

    private static ResourceLocation id(String name) {
        return new ResourceLocation(CraftingCraft.MOD_ID, name);
    }
}
