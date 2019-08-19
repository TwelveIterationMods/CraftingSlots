package net.blay09.mods.craftingcraft;

import net.blay09.mods.craftingcraft.client.ClientProxy;
import net.blay09.mods.craftingcraft.client.ModKeybindings;
import net.blay09.mods.craftingcraft.item.ModItems;
import net.blay09.mods.craftingcraft.network.NetworkHandler;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(CraftingCraft.MOD_ID)
@Mod.EventBusSubscriber
public class CraftingCraft {

    public static final String MOD_ID = "craftingcraft";

    public static CommonProxy proxy;

    public static final ItemGroup itemGroup = new ItemGroup(MOD_ID) {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ModItems.inventoryCraftingTable);
        }
    };

    public CraftingCraft() {
        proxy = DistExecutor.runForDist(() -> ClientProxy::new, () -> CommonProxy::new);

        DeferredWorkQueue.runLater(NetworkHandler::init);

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setupClient);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addGenericListener(ContainerType.class, this::registerContainers);
        FMLJavaModLoadingContext.get().getModEventBus().addGenericListener(Item.class, this::registerItems);
    }

    public void registerItems(RegistryEvent.Register<Item> event) {
        ModItems.register(event.getRegistry());
    }

    public void registerContainers(RegistryEvent.Register<ContainerType<?>> event) {
        ModContainers.register(event.getRegistry());
    }

    private void setup(FMLCommonSetupEvent event) {
        if (ModList.get().isLoaded("craftingtweaks")) {
            try {
                Class.forName("net.blay09.mods.craftingcraft.addon.CraftingTweaksAddon").newInstance();
            } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void setupClient(FMLClientSetupEvent event) {
        ModScreens.register();
        ModKeybindings.register();
    }

}
