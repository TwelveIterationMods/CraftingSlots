package net.blay09.mods.craftingslots.container;

import net.blay09.mods.balm.api.DeferredObject;
import net.blay09.mods.balm.api.menu.BalmMenuFactory;
import net.blay09.mods.balm.api.menu.BalmMenus;
import net.blay09.mods.craftingslots.CraftingSlots;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Unit;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.MenuType;

public class ModMenus {
    public static DeferredObject<MenuType<PortableCraftingMenu>> portableCrafting;
    public static DeferredObject<MenuType<InventoryCraftingMenu>> inventoryCrafting;

    public static void initialize(BalmMenus menus) {
        portableCrafting = menus.registerMenu(id("portable_crafting"), new BalmMenuFactory<PortableCraftingMenu, Unit>() {
            @Override
            public PortableCraftingMenu create(int windowId, Inventory inventory, Unit unit) {
                return new PortableCraftingMenu(windowId, inventory);
            }

            @Override
            public StreamCodec<RegistryFriendlyByteBuf, Unit> getStreamCodec() {
                return StreamCodec.unit(Unit.INSTANCE);
            }
        });
        inventoryCrafting = menus.registerMenu(id("inventory_crafting"), new BalmMenuFactory<InventoryCraftingMenu, Unit>() {
            @Override
            public InventoryCraftingMenu create(int windowId, Inventory inventory, Unit unit) {
                return new InventoryCraftingMenu(windowId, inventory);
            }

            @Override
            public StreamCodec<RegistryFriendlyByteBuf, Unit> getStreamCodec() {
                return StreamCodec.unit(Unit.INSTANCE);
            }
        });
    }

    private static ResourceLocation id(String name) {
        return new ResourceLocation(CraftingSlots.MOD_ID, name);
    }

}
