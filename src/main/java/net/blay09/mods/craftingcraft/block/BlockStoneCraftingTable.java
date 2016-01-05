package net.blay09.mods.craftingcraft.block;

import net.blay09.mods.craftingcraft.CraftingCraft;
import net.blay09.mods.craftingcraft.net.GuiHandler;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class BlockStoneCraftingTable extends BlockContainer {

    public enum EnumType implements IStringSerializable {
        STONE(0),
        NETHER(1);

        private final int meta;

        EnumType(int meta) {
            this.meta = meta;
        }

        public int getMetadata() {
            return meta;
        }

        @Override
        public String getName() {
            return name();
        }

        @Override
        public String toString() {
            return name();
        }

        public static EnumType byMetadata(int meta) {
            if (meta == 1) {
                return NETHER;
            }
            return STONE;
        }

    }

    public static final PropertyEnum VARIANT = PropertyEnum.create("variant", EnumType.class);

    public static final String name = "stoneCraftingTable";

    public BlockStoneCraftingTable() {
        super(Material.rock);

        setUnlocalizedName(CraftingCraft.MOD_ID + ":" + name);
        setHardness(2f);
        setResistance(10f);
        setCreativeTab(CraftingCraft.creativeTab);
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return getDefaultState().withProperty(VARIANT, EnumType.byMetadata(meta));
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return ((EnumType) state.getValue(VARIANT)).getMetadata();
    }

    @Override
    protected BlockState createBlockState() {
        return new BlockState(this, VARIANT);
    }

    @Override
    public int getRenderType() {
        return 3;
    }

    @Override
    public void getSubBlocks(Item item, CreativeTabs creativeTab, List<ItemStack> list) {
        list.add(new ItemStack(item, 1, 0));
        list.add(new ItemStack(item, 1, 1));
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer entityPlayer, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (!world.isRemote) {
            entityPlayer.openGui(CraftingCraft.instance, GuiHandler.GUI_STONE_CRAFTING_TABLE, world, pos.getX(), pos.getY(), pos.getZ());
        }
        return true;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata) {
        return new TileEntityStoneCraftingTable();
    }

    @SideOnly(Side.CLIENT)
    public void registerModels(ItemModelMesher mesher) {
        ModelBakery.registerItemVariants(Item.getItemFromBlock(this), new ResourceLocation(CraftingCraft.MOD_ID, ":stoneCraftingTable"), new ResourceLocation(CraftingCraft.MOD_ID, ":netherCraftingTable"));

        mesher.register(Item.getItemFromBlock(this), 0, new ModelResourceLocation(CraftingCraft.MOD_ID + ":stoneCraftingTable", "inventory"));
        mesher.register(Item.getItemFromBlock(this), 1, new ModelResourceLocation(CraftingCraft.MOD_ID + ":netherCraftingTable", "inventory"));
    }

}
