package net.blay09.mods.craftingcraft;

import net.blay09.mods.craftingcraft.item.ItemPortableCraftingTable;
import net.blay09.mods.craftingcraft.net.GuiHandler;
import net.blay09.mods.craftingcraft.net.NetworkHandler;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod(modid = CraftingCraft.MOD_ID, name = "CraftingCraft", acceptedMinecraftVersions = "[1.12]")
@Mod.EventBusSubscriber
public class CraftingCraft {

	public static final String MOD_ID = "craftingcraft";

	@Mod.Instance
	public static CraftingCraft instance;

	@SidedProxy(clientSide = "net.blay09.mods.craftingcraft.client.ClientProxy", serverSide = "net.blay09.mods.craftingcraft.CommonProxy")
	public static CommonProxy proxy;

	@GameRegistry.ObjectHolder(ItemPortableCraftingTable.name)
	public static final Item itemPortableCraftingTable = Items.AIR;

	public static final CreativeTabs creativeTab = new CreativeTabs(MOD_ID) {
		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(itemPortableCraftingTable, 1, 1);
		}
	};

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		proxy.preInit(event);
	}

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		event.getRegistry().registerAll(
				new ItemPortableCraftingTable()
		);
	}

	@SubscribeEvent
	public static void registerModels(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(itemPortableCraftingTable, 0, new ModelResourceLocation("craftingcraft:portable_crafting_table", "inventory"));
		ModelLoader.setCustomModelResourceLocation(itemPortableCraftingTable, 1, new ModelResourceLocation("craftingcraft:inventory_crafting_table", "inventory"));
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
		NetworkHandler.init();

		proxy.init(event);
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		event.buildSoftDependProxy("craftingtweaks", "net.blay09.mods.craftingcraft.addon.CraftingTweaksAddon");

		proxy.postInit(event);
	}

}
