package net.blay09.mods.craftingslots;

import net.blay09.mods.balm.api.Balm;
import net.blay09.mods.balm.api.client.BalmClient;
import net.blay09.mods.balm.forge.ForgeLoadContext;
import net.blay09.mods.craftingslots.client.CraftingSlotsClient;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;

@Mod(CraftingSlots.MOD_ID)
public class ForgeCraftingSlots {
    public ForgeCraftingSlots(FMLJavaModLoadingContext context) {
        final var loadContext = new ForgeLoadContext(context.getModEventBus());
        Balm.initializeMod(CraftingSlots.MOD_ID, loadContext, CraftingSlots::initialize);
        if (FMLEnvironment.dist.isClient()) {
            BalmClient.initializeMod(CraftingSlots.MOD_ID, loadContext, CraftingSlotsClient::initialize);
        }
    }
}
