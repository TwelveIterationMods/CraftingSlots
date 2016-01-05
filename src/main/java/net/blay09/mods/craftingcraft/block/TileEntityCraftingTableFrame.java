package net.blay09.mods.craftingcraft.block;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntityCraftingTableFrame extends TileEntityStoneCraftingTable {

    private Block visualBlock;
    private int visualMetadata;

    @Override
    public void readFromNBT(NBTTagCompound tagCompound) {
        super.readFromNBT(tagCompound);
        String blockModId = tagCompound.getString("VisualBlockModId");
        String blockName = tagCompound.getString("VisualBlockName");
        visualBlock = GameRegistry.findBlock(blockModId, blockName);
        visualMetadata = tagCompound.getByte("VisualMetadata");
    }

    @Override
    public void writeToNBT(NBTTagCompound tagCompound) {
        super.writeToNBT(tagCompound);
        GameRegistry.UniqueIdentifier identifier = GameRegistry.findUniqueIdentifierFor(visualBlock);
        if(identifier != null) {
            tagCompound.setString("VisualBlockModId", identifier.modId);
            tagCompound.setString("VisualBlockName", identifier.name);
            tagCompound.setByte("VisualMetadata", (byte) visualMetadata);
        }
    }

    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound tagCompound = new NBTTagCompound();
        GameRegistry.UniqueIdentifier identifier = GameRegistry.findUniqueIdentifierFor(visualBlock);
        if(identifier != null) {
            tagCompound.setString("VisualBlockModId", identifier.modId);
            tagCompound.setString("VisualBlockName", identifier.name);
            tagCompound.setByte("VisualMetadata", (byte) visualMetadata);
        }
        return new S35PacketUpdateTileEntity(pos, 0, tagCompound);
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        String blockModId = pkt.getNbtCompound().getString("VisualBlockModId");
        String blockName = pkt.getNbtCompound().getString("VisualBlockName");
        visualBlock = GameRegistry.findBlock(blockModId, blockName);
        visualMetadata = pkt.getNbtCompound().getByte("VisualMetadata");
        IBlockState blockState = worldObj.getBlockState(pos);
        worldObj.markAndNotifyBlock(pos, worldObj.getChunkFromBlockCoords(pos), blockState, blockState, 1 | 2);
    }

    public Block getVisualBlock() {
        return visualBlock;
    }

    public int getVisualMetadata() {
        return visualMetadata;
    }

    public void setVisualBlock(Block visualBlock, int metadata) {
        this.visualBlock = visualBlock;
        this.visualMetadata = metadata;
        markDirty();
        worldObj.markBlockForUpdate(pos);
    }
}
