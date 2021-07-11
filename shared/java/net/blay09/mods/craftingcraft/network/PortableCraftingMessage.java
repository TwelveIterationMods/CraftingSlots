package net.blay09.mods.craftingcraft.network;

import net.blay09.mods.craftingcraft.container.InventoryCraftingMenu;
import net.blay09.mods.craftingcraft.container.PortableCraftingMenu;
import net.blay09.mods.craftingcraft.item.ModItems;
import net.blay09.mods.balm.network.BalmNetworking;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;

public class PortableCraftingMessage {

    /**
     * literally just to shut up IntelliJ about this being a utility class
     */
    private boolean dummy;

    public static void encode(PortableCraftingMessage message, FriendlyByteBuf buf) {
    }

    public static PortableCraftingMessage decode(FriendlyByteBuf buf) {
        return new PortableCraftingMessage();
    }

    public static void handle(ServerPlayer player, PortableCraftingMessage message) {
        if (player == null) {
            return;
        }

        ItemStack itemStack = findPortableCrafting(player.getInventory());
        if (itemStack.getItem() == ModItems.inventoryCraftingTable) {
            BalmNetworking.openGui(player, InventoryCraftingMenu.provider);
        } else if (itemStack.getItem() == ModItems.portableCraftingTable) {
            BalmNetworking.openGui(player, PortableCraftingMenu.provider);
        }
    }

    private static ItemStack findPortableCrafting(Inventory inventory) {
        for (int i = 0; i < inventory.getContainerSize(); i++) {
            ItemStack itemStack = inventory.getItem(i);
            if (!itemStack.isEmpty() && (itemStack.getItem() == ModItems.inventoryCraftingTable || itemStack.getItem() == ModItems.portableCraftingTable)) {
                return itemStack;
            }
        }

        return ItemStack.EMPTY;
    }

}
