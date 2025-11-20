package net.blay09.mods.craftingslots.item;

import net.blay09.mods.balm.world.item.BalmCreativeModeTabRegistrar;
import net.blay09.mods.balm.world.item.BalmItemRegistrar;
import net.blay09.mods.balm.world.item.DeferredItem;
import net.blay09.mods.craftingslots.CraftingSlots;
import net.minecraft.network.chat.Component;

public class ModItems {
    public static DeferredItem portableCraftingTable;
    public static DeferredItem inventoryCraftingTable;

    public static void initialize(BalmItemRegistrar items) {
        portableCraftingTable = items.register("portable_crafting", PortableCraftingItem::new).asDeferredItem();
        inventoryCraftingTable = items.register("inventory_crafting", InventoryCraftingItem::new).asDeferredItem();
    }

    public static void initialize(BalmCreativeModeTabRegistrar creativeModeTabs) {
        creativeModeTabs.register(CraftingSlots.MOD_ID, (id, builder) ->
                builder.title(Component.translatable(id.toLanguageKey("itemGroup")))
                        .icon(() -> ModItems.inventoryCraftingTable.createStack())
                        .displayItems((displayParameters, output) -> {
                            output.accept(ModItems.portableCraftingTable.createStack());
                            output.accept(ModItems.inventoryCraftingTable.createStack());
                        })
        );
    }

}
