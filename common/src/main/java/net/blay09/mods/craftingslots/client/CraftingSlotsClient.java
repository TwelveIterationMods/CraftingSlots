package net.blay09.mods.craftingslots.client;

import net.blay09.mods.balm.Balm;
import net.blay09.mods.balm.client.BalmClientRegistrars;
import net.blay09.mods.balm.mixin.AbstractContainerScreenAccessor;
import net.blay09.mods.craftingslots.client.screen.InventoryCraftingScreen;
import net.blay09.mods.craftingslots.menu.InventoryCraftingMenu;
import net.blay09.mods.craftingslots.menu.ModMenus;
import net.blay09.mods.craftingslots.menu.PortableCraftingMenu;
import net.minecraft.client.renderer.Rect2i;
import net.minecraft.world.item.crafting.RecipeType;

import java.util.List;

import static net.blay09.mods.craftingslots.CraftingSlots.id;

public class CraftingSlotsClient {
    public static void initialize(BalmClientRegistrars registrars) {
        registrars.menuScreens(ModScreens::initialize);
        ModKeyBindings.initialize();

        Balm.modSupport().recipeViewers().register(id("transfer_handlers"), registrar -> {
            registrar.registerRecipeTransferHandler(PortableCraftingMenu.class, ModMenus.portableCrafting, RecipeType.CRAFTING, 1, 9, 10, 36);
            registrar.registerRecipeTransferHandler(InventoryCraftingMenu.class, ModMenus.inventoryCrafting, RecipeType.CRAFTING, 1, 9, 10, 27);
            registrar.registerScreenOcclusion(InventoryCraftingScreen.class, containerScreen -> {
                final var accessor = ((AbstractContainerScreenAccessor) containerScreen);
                return List.of(new Rect2i(
                        accessor.getLeftPos() + accessor.getImageWidth(),
                        accessor.getTopPos(),
                        40,
                        64
                ));
            });
        });
    }
}
