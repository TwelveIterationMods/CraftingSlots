package net.blay09.mods.craftingcraft.block;

import net.blay09.mods.craftingcraft.CraftingCraft;
import net.blay09.mods.craftingcraft.net.GuiHandler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.property.ExtendedBlockState;
import net.minecraftforge.common.property.IExtendedBlockState;
import net.minecraftforge.common.property.IUnlistedProperty;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockCraftingTableFrame extends BlockContainer {

    public static final String name = "craftingTableFrame";
    public static final IUnlistedProperty<IBlockState> VISUAL_BLOCK = new IUnlistedProperty<IBlockState>() {
        @Override
        public String getName() {
            return "visualBlock";
        }

        @Override
        public boolean isValid(IBlockState value) {
            return true;
        }

        @Override
        public Class<IBlockState> getType() {
            return IBlockState.class;
        }

        @Override
        public String valueToString(IBlockState value) {
            return value.toString();
        }
    };

    public BlockCraftingTableFrame() {
        super(Material.wood);
        setUnlocalizedName(CraftingCraft.MOD_ID + ":" + name);
        setHardness(1f);
        setResistance(10f);
        setCreativeTab(CraftingCraft.creativeTab);
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public EnumWorldBlockLayer getBlockLayer() {
        return EnumWorldBlockLayer.CUTOUT;
    }

    @Override
    public int getRenderType() {
        return 3;
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer entityPlayer, EnumFacing side, float hitX, float hitY, float hitZ) {
        if(!world.isRemote) {
            ItemStack itemStack = entityPlayer.getHeldItem();
            TileEntityCraftingTableFrame tileEntity = (TileEntityCraftingTableFrame) world.getTileEntity(pos);
            if(tileEntity.getVisualBlock() == null && itemStack != null) {
                if(itemStack.getItem() instanceof ItemBlock) {
                    Block visualBlock = ((ItemBlock) itemStack.getItem()).block;
                    if (visualBlock.getRenderType() == 1 || visualBlock.getRenderType() == 3 || visualBlock == Blocks.glass) {
                        int metadata = itemStack.getItem().getMetadata(itemStack.getItemDamage());
                        if (visualBlock == Blocks.grass) {
                            visualBlock = Blocks.dirt;
                        }
                        itemStack.splitStack(1);
                        tileEntity.setVisualBlock(visualBlock.getStateFromMeta(metadata));
                        return true;
                    }
                }/* else if(FluidContainerRegistry.isFilledContainer(itemStack)) {
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
                        tileEntity.setVisualBlock(fluidBlock.getStateFromMeta(0));
                        return true;
                    }
                }*/
            }
            entityPlayer.openGui(CraftingCraft.instance, GuiHandler.GUI_STONE_CRAFTING_TABLE, world, pos.getX(), pos.getY(), pos.getZ());
        }
        return true;
    }

    @Override
    protected BlockState createBlockState() {
        IProperty[] listedProperties = new IProperty[0];
        IUnlistedProperty[] unlistedProperties = new IUnlistedProperty[] {VISUAL_BLOCK};
        return new ExtendedBlockState(this, listedProperties, unlistedProperties);
    }

    @Override
    public IBlockState getExtendedState(IBlockState state, IBlockAccess world, BlockPos pos) {
        if(state instanceof IExtendedBlockState) {
            IExtendedBlockState extendedState = (IExtendedBlockState) state;
            TileEntityCraftingTableFrame tileEntity = (TileEntityCraftingTableFrame) world.getTileEntity(pos);
            IBlockState visualBlockState = tileEntity.getVisualBlockState();
            extendedState = extendedState.withProperty(VISUAL_BLOCK, visualBlockState);
            return extendedState;
        }
        return state;
    }

    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state) {
        TileEntityCraftingTableFrame tileEntity = (TileEntityCraftingTableFrame) world.getTileEntity(pos);
        Block visualBlock = tileEntity.getVisualBlock();
        if (visualBlock != null && FluidRegistry.lookupFluidForBlock(visualBlock) == null) {
            EntityItem entityItem = new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(visualBlock, 1, visualBlock.damageDropped(tileEntity.getVisualBlockState())));
            world.spawnEntityInWorld(entityItem);
        }
        super.breakBlock(world, pos, state);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata) {
        return new TileEntityCraftingTableFrame();
    }

    @Override
    public int getLightValue(IBlockAccess world, BlockPos pos) {
        TileEntityCraftingTableFrame tileEntity = (TileEntityCraftingTableFrame) world.getTileEntity(pos);
        if(tileEntity != null &&tileEntity.getVisualBlock() != null) {
            return tileEntity.getVisualBlock().getLightValue();
        }
        return super.getLightValue();
    }

    @Override
    public int getLightOpacity(IBlockAccess world, BlockPos pos) {
        TileEntityCraftingTableFrame tileEntity = (TileEntityCraftingTableFrame) world.getTileEntity(pos);
        if(tileEntity != null && tileEntity.getVisualBlock() != null) {
            return tileEntity.getVisualBlock().getLightOpacity();
        }
        return super.getLightOpacity();
    }

    @Override
    public float getExplosionResistance(World world, BlockPos pos, Entity exploder, Explosion explosion) {
        TileEntityCraftingTableFrame tileEntity = (TileEntityCraftingTableFrame) world.getTileEntity(pos);
        if(tileEntity != null && tileEntity.getVisualBlock() != null) {
            return tileEntity.getVisualBlock().getExplosionResistance(exploder);
        }
        return super.getExplosionResistance(world, pos, exploder, explosion);
    }

    @SideOnly(Side.CLIENT)
    public void registerModels(ItemModelMesher mesher) {
        mesher.register(Item.getItemFromBlock(this), 0, new ModelResourceLocation(CraftingCraft.MOD_ID + ":craftingTableFrame", "inventory"));
    }

}
