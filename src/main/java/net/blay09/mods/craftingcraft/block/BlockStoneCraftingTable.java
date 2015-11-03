package net.blay09.mods.craftingcraft.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.blay09.mods.craftingcraft.CraftingCraft;
import net.blay09.mods.craftingcraft.net.GuiHandler;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import java.util.List;

public class BlockStoneCraftingTable extends BlockContainer {

    private final IIcon[] sideIcon = new IIcon[2];
    private final IIcon[] topIcon = new IIcon[2];
    private final IIcon[] frontIcon = new IIcon[2];

    public BlockStoneCraftingTable() {
        super(Material.rock);
        setBlockName("craftingcraft:stoneCraftingTable");
        setHardness(2f);
        setResistance(10f);
        setCreativeTab(CraftingCraft.creativeTab);
    }

    @Override
    public void getSubBlocks(Item item, CreativeTabs creativeTab, List list) {
        list.add(new ItemStack(item, 1, 0));
        list.add(new ItemStack(item, 1, 1));
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int metadata) {
        if(metadata > 1) {
            metadata = 0;
        }
        return side == 1 ? topIcon[metadata] : (side == 0 ? Blocks.planks.getBlockTextureFromSide(side) : (side != 2 && side != 4 ? sideIcon[metadata] : frontIcon[metadata]));
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        sideIcon[0] = iconRegister.registerIcon("craftingcraft:stoneCraftingTable_side");
        topIcon[0] = iconRegister.registerIcon("craftingcraft:stoneCraftingTable_top");
        frontIcon[0] = iconRegister.registerIcon("craftingcraft:stoneCraftingTable_front");

        sideIcon[1] = iconRegister.registerIcon("craftingcraft:netherCraftingTable_side");
        topIcon[1] = iconRegister.registerIcon("craftingcraft:netherCraftingTable_top");
        frontIcon[1] = iconRegister.registerIcon("craftingcraft:netherCraftingTable_front");
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityPlayer, int side, float hitX, float hitY, float hitZ) {
        if(!world.isRemote) {
            entityPlayer.openGui(CraftingCraft.instance, GuiHandler.GUI_STONE_CRAFTING_TABLE, world, x, y, z);
        }
        return true;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata) {
        return new TileEntityStoneCraftingTable();
    }
}
