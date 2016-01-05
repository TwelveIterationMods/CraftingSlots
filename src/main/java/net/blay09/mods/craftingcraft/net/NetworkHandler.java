package net.blay09.mods.craftingcraft.net;

import net.blay09.mods.craftingcraft.CraftingCraft;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class NetworkHandler {

    public static final SimpleNetworkWrapper instance = new SimpleNetworkWrapper(CraftingCraft.MOD_ID);

    public static void init() {
        instance.registerMessage(HandlerPortableCrafting.class, MessagePortableCrafting.class, 0, Side.SERVER);
    }
}
