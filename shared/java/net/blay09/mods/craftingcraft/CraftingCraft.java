package net.blay09.mods.craftingcraft;

import net.blay09.mods.craftingcraft.container.ModMenus;
import net.blay09.mods.craftingcraft.item.ModItems;
import net.blay09.mods.craftingcraft.network.ModNetworking;
import net.fabricmc.loader.api.FabricLoader;

public class CraftingCraft {

    public static final String MOD_ID = "craftingcraft";

    public static void initialize() {
        ModNetworking.initialize();
        ModItems.initialize();
        ModMenus.initialize();

        if (FabricLoader.getInstance().isModLoaded("craftingtweaks")) {
            try {
                Class.forName("net.blay09.mods.craftingcraft.addon.CraftingTweaksAddon").newInstance();
            } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

}
