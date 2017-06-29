package net.blay09.mods.craftingcraft.addon;

import mezz.jei.api.*;
import mezz.jei.api.recipe.VanillaRecipeCategoryUid;
import mezz.jei.api.recipe.transfer.IRecipeTransferRegistry;
import net.blay09.mods.craftingcraft.CraftingCraft;
import net.blay09.mods.craftingcraft.container.ContainerInventoryCrafting;
import net.blay09.mods.craftingcraft.container.ContainerPortableCrafting;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

@JEIPlugin
public class JEICraftingCraft implements IModPlugin {

    @Override
    public void register(@Nonnull IModRegistry registry) {
        IRecipeTransferRegistry transferRegistry = registry.getRecipeTransferRegistry();
        transferRegistry.addRecipeTransferHandler(ContainerPortableCrafting.class, VanillaRecipeCategoryUid.CRAFTING, 1, 9, 10, 36);
        transferRegistry.addRecipeTransferHandler(ContainerInventoryCrafting.class, VanillaRecipeCategoryUid.CRAFTING, 0, 9, 9, 27);

        registry.addRecipeCatalyst(new ItemStack(CraftingCraft.itemPortableCraftingTable, 1, 0), VanillaRecipeCategoryUid.CRAFTING);
        registry.addRecipeCatalyst(new ItemStack(CraftingCraft.itemPortableCraftingTable, 1, 1), VanillaRecipeCategoryUid.CRAFTING);
    }

}
