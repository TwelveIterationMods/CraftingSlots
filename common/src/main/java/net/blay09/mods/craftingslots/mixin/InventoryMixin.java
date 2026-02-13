package net.blay09.mods.craftingslots.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.blay09.mods.craftingslots.internal.InventoryRecipePlacementFilter;
import net.minecraft.core.NonNullList;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Inventory.class)
public class InventoryMixin implements InventoryRecipePlacementFilter {

    @Unique
    private boolean craftingslots$ignoreInlineCraftingSlots;

    @WrapOperation(
            method = "findSlotMatchingCraftingIngredient",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/core/NonNullList;get(I)Ljava/lang/Object;")
    )
    public Object findSlotMatchingCraftingIngredient(NonNullList<?> list, int index, Operation<Object> original) {
        if (craftingslots$ignoreInlineCraftingSlots && craftingSlots$isInlineCraftingSlot(index)) {
            return ItemStack.EMPTY;
        }
        return original.call(list, index);
    }

    @Override
    public void craftingslots$setIgnoreInlineCraftingSlots(boolean value) {
        craftingslots$ignoreInlineCraftingSlots = value;
    }

    @Override
    public boolean craftingslots$getIgnoreInlineCraftingSlots() {
        return craftingslots$ignoreInlineCraftingSlots;
    }

    @Unique
    private static boolean craftingSlots$isInlineCraftingSlot(int slot) {
        return (slot >= 15 && slot <= 17)
                || (slot >= 24 && slot <= 26)
                || (slot >= 33 && slot <= 35);
    }
}
