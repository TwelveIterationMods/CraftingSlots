package net.blay09.mods.craftingcraft;

import net.blay09.mods.balm.api.Balm;
import net.blay09.mods.craftingcraft.container.ModMenus;
import net.blay09.mods.craftingcraft.item.ModItems;
import net.blay09.mods.craftingcraft.network.ModNetworking;
import net.fabricmc.loader.api.FabricLoader;

import java.lang.reflect.InvocationTargetException;

public class CraftingCraft {

    public static final String MOD_ID = "craftingcraft";

    public static void initialize() {
        ModNetworking.initialize();
        ModItems.initialize(Balm.getItems());
        ModMenus.initialize(Balm.getMenus());

        if (FabricLoader.getInstance().isModLoaded("craftingtweaks")) {
            try {
                Class.forName("net.blay09.mods.craftingcraft.addon.CraftingTweaksAddon").getConstructor().newInstance();
            } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | NoSuchMethodException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

}
