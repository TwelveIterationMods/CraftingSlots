package net.blay09.mods.craftingcraft;

import net.blay09.mods.craftingcraft.client.CraftingCraftClient;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;

@Mod(CraftingCraft.MOD_ID)
public class ForgeCraftingCraft {
    public ForgeCraftingCraft() {
        CraftingCraft.initialize();
        DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> CraftingCraftClient::initialize);
    }
}
