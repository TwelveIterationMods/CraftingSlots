package net.blay09.mods.craftingcraft.client;

import net.blay09.mods.craftingcraft.CommonProxy;
import net.blay09.mods.craftingcraft.CraftingCraft;
import net.blay09.mods.craftingcraft.client.gui.GuiFocusProvider;
import net.blay09.mods.craftingcraft.client.render.BlockModelCraftingTableFrame;
import net.blay09.mods.craftingcraft.net.MessagePortableCrafting;
import net.blay09.mods.craftingcraft.net.NetworkHandler;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.model.IFlexibleBakedModel;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Keyboard;

public class ClientProxy extends CommonProxy {

    private static KeyBinding keyPortableCrafting;

    private GuiFocusProvider focusProvider;
    private boolean wasKeyDown;

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
        ClientRegistry.registerKeyBinding(keyPortableCrafting);
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        focusProvider = (GuiFocusProvider) event.buildSoftDependProxy("NotEnoughItems", "net.blay09.mods.craftingcraft.addon.NEIFocusProvider").orNull();
    }

    @SubscribeEvent
    public void clientTick(TickEvent.ClientTickEvent event) {
        if(focusProvider == null || !focusProvider.isTextboxFocused()) {
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

    @SubscribeEvent
    public void onModelBakeEvent(ModelBakeEvent event) {
        Object object = event.modelRegistry.getObject(BlockModelCraftingTableFrame.modelResource);
        if(object != null) {
            IFlexibleBakedModel model = (IFlexibleBakedModel) object;
            BlockModelCraftingTableFrame customModel = new BlockModelCraftingTableFrame(model);
            event.modelRegistry.putObject(BlockModelCraftingTableFrame.modelResource, customModel);
        }
    }

}
