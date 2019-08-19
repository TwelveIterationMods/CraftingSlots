package net.blay09.mods.craftingcraft.network;

import net.blay09.mods.craftingcraft.item.ModItems;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class MessagePortableCrafting {

    public static void handle(MessagePortableCrafting message, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            PlayerEntity player = context.getSender();
            if (player == null) {
                return;
            }

            for (int i = 0; i < player.inventory.getSizeInventory(); i++) {
                ItemStack itemStack = player.inventory.getStackInSlot(i);
                if (isPortableCraftingTable(itemStack)) {
                    // TODO PortableCraftingTableItem.openPortableCrafting(player, itemStack);
                }
            }
        });
    }

    private static boolean isPortableCraftingTable(ItemStack itemStack) {
        return !itemStack.isEmpty() && (itemStack.getItem() == ModItems.inventoryCraftingTable || itemStack.getItem() == ModItems.portableCraftingTable);
    }

}
