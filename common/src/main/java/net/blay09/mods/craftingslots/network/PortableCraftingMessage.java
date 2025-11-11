package net.blay09.mods.craftingslots.network;

import net.blay09.mods.balm.api.Balm;
import net.blay09.mods.craftingslots.CraftingSlots;
import net.blay09.mods.craftingslots.menu.InventoryCraftingMenu;
import net.blay09.mods.craftingslots.menu.PortableCraftingMenu;
import net.blay09.mods.craftingslots.item.ModItems;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;

import java.util.function.Predicate;

public class PortableCraftingMessage implements CustomPacketPayload {

    public static final PortableCraftingMessage INSTANCE = new PortableCraftingMessage();
    public static final Type<PortableCraftingMessage> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(CraftingSlots.MOD_ID, "portable_crafting"));
    public static final StreamCodec<RegistryFriendlyByteBuf, PortableCraftingMessage> STREAM_CODEC = StreamCodec.unit(INSTANCE);

    private PortableCraftingMessage() {
    }

    public static void handle(ServerPlayer player, PortableCraftingMessage message) {
        if (player == null) {
            return;
        }

        ItemStack itemStack = findPortableCrafting(player.getInventory());
        if (itemStack.getItem() == ModItems.inventoryCraftingTable && !(player.containerMenu instanceof InventoryCraftingMenu)) {
            Balm.getNetworking().openMenu(player, InventoryCraftingMenu.provider);
        } else if (itemStack.getItem() == ModItems.portableCraftingTable && !(player.containerMenu instanceof PortableCraftingMenu)) {
            Balm.getNetworking().openMenu(player, PortableCraftingMenu.provider);
        }
    }

    private static ItemStack findPortableCrafting(Inventory inventory) {
        final Predicate<ItemStack> predicate = itemStack -> itemStack.getItem() == ModItems.inventoryCraftingTable || itemStack.getItem() == ModItems.portableCraftingTable;
        final var charm = Balm.getModSupport().trinkets().findEquipped(inventory.player, predicate);
        if (!charm.isEmpty()) {
            return charm;
        }

        for (int i = 0; i < inventory.getContainerSize(); i++) {
            final var itemStack = inventory.getItem(i);
            if (predicate.test(itemStack)) {
                return itemStack;
            }
        }

        return ItemStack.EMPTY;
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
