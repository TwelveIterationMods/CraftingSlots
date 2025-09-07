package net.blay09.mods.craftingslots.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.blay09.mods.balm.api.Balm;
import net.blay09.mods.craftingslots.client.screen.InventoryCraftingScreen;
import net.blay09.mods.craftingslots.client.screen.PortableCraftingScreen;
import net.blay09.mods.craftingslots.network.PortableCraftingMessage;
import net.blay09.mods.kuma.api.InputBinding;
import net.blay09.mods.kuma.api.Kuma;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;

import static net.blay09.mods.craftingslots.CraftingSlots.id;

public class ModKeyBindings {

    public static void initialize() {
        Kuma.createKeyMapping(id("portable_crafting"))
                .withDefault(InputBinding.key(InputConstants.KEY_C))
                .handleWorldInput(event -> {
                    if (Balm.getProxy().isConnected()) {
                        Balm.getNetworking().sendToServer(PortableCraftingMessage.INSTANCE);
                    }
                    return false;
                })
                .handleScreenInput(event -> {
                    if (Balm.getProxy().isConnected() && event.screen() instanceof AbstractContainerScreen<?>) {
                        Balm.getNetworking().sendToServer(PortableCraftingMessage.INSTANCE);
                    }
                    return false;
                })
                .build();

        Kuma.createKeyMapping(id("back_to_inventory"))
                .handleScreenInput(event -> {
                    final var client = Minecraft.getInstance();
                    if (client.player != null && (event.screen() instanceof InventoryCraftingScreen || event.screen() instanceof PortableCraftingScreen)) {
                        client.player.closeContainer();
                        client.setScreen(new InventoryScreen(client.player));
                        return true;
                    }
                    return false;
                })
                .build();
    }

}
