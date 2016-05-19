package net.blay09.mods.craftingcraft;

import net.blay09.mods.craftingcraft.block.BlockCraftingTableFrame;
import net.blay09.mods.craftingcraft.block.BlockStoneCraftingTable;
import net.blay09.mods.craftingcraft.block.TileEntityCraftingTableFrame;
import net.blay09.mods.craftingcraft.block.TileEntityStoneCraftingTable;
import net.blay09.mods.craftingcraft.item.ItemBlockStoneCraftingTable;
import net.blay09.mods.craftingcraft.item.ItemPortableCraftingTable;
import net.blay09.mods.craftingcraft.net.GuiHandler;
import net.blay09.mods.craftingcraft.net.NetworkHandler;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

import javax.annotation.Nonnull;

@Mod(modid = CraftingCraft.MOD_ID, name = "CraftingCraft",
    updateJSON = "http://balyware.com/new/forge_update.php?modid=" + CraftingCraft.MOD_ID)
public class CraftingCraft {

    public static final String MOD_ID = "craftingcraft";

    public static final CreativeTabs creativeTab = new CreativeTabs("craftingcraft") {
        @Override
        @Nonnull
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
        GameRegistry.register(portableCraftingTable);

        stoneCraftingTable = new BlockStoneCraftingTable();
        GameRegistry.register(stoneCraftingTable);
        GameRegistry.register(new ItemBlockStoneCraftingTable(stoneCraftingTable).setRegistryName(stoneCraftingTable.getRegistryName()));
        GameRegistry.registerTileEntity(TileEntityStoneCraftingTable.class, MOD_ID + ":" + BlockStoneCraftingTable.name);

        craftingTableFrame = new BlockCraftingTableFrame();
        GameRegistry.register(craftingTableFrame);
        GameRegistry.register(new ItemBlock(craftingTableFrame).setRegistryName(craftingTableFrame.getRegistryName()));
        GameRegistry.registerTileEntity(TileEntityCraftingTableFrame.class, MOD_ID + ":" + BlockCraftingTableFrame.name);

        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        GameRegistry.addShapedRecipe(new ItemStack(portableCraftingTable, 1, 0), " c", "s ", 'c', Blocks.CRAFTING_TABLE, 's', Items.STICK);
        GameRegistry.addShapelessRecipe(new ItemStack(portableCraftingTable, 1, 1), new ItemStack(portableCraftingTable, 1, 0));
        GameRegistry.addShapelessRecipe(new ItemStack(portableCraftingTable, 1, 0), new ItemStack(portableCraftingTable, 1, 1));
        GameRegistry.addShapedRecipe(new ItemStack(stoneCraftingTable, 1, 0), "sss", "scs", "sss", 's', Blocks.STONEBRICK, 'c', Blocks.CRAFTING_TABLE);
        GameRegistry.addShapedRecipe(new ItemStack(stoneCraftingTable, 1, 1), "nn", "nn", 'n', Blocks.NETHERRACK);
        GameRegistry.addShapedRecipe(new ItemStack(stoneCraftingTable, 1, 1), "nn", "nn", 'n', Blocks.NETHER_BRICK);
        GameRegistry.addShapedRecipe(new ItemStack(craftingTableFrame, 1, 0), "ggg", "gcg", "ggg", 'g', Blocks.GLASS_PANE, 'c', Blocks.CRAFTING_TABLE);

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
