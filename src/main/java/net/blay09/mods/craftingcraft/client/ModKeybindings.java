package net.blay09.mods.craftingcraft.client;

import net.blay09.mods.craftingcraft.CraftingCraft;
import net.blay09.mods.craftingcraft.client.gui.InventoryCraftingScreen;
import net.blay09.mods.craftingcraft.client.gui.PortableCraftingScreen;
import net.blay09.mods.craftingcraft.network.NetworkHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = CraftingCraft.MOD_ID)
public class ModKeybindings {

    private static KeyBinding keyPortableCrafting;
    private static KeyBinding keyBackToInventory;

    public static void register() {
        keyPortableCrafting = new KeyBinding("key.craftingcraft.portable_crafting", Keyboard.KEY_C, "key.categories.craftingcraft");
        ClientRegistry.registerKeyBinding(keyPortableCrafting);
        keyBackToInventory = new KeyBinding("key.craftingcraft.back_to_inventory", 0, "key.categories.craftingcraft");
        ClientRegistry.registerKeyBinding(keyBackToInventory);
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onKeyboardEvent(GuiScreenEvent.KeyboardInputEvent.Pre event) {
        if(Keyboard.getEventKeyState()) {
            if(keyPortableCrafting.isActiveAndMatches(Keyboard.getEventKey())) {
                if (Minecraft.getMinecraft().currentScreen instanceof GuiInventory) {
                    NetworkHandler.instance.sendToServer(new MessagePortableCrafting());
                }
            } else if(keyBackToInventory.isActiveAndMatches(Keyboard.getEventKey())) {
                if (Minecraft.getMinecraft().currentScreen instanceof InventoryCraftingScreen || Minecraft.getMinecraft().currentScreen instanceof PortableCraftingScreen) {
                    Minecraft.getMinecraft().player.closeScreen();
                    Minecraft.getMinecraft().displayGuiScreen(new GuiInventory(Minecraft.getMinecraft().player));
                }
            }
        }
    }

    @SubscribeEvent
    public static void onKeyboardEvent(InputEvent.KeyInputEvent event) {
        if(Keyboard.getEventKeyState() && keyPortableCrafting.isActiveAndMatches(Keyboard.getEventKey())) {
            NetworkHandler.instance.sendToServer(new MessagePortableCrafting());
        }
    }
}
