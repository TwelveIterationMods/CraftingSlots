package net.blay09.mods.craftingslots.network;

import net.blay09.mods.balm.Balm;

public class ModNetworking {

    public static void initialize() {
        Balm.networking().registerServerboundPacket(PortableCraftingMessage.TYPE, PortableCraftingMessage.class, PortableCraftingMessage.STREAM_CODEC, PortableCraftingMessage::handle);
    }

}
