package net.blay09.mods.craftingcraft.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.blay09.mods.craftingcraft.client.screen.InventoryCraftingScreen;
import net.blay09.mods.craftingcraft.client.screen.PortableCraftingScreen;
import net.blay09.mods.craftingcraft.network.PortableCraftingMessage;
import net.blay09.mods.forbic.client.ForbicKeybindings;
import net.blay09.mods.forbic.client.KeyConflictContext;
import net.blay09.mods.forbic.client.KeyModifier;
import net.blay09.mods.forbic.network.ForbicNetworking;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import org.lwjgl.glfw.GLFW;

public class ModKeybindings extends ForbicKeybindings {

    private static KeyMapping keyPortableCrafting;
    private static KeyMapping keyBackToInventory;

    public static void initialize() {
        keyPortableCrafting = registerKeyBinding("key.craftingcraft.portable_crafting", KeyConflictContext.UNIVERSAL, KeyModifier.NONE, GLFW.GLFW_KEY_C, "key.categories.craftingcraft");
        keyBackToInventory = registerKeyBinding("key.craftingcraft.back_to_inventory", KeyConflictContext.GUI, KeyModifier.NONE, InputConstants.UNKNOWN.getValue(), "key.categories.craftingcraft");

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (isActiveAndWasPressed(keyPortableCrafting)) {
                if (client.player != null && (client.screen == null || client.screen instanceof InventoryScreen)) {
                    ForbicNetworking.sendToServer(new PortableCraftingMessage());
                }
            }

            while (isActiveAndWasPressed(keyBackToInventory)) {
                if (client.player != null && (client.screen instanceof InventoryCraftingScreen || client.screen instanceof PortableCraftingScreen)) {
                    client.player.closeContainer();
                    client.setScreen(new InventoryScreen(client.player));
                }
            }
        });
    }

}
