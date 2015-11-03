package net.blay09.mods.craftcraft;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.blay09.mods.craftcraft.block.BlockCraftingTableFrame;
import net.blay09.mods.craftcraft.block.BlockStoneCraftingTable;
import net.blay09.mods.craftcraft.block.TileEntityCraftingTableFrame;
import net.blay09.mods.craftcraft.block.TileEntityStoneCraftingTable;
import net.blay09.mods.craftcraft.item.ItemBlockStoneCraftingTable;
import net.blay09.mods.craftcraft.item.ItemPortableCraftingTable;
import net.blay09.mods.craftcraft.net.GuiHandler;
import net.blay09.mods.craftcraft.net.NetworkHandler;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

@Mod(modid = "craftcraft", name = "CraftCraft")
public class CraftCraft {

    public static final CreativeTabs creativeTab = new CreativeTabs("craftcraft") {
        @Override
        public Item getTabIconItem() {
            return portableCraftingTable;
        }
    };

    public static final ItemPortableCraftingTable portableCraftingTable = new ItemPortableCraftingTable();
    public static final BlockStoneCraftingTable stoneCraftingTable = new BlockStoneCraftingTable();
    public static final BlockCraftingTableFrame craftingTableFrame = new BlockCraftingTableFrame();

    @Mod.Instance
    public static CraftCraft instance;

    @SidedProxy(clientSide = "net.blay09.mods.craftcraft.client.ClientProxy", serverSide = "net.blay09.mods.craftcraft.CommonProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        GameRegistry.registerItem(portableCraftingTable, "portableCraftingTable");
        GameRegistry.registerBlock(stoneCraftingTable, ItemBlockStoneCraftingTable.class, "stoneCraftingTable");
        GameRegistry.registerBlock(craftingTableFrame, "craftingTableFrame");
        GameRegistry.registerTileEntity(TileEntityStoneCraftingTable.class, "craftcraft:stoneCraftingTable");
        GameRegistry.registerTileEntity(TileEntityCraftingTableFrame.class, "craftcraft:craftingTableFrame");

        GameRegistry.addShapedRecipe(new ItemStack(portableCraftingTable, 1, 0), " c", "s ", 'c', Blocks.crafting_table, 's', Items.stick);
        GameRegistry.addShapelessRecipe(new ItemStack(portableCraftingTable, 1, 1), new ItemStack(portableCraftingTable, 1, 0));
        GameRegistry.addShapelessRecipe(new ItemStack(portableCraftingTable, 1, 0), new ItemStack(portableCraftingTable, 1, 1));
        GameRegistry.addShapedRecipe(new ItemStack(stoneCraftingTable, 1, 0), "sss", "scs", "sss", 's', Blocks.stonebrick, 'c', Blocks.crafting_table);
        GameRegistry.addShapedRecipe(new ItemStack(stoneCraftingTable, 1, 1), "nn", "nn", 'n', Blocks.netherrack);
        GameRegistry.addShapedRecipe(new ItemStack(stoneCraftingTable, 1, 1), "nn", "nn", 'n', Blocks.nether_brick);
        GameRegistry.addShapedRecipe(new ItemStack(craftingTableFrame, 1, 0), "ggg", "gcg", "ggg", 'g', Blocks.glass_pane, 'c', Blocks.crafting_table);

        NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
        NetworkHandler.init();

        proxy.init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        event.buildSoftDependProxy("craftingtweaks", "net.blay09.mods.craftcraft.addon.CraftingTweaksAddon");
    }

}
