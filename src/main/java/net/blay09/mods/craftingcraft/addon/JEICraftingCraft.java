package net.blay09.mods.craftingcraft.addon;

import mezz.jei.api.*;
import mezz.jei.api.recipe.VanillaRecipeCategoryUid;
import mezz.jei.api.recipe.transfer.IRecipeTransferRegistry;
import net.blay09.mods.craftingcraft.CraftingCraft;
import net.blay09.mods.craftingcraft.container.ContainerInventoryCrafting;
import net.blay09.mods.craftingcraft.container.ContainerPortableCrafting;
import net.blay09.mods.craftingcraft.container.ContainerStoneCraftingTable;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

@SuppressWarnings("unused")
@JEIPlugin
public class JEICraftingCraft extends BlankModPlugin {

    @Override
    public void register(@Nonnull IModRegistry registry) {
        IRecipeTransferRegistry transferRegistry = registry.getRecipeTransferRegistry();
        transferRegistry.addRecipeTransferHandler(ContainerStoneCraftingTable.class, VanillaRecipeCategoryUid.CRAFTING, 1, 9, 10, 36);
        transferRegistry.addRecipeTransferHandler(ContainerPortableCrafting.class, VanillaRecipeCategoryUid.CRAFTING, 1, 9, 10, 36);
        transferRegistry.addRecipeTransferHandler(ContainerInventoryCrafting.class, VanillaRecipeCategoryUid.CRAFTING, 0, 9, 9, 27);

        registry.addRecipeCategoryCraftingItem(new ItemStack(CraftingCraft.portableCraftingTable, 1, 0), VanillaRecipeCategoryUid.CRAFTING);
        registry.addRecipeCategoryCraftingItem(new ItemStack(CraftingCraft.portableCraftingTable, 1, 1), VanillaRecipeCategoryUid.CRAFTING);
        registry.addRecipeCategoryCraftingItem(new ItemStack(CraftingCraft.stoneCraftingTable, 1, 0), VanillaRecipeCategoryUid.CRAFTING);
        registry.addRecipeCategoryCraftingItem(new ItemStack(CraftingCraft.stoneCraftingTable, 1, 1), VanillaRecipeCategoryUid.CRAFTING);
        registry.addRecipeCategoryCraftingItem(new ItemStack(CraftingCraft.craftingTableFrame), VanillaRecipeCategoryUid.CRAFTING);
    }

}
