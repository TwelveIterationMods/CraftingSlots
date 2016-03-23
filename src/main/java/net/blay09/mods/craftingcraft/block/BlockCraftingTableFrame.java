package net.blay09.mods.craftingcraft.block;

import net.blay09.mods.craftingcraft.CraftingCraft;
import net.blay09.mods.craftingcraft.net.GuiHandler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.property.ExtendedBlockState;
import net.minecraftforge.common.property.IExtendedBlockState;
import net.minecraftforge.common.property.IUnlistedProperty;
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
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }

    @Override
    public boolean canRenderInLayer(BlockRenderLayer layer) {
        return layer == BlockRenderLayer.CUTOUT;
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer entityPlayer, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
        if(!world.isRemote) {
            TileEntityCraftingTableFrame tileEntity = (TileEntityCraftingTableFrame) world.getTileEntity(pos);
            if(tileEntity.getVisualBlock() == null && heldItem != null) {
                if(heldItem.getItem() instanceof ItemBlock) {
                    Block visualBlock = ((ItemBlock) heldItem.getItem()).block;
                    if (visualBlock == Blocks.grass) {
                        visualBlock = Blocks.dirt;
                    }
                    int metadata = heldItem.getItem().getMetadata(heldItem.getItemDamage());
                    IBlockState visualState = visualBlock.getStateFromMeta(metadata);
                    if (visualBlock.getRenderType(visualState) == EnumBlockRenderType.MODEL || visualBlock == Blocks.glass) {
                        heldItem.splitStack(1);
                        tileEntity.setVisualBlock(visualState);
                        return true;
                    }
                }
            }
            entityPlayer.openGui(CraftingCraft.instance, GuiHandler.GUI_STONE_CRAFTING_TABLE, world, pos.getX(), pos.getY(), pos.getZ());
        }
        return true;
    }

    @Override
    protected BlockStateContainer createBlockState() {
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
    public int getLightValue(IBlockState state, IBlockAccess world, BlockPos pos) {
        TileEntityCraftingTableFrame tileEntity = (TileEntityCraftingTableFrame) world.getTileEntity(pos);
        if(tileEntity != null &&tileEntity.getVisualBlock() != null) {
            return tileEntity.getVisualBlock().getLightValue(state);
        }
        return super.getLightValue(state, world, pos);
    }

    @Override
    public int getLightOpacity(IBlockState state, IBlockAccess world, BlockPos pos) {
        TileEntityCraftingTableFrame tileEntity = (TileEntityCraftingTableFrame) world.getTileEntity(pos);
        if(tileEntity != null && tileEntity.getVisualBlock() != null) {
            return tileEntity.getVisualBlock().getLightOpacity(state);
        }
        return super.getLightOpacity(state, world, pos);
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
