package net.blay09.mods.craftingcraft.addon;

import net.blay09.mods.craftingcraft.CraftingCraft;
import net.blay09.mods.craftingcraft.container.InventoryCraftingContainer;
import net.blay09.mods.craftingcraft.container.PortableCraftingContainer;
import net.blay09.mods.craftingtweaks.api.CraftingTweaksAPI;
import net.blay09.mods.craftingtweaks.api.SimpleTweakProvider;

@SuppressWarnings("unused")
public class CraftingTweaksAddon {

    public CraftingTweaksAddon() {
        SimpleTweakProvider<?> provider = CraftingTweaksAPI.registerSimpleProvider(CraftingCraft.MOD_ID, PortableCraftingContainer.class);
        provider.setTweakRotate(true, true, 10, 17);
        provider.setTweakBalance(true, true, 10, 17 + 18);
        provider.setTweakClear(true, true, 10, 17 + 36);

        CraftingTweaksAPI.registerProvider(InventoryCraftingContainer.class, new InventoryCraftingTweakProvider());
    }

}
