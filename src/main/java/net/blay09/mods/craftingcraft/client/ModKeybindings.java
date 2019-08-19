package net.blay09.mods.craftingcraft.client;

import net.blay09.mods.craftingcraft.CraftingCraft;
import net.blay09.mods.craftingcraft.client.gui.InventoryCraftingScreen;
import net.blay09.mods.craftingcraft.client.gui.PortableCraftingScreen;
import net.blay09.mods.craftingcraft.network.MessagePortableCrafting;
import net.blay09.mods.craftingcraft.network.NetworkHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.InventoryScreen;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.util.InputMappings;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.client.settings.KeyModifier;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

@Mod.EventBusSubscriber(modid = CraftingCraft.MOD_ID)
public class ModKeybindings {

    private static KeyBinding keyPortableCrafting;
    private static KeyBinding keyBackToInventory;

    public static void register() {
        keyPortableCrafting = new KeyBinding("key.craftingcraft.portable_crafting", KeyConflictContext.UNIVERSAL, KeyModifier.NONE, InputMappings.getInputByCode(GLFW.GLFW_KEY_C, 0), "key.categories.craftingcraft");
        ClientRegistry.registerKeyBinding(keyPortableCrafting);
        keyBackToInventory = new KeyBinding("key.craftingcraft.back_to_inventory", KeyConflictContext.GUI, KeyModifier.NONE, InputMappings.INPUT_INVALID, "key.categories.craftingcraft");
        ClientRegistry.registerKeyBinding(keyBackToInventory);
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onKeyboardEvent(GuiScreenEvent.KeyboardKeyPressedEvent event) {
        Minecraft minecraft = Minecraft.getInstance();
        if (keyPortableCrafting.isActiveAndMatches(InputMappings.getInputByCode(event.getKeyCode(), event.getScanCode()))) {
            if (minecraft.currentScreen instanceof InventoryScreen) {
                NetworkHandler.channel.sendToServer(new MessagePortableCrafting());
            }
        } else if (keyBackToInventory.isActiveAndMatches(InputMappings.getInputByCode(event.getKeyCode(), event.getScanCode()))) {
            if (minecraft.currentScreen instanceof InventoryCraftingScreen || minecraft.currentScreen instanceof PortableCraftingScreen) {
                minecraft.player.closeScreen();
                minecraft.displayGuiScreen(new InventoryScreen(minecraft.player));
            }
        }
    }

    @SubscribeEvent
    public static void onKeyboardEvent(InputEvent.KeyInputEvent event) {
        if (event.getAction() == GLFW.GLFW_PRESS && keyPortableCrafting.isActiveAndMatches(InputMappings.getInputByCode(event.getKey(), event.getScanCode()))) {
            NetworkHandler.channel.sendToServer(new MessagePortableCrafting());
        }
    }
}
