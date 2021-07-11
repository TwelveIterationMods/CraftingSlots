package net.blay09.mods.craftingcraft.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.blay09.mods.balm.client.keybinds.BalmKeyMappings;
import net.blay09.mods.balm.client.keybinds.KeyConflictContext;
import net.blay09.mods.balm.client.keybinds.KeyModifier;
import net.blay09.mods.balm.event.client.BalmClientEvents;
import net.blay09.mods.craftingcraft.client.screen.InventoryCraftingScreen;
import net.blay09.mods.craftingcraft.client.screen.PortableCraftingScreen;
import net.blay09.mods.craftingcraft.network.PortableCraftingMessage;
import net.blay09.mods.balm.network.BalmNetworking;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import org.lwjgl.glfw.GLFW;

public class ModKeyBindings extends BalmKeyMappings {

    private static KeyMapping keyPortableCrafting;
    private static KeyMapping keyBackToInventory;

    public static void initialize() {
        keyPortableCrafting = registerKeyMapping("key.craftingcraft.portable_crafting", KeyConflictContext.UNIVERSAL, KeyModifier.NONE, GLFW.GLFW_KEY_C, "key.categories.craftingcraft");
        keyBackToInventory = registerKeyMapping("key.craftingcraft.back_to_inventory", KeyConflictContext.GUI, KeyModifier.NONE, InputConstants.UNKNOWN.getValue(), "key.categories.craftingcraft");

        BalmClientEvents.onScreenKeyPressed(ModKeyBindings::screenKeyPressed);
        BalmClientEvents.onClientTicked(ModKeyBindings::clientTicked);
    }

    private static void clientTicked(Minecraft client) {
        if (isKeyDownIgnoreContext(keyPortableCrafting)) {
            if (client.player != null && client.screen == null) {
                BalmNetworking.sendToServer(new PortableCraftingMessage());
            }
        }
    }

    private static boolean screenKeyPressed(Screen screen, int keyCode, int scanCode, int modifiers) {
        Minecraft client = Minecraft.getInstance();
        if (isActiveAndMatches(keyPortableCrafting, keyCode, scanCode)) {
            if (client.player != null && client.screen instanceof InventoryScreen) {
                BalmNetworking.sendToServer(new PortableCraftingMessage());
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
