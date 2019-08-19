package net.blay09.mods.craftingcraft.item;

import net.blay09.mods.craftingcraft.container.InventoryCraftingContainer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class InventoryCraftingTableItem extends PortableCraftingTableItem {

    public static final String name = "inventory_crafting_table";

    @Override
    protected INamedContainerProvider getCraftingContainerProvider() {
        return new INamedContainerProvider() {
            @Override
            public ITextComponent getDisplayName() {
                return new TranslationTextComponent("container.craftingcraft:inventory_crafting");
            }

            @Override
            public Container createMenu(int windowId, PlayerInventory playerInventory, PlayerEntity playerEntity) {
                return new InventoryCraftingContainer(windowId, playerInventory);
            }
        };
    }

}
