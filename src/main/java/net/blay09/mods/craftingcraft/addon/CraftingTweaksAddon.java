package net.blay09.mods.craftingcraft.addon;

import net.blay09.mods.craftingcraft.container.ContainerInventoryCrafting;
import net.blay09.mods.craftingcraft.container.ContainerPortableCrafting;
import net.blay09.mods.craftingcraft.container.ContainerStoneCraftingTable;
import net.blay09.mods.craftingtweaks.api.CraftingTweaksAPI;

public class CraftingTweaksAddon {

    public CraftingTweaksAddon() {
        CraftingTweaksAPI.registerProvider(ContainerPortableCrafting.class, new PortableCraftingTweakProvider());
        CraftingTweaksAPI.registerProvider(ContainerInventoryCrafting.class, new InventoryCraftingTweakProvider());
        CraftingTweaksAPI.registerProvider(ContainerStoneCraftingTable.class, new StoneCraftingTableTweakProvider());
    }

}
