package net.blay09.mods.craftingcraft.client;

import net.blay09.mods.craftingcraft.CommonProxy;
import net.blay09.mods.craftingcraft.CraftingCraft;
import net.blay09.mods.craftingcraft.client.gui.GuiCraftCrafting;
import net.blay09.mods.craftingcraft.client.gui.GuiInventoryCrafting;
import net.blay09.mods.craftingcraft.client.render.BlockModelCraftingTableFrame;
import net.blay09.mods.craftingcraft.net.MessagePortableCrafting;
import net.blay09.mods.craftingcraft.net.NetworkHandler;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.model.ModelLoader;
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

        StateMapperBase ignoreState = new StateMapperBase() {
            @Override
            protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
                return BlockModelCraftingTableFrame.modelResource;
            }
        };
        ModelLoader.setCustomStateMapper(CraftingCraft.craftingTableFrame, ignoreState);

        MinecraftForge.EVENT_BUS.register(this);
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);

        RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
        CraftingCraft.portableCraftingTable.registerModels(renderItem.getItemModelMesher());
        CraftingCraft.stoneCraftingTable.registerModels(renderItem.getItemModelMesher());
        CraftingCraft.craftingTableFrame.registerModels(renderItem.getItemModelMesher());

        keyPortableCrafting = new KeyBinding("key.craftingcraft.portableCrafting", Keyboard.KEY_C, "key.categories.craftingcraft");
        keyBackToInventory = new KeyBinding("key.craftingcraft.backToInventory", 0, "key.categories.craftingcraft");
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
                if (Minecraft.getMinecraft().currentScreen instanceof GuiInventoryCrafting || Minecraft.getMinecraft().currentScreen instanceof GuiCraftCrafting) {
                    Minecraft.getMinecraft().thePlayer.closeScreen();
                    Minecraft.getMinecraft().displayGuiScreen(new GuiInventory(Minecraft.getMinecraft().thePlayer));
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

    @SubscribeEvent
    public void onModelBakeEvent(ModelBakeEvent event) {
        Object object = event.getModelRegistry().getObject(BlockModelCraftingTableFrame.modelResource);
        if(object != null) {
            BlockModelCraftingTableFrame customModel = new BlockModelCraftingTableFrame((IBakedModel) object);
            event.getModelRegistry().putObject(BlockModelCraftingTableFrame.modelResource, customModel);
        }
    }

}
