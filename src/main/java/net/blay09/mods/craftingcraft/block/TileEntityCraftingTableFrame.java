package net.blay09.mods.craftingcraft.block;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntityCraftingTableFrame extends TileEntityStoneCraftingTable {

    private Block visualBlock;
    private IBlockState visualBlockState;

    @Override
    public void readFromNBT(NBTTagCompound tagCompound) {
        super.readFromNBT(tagCompound);
        String blockModId = tagCompound.getString("VisualBlockModId");
        String blockName = tagCompound.getString("VisualBlockName");
        visualBlock = GameRegistry.findBlock(blockModId, blockName);
        int visualMetadata = tagCompound.getByte("VisualMetadata");
        visualBlockState = visualBlock.getStateFromMeta(visualMetadata);
    }

    @Override
    public void writeToNBT(NBTTagCompound tagCompound) {
        super.writeToNBT(tagCompound);
        GameRegistry.UniqueIdentifier identifier = GameRegistry.findUniqueIdentifierFor(visualBlock);
        if(identifier != null) {
            tagCompound.setString("VisualBlockModId", identifier.modId);
            tagCompound.setString("VisualBlockName", identifier.name);
            tagCompound.setByte("VisualMetadata", (byte) visualBlock.getMetaFromState(visualBlockState));
        }
    }

    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound tagCompound = new NBTTagCompound();
        GameRegistry.UniqueIdentifier identifier = GameRegistry.findUniqueIdentifierFor(visualBlock);
        if(identifier != null) {
            tagCompound.setString("VisualBlockModId", identifier.modId);
            tagCompound.setString("VisualBlockName", identifier.name);
            tagCompound.setByte("VisualMetadata", (byte) visualBlock.getMetaFromState(visualBlockState));
        }
        return new SPacketUpdateTileEntity(pos, 0, tagCompound);
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
        String blockModId = pkt.getNbtCompound().getString("VisualBlockModId");
        String blockName = pkt.getNbtCompound().getString("VisualBlockName");
        visualBlock = GameRegistry.findBlock(blockModId, blockName);
        int visualMetadata = pkt.getNbtCompound().getByte("VisualMetadata");
        visualBlockState = visualBlock.getStateFromMeta(visualMetadata);
        IBlockState blockState = worldObj.getBlockState(pos);
        worldObj.markAndNotifyBlock(pos, worldObj.getChunkFromBlockCoords(pos), blockState, blockState, 1 | 2);
    }

    public Block getVisualBlock() {
        return visualBlock;
    }

    public void setVisualBlock(IBlockState visualBlockState) {
        this.visualBlock = visualBlockState.getBlock();
        this.visualBlockState = visualBlockState;
        markDirty();
        IBlockState blockState = worldObj.getBlockState(pos);
        worldObj.markAndNotifyBlock(pos, worldObj.getChunkFromBlockCoords(pos), blockState, blockState, 1 | 2);
    }

    public IBlockState getVisualBlockState() {
        return visualBlockState;
    }
}
