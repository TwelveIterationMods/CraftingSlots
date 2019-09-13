package net.blay09.mods.craftingcraft.addon;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaRecipeCategoryUid;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeTransferRegistration;
import net.blay09.mods.craftingcraft.container.InventoryCraftingContainer;
import net.blay09.mods.craftingcraft.container.PortableCraftingContainer;
import net.blay09.mods.craftingcraft.item.ModItems;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

@JeiPlugin
public class JEICraftingCraft implements IModPlugin {

    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation("craftingcraft", "craftingcraft");
    }

    @Override
    public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration) {
        registration.addRecipeTransferHandler(PortableCraftingContainer.class, VanillaRecipeCategoryUid.CRAFTING, 1, 9, 10, 36);
        registration.addRecipeTransferHandler(InventoryCraftingContainer.class, VanillaRecipeCategoryUid.CRAFTING, 1, 9, 10, 27);
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(ModItems.portableCraftingTable), VanillaRecipeCategoryUid.CRAFTING);
        registration.addRecipeCatalyst(new ItemStack(ModItems.inventoryCraftingTable), VanillaRecipeCategoryUid.CRAFTING);
    }

}
