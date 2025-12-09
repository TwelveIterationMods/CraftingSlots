package net.blay09.mods.craftingslots;

import net.blay09.mods.balm.Balm;
import net.blay09.mods.balm.core.BalmRegistrars;
import net.blay09.mods.craftingslots.menu.ModMenus;
import net.blay09.mods.craftingslots.item.ModItems;
import net.blay09.mods.craftingslots.network.ModNetworking;
import net.minecraft.resources.Identifier;

public class CraftingSlots {

    public static final String MOD_ID = "craftingslots";

    public static void initialize(BalmRegistrars registrars) {
        ModNetworking.initialize();
        registrars.items(ModItems::initialize);
        registrars.creativeModeTabs(ModItems::initialize);
        registrars.menuTypes(ModMenus::initialize);

        Balm.initializeIfLoaded("craftingtweaks", "net.blay09.mods.craftingslots.addon.CraftingTweaksAddon");
    }

    public static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }
}
