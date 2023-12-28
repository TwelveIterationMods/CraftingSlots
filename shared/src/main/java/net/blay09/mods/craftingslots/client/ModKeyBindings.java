package net.blay09.mods.craftingslots.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.blay09.mods.balm.api.Balm;
import net.blay09.mods.balm.api.client.BalmClient;
import net.blay09.mods.balm.api.client.keymappings.BalmKeyMappings;
import net.blay09.mods.balm.api.client.keymappings.KeyConflictContext;
import net.blay09.mods.balm.api.client.keymappings.KeyModifier;
import net.blay09.mods.balm.api.event.TickPhase;
import net.blay09.mods.balm.api.event.TickType;
import net.blay09.mods.balm.api.event.client.screen.ScreenKeyEvent;
import net.blay09.mods.craftingslots.client.screen.InventoryCraftingScreen;
import net.blay09.mods.craftingslots.client.screen.PortableCraftingScreen;
import net.blay09.mods.craftingslots.network.PortableCraftingMessage;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import org.lwjgl.glfw.GLFW;

public class ModKeyBindings {

    private static KeyMapping keyPortableCrafting;
    private static KeyMapping keyBackToInventory;

    public static void initialize(BalmKeyMappings keyMappings) {
        keyPortableCrafting = keyMappings.registerKeyMapping("key.craftingslots.portable_crafting", KeyConflictContext.UNIVERSAL, KeyModifier.NONE, GLFW.GLFW_KEY_C, "key.categories.craftingslots");
        keyBackToInventory = keyMappings.registerKeyMapping("key.craftingslots.back_to_inventory", KeyConflictContext.GUI, KeyModifier.NONE, InputConstants.UNKNOWN.getValue(), "key.categories.craftingslots");

        Balm.getEvents().onEvent(ScreenKeyEvent.Press.Post.class, ModKeyBindings::screenKeyPressed);
        Balm.getEvents().onTickEvent(TickType.Client, TickPhase.End, ModKeyBindings::clientTicked);
    }

    private static void clientTicked(Minecraft client) {
        if (BalmClient.getKeyMappings().isKeyDownIgnoreContext(keyPortableCrafting)) {
            if (client.player != null && client.screen == null) {
                Balm.getNetworking().sendToServer(new PortableCraftingMessage());
            }
        }
    }

    private static boolean screenKeyPressed(ScreenKeyEvent.Press event) {
        Screen screen = event.getScreen();
        int key = event.getKey();
        int scanCode = event.getScanCode();
        Minecraft client = Minecraft.getInstance();
        if (BalmClient.getKeyMappings().isActiveAndMatches(keyPortableCrafting, key, scanCode)) {
            if (client.player != null && client.screen instanceof InventoryScreen) {
                Balm.getNetworking().sendToServer(new PortableCraftingMessage());
            }
        }

        if (BalmClient.getKeyMappings().isActiveAndMatches(keyBackToInventory, key, scanCode)) {
            if (client.player != null && (screen instanceof InventoryCraftingScreen || screen instanceof PortableCraftingScreen)) {
                client.player.closeContainer();
                client.setScreen(new InventoryScreen(client.player));
                return true;
            }
        }

        return false;
    }

}
