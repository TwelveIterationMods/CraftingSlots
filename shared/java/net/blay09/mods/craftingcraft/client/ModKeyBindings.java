package net.blay09.mods.craftingcraft.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.blay09.mods.craftingcraft.client.screen.InventoryCraftingScreen;
import net.blay09.mods.craftingcraft.client.screen.PortableCraftingScreen;
import net.blay09.mods.craftingcraft.network.PortableCraftingMessage;
import net.blay09.mods.forbic.client.ForbicKeyBindings;
import net.blay09.mods.forbic.client.KeyConflictContext;
import net.blay09.mods.forbic.client.KeyModifier;
import net.blay09.mods.forbic.event.ForbicEvents;
import net.blay09.mods.forbic.network.ForbicNetworking;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import org.lwjgl.glfw.GLFW;

public class ModKeyBindings extends ForbicKeyBindings {

    private static KeyMapping keyPortableCrafting;
    private static KeyMapping keyBackToInventory;

    public static void initialize() {
        keyPortableCrafting = registerKeyBinding("key.craftingcraft.portable_crafting", KeyConflictContext.UNIVERSAL, KeyModifier.NONE, GLFW.GLFW_KEY_C, "key.categories.craftingcraft");
        keyBackToInventory = registerKeyBinding("key.craftingcraft.back_to_inventory", KeyConflictContext.GUI, KeyModifier.NONE, InputConstants.UNKNOWN.getValue(), "key.categories.craftingcraft");

        ForbicEvents.onScreenKeyPressed(ModKeyBindings::screenKeyPressed);
        ForbicEvents.onClientTicked(ModKeyBindings::clientTicked);
    }

    private static void clientTicked(Minecraft client) {
        if (isKeyDownIgnoreContext(keyPortableCrafting)) {
            if (client.player != null && client.screen == null) {
                ForbicNetworking.sendToServer(new PortableCraftingMessage());
            }
        }
    }

    private static boolean screenKeyPressed(Screen screen, int keyCode, int scanCode, int modifiers) {
        Minecraft client = Minecraft.getInstance();
        if (isActiveAndMatches(keyPortableCrafting, keyCode, scanCode)) {
            if (client.player != null && client.screen instanceof InventoryScreen) {
                ForbicNetworking.sendToServer(new PortableCraftingMessage());
            }
        }

        if (isActiveAndMatches(keyBackToInventory, keyCode, scanCode)) {
            if (client.player != null && (screen instanceof InventoryCraftingScreen || screen instanceof PortableCraftingScreen)) {
                client.player.closeContainer();
                client.setScreen(new InventoryScreen(client.player));
                return true;
            }
        }

        return false;
    }

}
