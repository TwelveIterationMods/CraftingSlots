package net.blay09.mods.craftingcraft.item;

import net.blay09.mods.craftingcraft.container.InventoryCraftingContainer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class InventoryCraftingItem extends PortableCraftingItem {

    public static final String name = "inventory_crafting";

    @Override
    protected INamedContainerProvider getCraftingContainerProvider() {
        return InventoryCraftingContainer.provider;
    }

}
