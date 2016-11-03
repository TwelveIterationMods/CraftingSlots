package net.blay09.mods.craftingcraft.block;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class TileEntityCraftingTableFrame extends TileEntityStoneCraftingTable {

    private IBlockState visualBlockState;

    @Override
    public void readFromNBT(NBTTagCompound tagCompound) {
        super.readFromNBT(tagCompound);
        if(tagCompound.hasKey("VisualBlock")) {
            String blockName = tagCompound.getString("VisualBlock");
            Block visualBlock = Block.REGISTRY.getObject(new ResourceLocation(blockName));
            int visualMetadata = tagCompound.getByte("VisualBlockMeta");
            visualBlockState = visualBlock.getStateFromMeta(visualMetadata);
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound tagCompound) {
        super.writeToNBT(tagCompound);
        if(visualBlockState != null && visualBlockState.getBlock().getRegistryName() != null) {
            tagCompound.setString("VisualBlock", visualBlockState.getBlock().getRegistryName().toString());
            tagCompound.setByte("VisualBlockMeta", (byte) visualBlockState.getBlock().getMetaFromState(visualBlockState));
        }
        return tagCompound;
    }

    @Override
    public NBTTagCompound getUpdateTag() {
        NBTTagCompound tagCompound = super.getUpdateTag();
        writeToNBT(tagCompound);
        return tagCompound;
    }

    @Nullable
    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        NBTTagCompound tagCompound = new NBTTagCompound();
        writeToNBT(tagCompound);
        return new SPacketUpdateTileEntity(pos, 0, tagCompound);
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
        readFromNBT(pkt.getNbtCompound());
        IBlockState blockState = worldObj.getBlockState(pos);
        worldObj.markAndNotifyBlock(pos, worldObj.getChunkFromBlockCoords(pos), blockState, blockState, 1 | 2);
    }

    @Nullable
    public IBlockState getVisualBlock() {
        return visualBlockState;
    }

    public void setVisualBlock(@Nullable IBlockState visualBlockState) {
        this.visualBlockState = visualBlockState;
        markDirty();
        IBlockState blockState = worldObj.getBlockState(pos);
        worldObj.markAndNotifyBlock(pos, worldObj.getChunkFromBlockCoords(pos), blockState, blockState, 1 | 2);
    }
}
