package net.blay09.mods.craftcraft.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.blay09.mods.craftcraft.CraftCraft;
import net.blay09.mods.craftcraft.client.BlockRendererCraftingTableFrame;
import net.blay09.mods.craftcraft.net.GuiHandler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;

import java.util.ArrayList;
import java.util.List;

public class BlockCraftingTableFrame extends BlockContainer {

    private IIcon sideIcon;
    private IIcon topIcon;
    private IIcon frontIcon;
    private IIcon bottomIcon;

    public BlockCraftingTableFrame() {
        super(Material.wood);
        setBlockName("craftcraft:craftingTableFrame");
        setHardness(1f);
        setResistance(10f);
        setCreativeTab(CraftCraft.creativeTab);
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public int getRenderType() {
        return BlockRendererCraftingTableFrame.RENDER_ID;
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int metadata) {
        return side == 1 ? topIcon : (side == 0 ? bottomIcon : (side != 2 && side != 4 ? sideIcon : frontIcon));
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        sideIcon = iconRegister.registerIcon("craftcraft:craftingTable_side");
        topIcon = iconRegister.registerIcon("craftcraft:craftingTable_top");
        frontIcon = iconRegister.registerIcon("craftcraft:craftingTable_front");
        bottomIcon = iconRegister.registerIcon("craftcraft:craftingTable_bottom");
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityPlayer, int side, float hitX, float hitY, float hitZ) {
        if(!world.isRemote) {
            ItemStack itemStack = entityPlayer.getHeldItem();
            TileEntityCraftingTableFrame tileEntity = (TileEntityCraftingTableFrame) world.getTileEntity(x, y, z);
            if(tileEntity.getVisualBlock() == null && itemStack != null) {
                if(itemStack.getItem() instanceof ItemBlock) {
                    Block visualBlock = ((ItemBlock) itemStack.getItem()).field_150939_a;
                    if (visualBlock.renderAsNormalBlock()) {
                        int metadata = itemStack.getItem().getMetadata(itemStack.getItemDamage());
                        if (visualBlock == Blocks.grass) {
                            visualBlock = Blocks.dirt;
                        }
                        itemStack.splitStack(1);
                        tileEntity.setVisualBlock(visualBlock, metadata);
                        return true;
                    }
                } else if(FluidContainerRegistry.isFilledContainer(itemStack)) {
                    Block fluidBlock = FluidContainerRegistry.getFluidForFilledItem(itemStack).getFluid().getBlock();
                    if(fluidBlock != null) {
                        ItemStack emptyStack = FluidContainerRegistry.drainFluidContainer(itemStack);
                        itemStack.splitStack(1);
                        if(itemStack.stackSize == 0) {
                            entityPlayer.inventory.setInventorySlotContents(entityPlayer.inventory.currentItem, emptyStack);
                        } else {
                            if(!entityPlayer.inventory.addItemStackToInventory(emptyStack)) {
                                entityPlayer.dropPlayerItemWithRandomChoice(emptyStack, false);
                            }
                        }
                        tileEntity.setVisualBlock(fluidBlock, 0);
                        return true;
                    }
                }
            }
            entityPlayer.openGui(CraftCraft.instance, GuiHandler.GUI_STONE_CRAFTING_TABLE, world, x, y, z);
        }
        return true;
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int metadata) {
        TileEntityCraftingTableFrame tileEntity = (TileEntityCraftingTableFrame) world.getTileEntity(x, y, z);
        Block visualBlock = tileEntity.getVisualBlock();
        if (visualBlock != null && FluidRegistry.lookupFluidForBlock(visualBlock) == null) {
            EntityItem entityItem = new EntityItem(world, x, y, z, new ItemStack(visualBlock, 1, visualBlock.damageDropped(tileEntity.getVisualMetadata())));
            world.spawnEntityInWorld(entityItem);
        }
        super.breakBlock(world, x, y, z, block, metadata);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata) {
        return new TileEntityCraftingTableFrame();
    }

    @Override
    public int getLightValue(IBlockAccess world, int x, int y, int z) {
        TileEntityCraftingTableFrame tileEntity = (TileEntityCraftingTableFrame) world.getTileEntity(x, y, z);
        if(tileEntity.getVisualBlock() != null) {
            return tileEntity.getVisualBlock().getLightValue();
        }
        return super.getLightValue();
    }

    @Override
    public int getLightOpacity(IBlockAccess world, int x, int y, int z) {
        TileEntityCraftingTableFrame tileEntity = (TileEntityCraftingTableFrame) world.getTileEntity(x, y, z);
        if(tileEntity.getVisualBlock() != null) {
            return tileEntity.getVisualBlock().getLightOpacity();
        }
        return super.getLightOpacity();
    }

    @Override
    public float getExplosionResistance(Entity entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ) {
        TileEntityCraftingTableFrame tileEntity = (TileEntityCraftingTableFrame) world.getTileEntity(x, y, z);
        if(tileEntity.getVisualBlock() != null) {
            return tileEntity.getVisualBlock().getExplosionResistance(entity);
        }
        return super.getExplosionResistance(entity, world, x, y, z, explosionX, explosionY, explosionZ);
    }
}
