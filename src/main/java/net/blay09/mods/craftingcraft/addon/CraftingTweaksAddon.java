package net.blay09.mods.craftingcraft.addon;

import net.blay09.mods.craftingcraft.CraftingCraft;
import net.blay09.mods.craftingcraft.container.ContainerInventoryCrafting;
import net.blay09.mods.craftingcraft.container.ContainerPortableCrafting;

@SuppressWarnings("unused")
public class CraftingTweaksAddon {

    public CraftingTweaksAddon() {
        SimpleTweakProvider provider = CraftingTweaksAPI.registerSimpleProvider(CraftingCraft.MOD_ID, ContainerPortableCrafting.class);
        provider.setTweakRotate(true, true, 10, 17);
        provider.setTweakBalance(true, true, 10, 17 + 18);
        provider.setTweakClear(true, true, 10, 17 + 36);

        CraftingTweaksAPI.registerProvider(ContainerInventoryCrafting.class, new InventoryCraftingTweakProvider());
    }

}
