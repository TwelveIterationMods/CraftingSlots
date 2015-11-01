package net.blay09.mods.craftcraft;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;

public class BlockStoneCraftingTable extends Block {

    private IIcon topIcon;
    private IIcon frontIcon;

    protected BlockStoneCraftingTable() {
        super(Material.rock);
        setBlockName("stoneCraftingTable");
        setHardness(2f);
        setResistance(10f);
        setCreativeTab(CraftCraft.creativeTab);
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int metadata) {
        return side == 1 ? topIcon : (side == 0 ? Blocks.planks.getBlockTextureFromSide(side) : (side != 2 && side != 4 ? blockIcon : frontIcon));
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        blockIcon = iconRegister.registerIcon("craftcraft:stoneCraftingTable_side");
        topIcon = iconRegister.registerIcon("craftcraft:stoneCraftingTable_top");
        frontIcon = iconRegister.registerIcon("craftcraft:stoneCraftingTable_front");
    }

}
