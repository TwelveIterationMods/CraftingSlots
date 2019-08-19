package net.blay09.mods.craftingcraft.network;

import net.blay09.mods.craftingcraft.CraftingCraft;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

import java.util.function.Predicate;

public class NetworkHandler {

    private static String version = "1.0";
    private static Predicate<String> versionCheck = it -> version.equals(it);

    public static SimpleChannel channel;

    public static void init() {
        channel = NetworkRegistry.newSimpleChannel(new ResourceLocation(CraftingCraft.MOD_ID, "network"), () -> version, versionCheck, versionCheck);
        channel.registerMessage(0, MessagePortableCrafting.class, (message, buf) -> {
        }, it -> new MessagePortableCrafting(), MessagePortableCrafting::handle);
    }
}
