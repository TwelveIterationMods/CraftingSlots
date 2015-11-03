package net.blay09.mods.craftingcraft.client;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.blay09.mods.craftingcraft.CommonProxy;
import net.blay09.mods.craftingcraft.net.MessagePortableCrafting;
import net.blay09.mods.craftingcraft.net.NetworkHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;

public class ClientProxy extends CommonProxy {

    private static KeyBinding keyPortableCrafting;

    private boolean wasKeyDown;

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
        keyPortableCrafting = new KeyBinding("key.craftingcraft.portableCrafting", Keyboard.KEY_C, "key.categories.craftingcraft");
        ClientRegistry.registerKeyBinding(keyPortableCrafting);

        RenderingRegistry.registerBlockHandler(BlockRendererCraftingTableFrame.RENDER_ID, new BlockRendererCraftingTableFrame());

        FMLCommonHandler.instance().bus().register(this);
    }

    @SubscribeEvent
    public void clientTick(TickEvent.ClientTickEvent event) {
        if(keyPortableCrafting.getKeyCode() > 0 && Keyboard.isKeyDown(keyPortableCrafting.getKeyCode())) {
            if(!wasKeyDown) {
                if(Minecraft.getMinecraft().currentScreen == null || Minecraft.getMinecraft().currentScreen instanceof GuiInventory) {
                    NetworkHandler.instance.sendToServer(new MessagePortableCrafting());
                }
                wasKeyDown = true;
            }
        } else {
            wasKeyDown = false;
        }
    }

}
