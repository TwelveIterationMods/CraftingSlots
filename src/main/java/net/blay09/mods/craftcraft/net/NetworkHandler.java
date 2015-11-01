package net.blay09.mods.craftcraft.net;

import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

public class NetworkHandler {

    public static final SimpleNetworkWrapper instance = new SimpleNetworkWrapper("craftcraft");

    public static void init() {
        instance.registerMessage(HandlerPortableCrafting.class, MessagePortableCrafting.class, 0, Side.SERVER);
    }
}
