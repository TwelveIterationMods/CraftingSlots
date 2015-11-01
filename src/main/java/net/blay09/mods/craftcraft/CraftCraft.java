package net.blay09.mods.craftcraft;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.blay09.mods.craftcraft.net.GuiHandler;
import net.blay09.mods.craftcraft.net.NetworkHandler;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

@Mod(modid = "craftcraft", name = "CraftCraft")
public class CraftCraft {

    public static final CreativeTabs creativeTab = new CreativeTabs("craftcraft:creativeTab") {
        @Override
        public Item getTabIconItem() {
            return portableCraftingTable;
        }
    };

    public static ItemPortableCraftingTable portableCraftingTable;
    public static BlockStoneCraftingTable stoneCraftingTable;

    @Mod.Instance
    public static CraftCraft instance;

    @SidedProxy(clientSide = "net.blay09.mods.craftcraft.client.ClientProxy", serverSide = "net.blay09.mods.craftcraft.CommonProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        portableCraftingTable = new ItemPortableCraftingTable();
        GameRegistry.registerItem(portableCraftingTable, "portableCraftingTable");

        stoneCraftingTable = new BlockStoneCraftingTable();
        GameRegistry.registerBlock(stoneCraftingTable, "stoneCraftingTable");

        GameRegistry.addShapedRecipe(new ItemStack(portableCraftingTable, 1, 0), " c", "s ", 'c', Blocks.crafting_table, 's', Items.stick);
        GameRegistry.addShapelessRecipe(new ItemStack(portableCraftingTable, 1, 1), new ItemStack(portableCraftingTable, 1, 0));
        GameRegistry.addShapelessRecipe(new ItemStack(portableCraftingTable, 1, 0), new ItemStack(portableCraftingTable, 1, 1));

        NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
        NetworkHandler.init();

        proxy.init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        event.buildSoftDependProxy("craftingtweaks", "net.blay09.mods.craftcraft.addon.CraftingTweaksAddon");
    }


}
