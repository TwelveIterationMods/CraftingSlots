package net.blay09.mods.craftcraft.addon;

import net.blay09.mods.craftcraft.container.ContainerInventoryCrafting;
import net.blay09.mods.craftcraft.container.ContainerPortableCrafting;
import net.blay09.mods.craftcraft.container.ContainerStoneCraftingTable;
import net.blay09.mods.craftingtweaks.api.CraftingTweaksAPI;

public class CraftingTweaksAddon {

    public CraftingTweaksAddon() {
        CraftingTweaksAPI.registerProvider(ContainerPortableCrafting.class, new PortableCraftingTweakProvider());
        CraftingTweaksAPI.registerProvider(ContainerInventoryCrafting.class, new InventoryCraftingTweakProvider());
        CraftingTweaksAPI.registerProvider(ContainerStoneCraftingTable.class, new StoneCraftingTableTweakProvider());
    }

}
