package net.blay09.mods.craftingcraft.client;

import net.blay09.mods.craftingcraft.CommonProxy;
import net.blay09.mods.craftingcraft.CraftingCraft;
import net.blay09.mods.craftingcraft.client.gui.GuiPortableCrafting;
import net.blay09.mods.craftingcraft.client.gui.GuiInventoryCrafting;
import net.blay09.mods.craftingcraft.net.MessagePortableCrafting;
import net.blay09.mods.craftingcraft.net.NetworkHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;

@SuppressWarnings("unused")
public class ClientProxy extends CommonProxy {

    private static KeyBinding keyPortableCrafting;
    private static KeyBinding keyBackToInventory;

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);

        MinecraftForge.EVENT_BUS.register(this);
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);

        RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
        CraftingCraft.portableCraftingTable.registerModels(renderItem.getItemModelMesher());

        keyPortableCrafting = new KeyBinding("key.craftingcraft.portable_crafting", Keyboard.KEY_C, "key.categories.craftingcraft");
        keyBackToInventory = new KeyBinding("key.craftingcraft.back_to_inventory", 0, "key.categories.craftingcraft");
        ClientRegistry.registerKeyBinding(keyPortableCrafting);
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void onKeyboardEvent(GuiScreenEvent.KeyboardInputEvent.Pre event) {
        if(Keyboard.getEventKeyState()) {
            if(keyPortableCrafting.isActiveAndMatches(Keyboard.getEventKey())) {
                if (Minecraft.getMinecraft().currentScreen instanceof GuiInventory) {
                    NetworkHandler.instance.sendToServer(new MessagePortableCrafting());
                }
            } else if(keyBackToInventory.isActiveAndMatches(Keyboard.getEventKey())) {
                if (Minecraft.getMinecraft().currentScreen instanceof GuiInventoryCrafting || Minecraft.getMinecraft().currentScreen instanceof GuiPortableCrafting) {
                    Minecraft.getMinecraft().player.closeScreen();
                    Minecraft.getMinecraft().displayGuiScreen(new GuiInventory(Minecraft.getMinecraft().player));
                }
            }
        }
    }

    @SubscribeEvent
    public void onKeyboardEvent(InputEvent.KeyInputEvent event) {
        if(Keyboard.getEventKeyState() && keyPortableCrafting.isActiveAndMatches(Keyboard.getEventKey())) {
            NetworkHandler.instance.sendToServer(new MessagePortableCrafting());
        }
    }

}
