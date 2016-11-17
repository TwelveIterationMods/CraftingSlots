package net.blay09.mods.craftingcraft;

import net.blay09.mods.craftingcraft.item.ItemPortableCraftingTable;
import net.blay09.mods.craftingcraft.net.GuiHandler;
import net.blay09.mods.craftingcraft.net.NetworkHandler;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = CraftingCraft.MOD_ID, name = "CraftingCraft",
        acceptedMinecraftVersions = "[1.11]")
public class CraftingCraft {

    public static final String MOD_ID = "craftingcraft";

    public static final CreativeTabs creativeTab = new CreativeTabs(MOD_ID) {
        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(portableCraftingTable, 1, 1);
        }
    };

    public static ItemPortableCraftingTable portableCraftingTable;

    @Mod.Instance
    public static CraftingCraft instance;

    @SidedProxy(clientSide = "net.blay09.mods.craftingcraft.client.ClientProxy", serverSide = "net.blay09.mods.craftingcraft.CommonProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        portableCraftingTable = new ItemPortableCraftingTable();
        GameRegistry.register(portableCraftingTable);

        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        GameRegistry.addShapedRecipe(new ItemStack(portableCraftingTable, 1, 0), " c", "s ", 'c', Blocks.CRAFTING_TABLE, 's', Items.STICK);
        GameRegistry.addShapelessRecipe(new ItemStack(portableCraftingTable, 1, 1), new ItemStack(portableCraftingTable, 1, 0));
        GameRegistry.addShapelessRecipe(new ItemStack(portableCraftingTable, 1, 0), new ItemStack(portableCraftingTable, 1, 1));

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
