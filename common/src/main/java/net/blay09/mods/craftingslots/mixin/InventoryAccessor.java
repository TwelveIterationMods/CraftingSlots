package net.blay09.mods.craftingslots.mixin;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(Inventory.class)
public interface InventoryAccessor {
    @Invoker
    boolean callHasRemainingSpaceForItem(ItemStack slotStack, ItemStack itemStack);
}
