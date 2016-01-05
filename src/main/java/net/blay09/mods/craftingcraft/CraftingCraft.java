package net.blay09.mods.craftingcraft;

import net.blay09.mods.craftingcraft.block.BlockCraftingTableFrame;
import net.blay09.mods.craftingcraft.block.BlockStoneCraftingTable;
import net.blay09.mods.craftingcraft.block.TileEntityCraftingTableFrame;
import net.blay09.mods.craftingcraft.block.TileEntityStoneCraftingTable;
import net.blay09.mods.craftingcraft.item.ItemBlockCraftingTableFrame;
import net.blay09.mods.craftingcraft.item.ItemBlockStoneCraftingTable;
import net.blay09.mods.craftingcraft.item.ItemPortableCraftingTable;
import net.blay09.mods.craftingcraft.net.GuiHandler;
import net.blay09.mods.craftingcraft.net.NetworkHandler;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = CraftingCraft.MOD_ID, name = "CraftingCraft")
public class CraftingCraft {

    public static final String MOD_ID = "craftingcraft";

    public static final CreativeTabs creativeTab = new CreativeTabs("craftingcraft") {
        @Override
        public Item getTabIconItem() {
            return portableCraftingTable;
        }
    };

    public static ItemPortableCraftingTable portableCraftingTable;
    public static BlockStoneCraftingTable stoneCraftingTable;
    public static BlockCraftingTableFrame craftingTableFrame;

    @Mod.Instance
    public static CraftingCraft instance;

    @SidedProxy(clientSide = "net.blay09.mods.craftingcraft.client.ClientProxy", serverSide = "net.blay09.mods.craftingcraft.CommonProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        portableCraftingTable = new ItemPortableCraftingTable();
        GameRegistry.registerItem(portableCraftingTable, ItemPortableCraftingTable.name);

        stoneCraftingTable = new BlockStoneCraftingTable();
        GameRegistry.registerBlock(stoneCraftingTable, ItemBlockStoneCraftingTable.class, BlockStoneCraftingTable.name);
        GameRegistry.registerTileEntity(TileEntityStoneCraftingTable.class, MOD_ID + ":" + BlockStoneCraftingTable.name);

        craftingTableFrame = new BlockCraftingTableFrame();
        GameRegistry.registerBlock(craftingTableFrame, ItemBlockCraftingTableFrame.class, BlockCraftingTableFrame.name);
        GameRegistry.registerTileEntity(TileEntityCraftingTableFrame.class, MOD_ID + ":" + BlockCraftingTableFrame.name);

        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
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
        event.buildSoftDependProxy("craftingtweaks", "net.blay09.mods.craftingcraft.addon.CraftingTweaksAddon");

        proxy.postInit(event);
    }

}
