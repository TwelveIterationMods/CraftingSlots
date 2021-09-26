package net.blay09.mods.craftingcraft;

import net.blay09.mods.balm.api.Balm;
import net.blay09.mods.craftingcraft.container.ModMenus;
import net.blay09.mods.craftingcraft.item.ModItems;
import net.blay09.mods.craftingcraft.network.ModNetworking;

public class CraftingCraft {

    public static final String MOD_ID = "craftingcraft";

    public static void initialize() {
        ModNetworking.initialize();
        ModItems.initialize(Balm.getItems());
        ModMenus.initialize(Balm.getMenus());

        Balm.initializeIfLoaded("craftingtweaks", "net.blay09.mods.craftingcraft.addon.CraftingTweaksAddon");

        Balm.initialize(MOD_ID);
    }

}
