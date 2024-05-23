package net.blay09.mods.craftingslots;

import net.blay09.mods.balm.api.Balm;
import net.blay09.mods.craftingslots.container.ModMenus;
import net.blay09.mods.craftingslots.item.ModItems;
import net.blay09.mods.craftingslots.network.ModNetworking;

public class CraftingSlots {

    public static final String MOD_ID = "craftingslots";

    public static void initialize() {
        ModNetworking.initialize();
        ModItems.initialize(Balm.getItems());
        ModMenus.initialize(Balm.getMenus());

        Balm.initializeIfLoaded("craftingtweaks", "net.blay09.mods.craftingslots.addon.CraftingTweaksAddon");
    }

}
