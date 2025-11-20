package net.blay09.mods.craftingslots.menu;

import net.blay09.mods.balm.world.BalmMenuFactory;
import net.blay09.mods.balm.world.inventory.BalmMenuTypeRegistrar;
import net.blay09.mods.craftingslots.CraftingSlots;
import net.minecraft.core.Holder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Unit;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.MenuType;

public class ModMenus {
    public static Holder<MenuType<PortableCraftingMenu>> portableCrafting;
    public static Holder<MenuType<InventoryCraftingMenu>> inventoryCrafting;

    public static void initialize(BalmMenuTypeRegistrar menus) {
        portableCrafting = menus.register("portable_crafting", new BalmMenuFactory<PortableCraftingMenu, Unit>() {
            @Override
            public PortableCraftingMenu create(int windowId, Inventory inventory, Unit unit) {
                return new PortableCraftingMenu(windowId, inventory);
            }

            @Override
            public StreamCodec<RegistryFriendlyByteBuf, Unit> getStreamCodec() {
                return StreamCodec.unit(Unit.INSTANCE);
            }
        }).asHolder();
        inventoryCrafting = menus.register("inventory_crafting", new BalmMenuFactory<InventoryCraftingMenu, Unit>() {
            @Override
            public InventoryCraftingMenu create(int windowId, Inventory inventory, Unit unit) {
                return new InventoryCraftingMenu(windowId, inventory);
            }

            @Override
            public StreamCodec<RegistryFriendlyByteBuf, Unit> getStreamCodec() {
                return StreamCodec.unit(Unit.INSTANCE);
            }
        }).asHolder();
    }

    private static Identifier id(String name) {
        return Identifier.fromNamespaceAndPath(CraftingSlots.MOD_ID, name);
    }

}
