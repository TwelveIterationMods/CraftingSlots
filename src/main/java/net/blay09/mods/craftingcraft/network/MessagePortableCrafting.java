package net.blay09.mods.craftingcraft.network;

import net.blay09.mods.craftingcraft.container.InventoryCraftingContainer;
import net.blay09.mods.craftingcraft.container.PortableCraftingContainer;
import net.blay09.mods.craftingcraft.item.ModItems;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.network.NetworkHooks;

import java.util.function.Supplier;

public class MessagePortableCrafting {

    public static void handle(MessagePortableCrafting message, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            ServerPlayerEntity player = context.getSender();
            if (player == null) {
                return;
            }

            ItemStack itemStack = findPortableCrafting(player.inventory);
            if (itemStack.getItem() == ModItems.inventoryCraftingTable) {
                NetworkHooks.openGui(player, InventoryCraftingContainer.provider);
            } else if (itemStack.getItem() == ModItems.portableCraftingTable) {
                NetworkHooks.openGui(player, PortableCraftingContainer.provider);
            }
        });
    }

    private static ItemStack findPortableCrafting(PlayerInventory inventory) {
        for (int i = 0; i < inventory.getSizeInventory(); i++) {
            ItemStack itemStack = inventory.getStackInSlot(i);
            if (!itemStack.isEmpty() && (itemStack.getItem() == ModItems.inventoryCraftingTable || itemStack.getItem() == ModItems.portableCraftingTable)) {
                return itemStack;
            }
        }

        return ItemStack.EMPTY;
    }

}
