package net.blay09.mods.craftingcraft.block;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.Constants;

public class TileEntityStoneCraftingTable extends TileEntity implements IInventory {

    private final ItemStack[] craftMatrix = new ItemStack[9];

    @Override
    public int getSizeInventory() {
        return 9;
    }

    @Override
    public ItemStack getStackInSlot(int i) {
        return i < 9 ? craftMatrix[i] : null;
    }

    @Override
    public ItemStack decrStackSize(int i, int count) {
        if (craftMatrix[i] != null) {
            ItemStack itemstack;
            if (craftMatrix[i].stackSize <= count) {
                itemstack = craftMatrix[i];
                craftMatrix[i] = null;
                markDirty();
                return itemstack;
            } else {
                itemstack = craftMatrix[i].splitStack(count);
                if (craftMatrix[i].stackSize == 0) {
                    craftMatrix[i] = null;
                }
                markDirty();
                return itemstack;
            }
        }
        return null;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int i) {
        return craftMatrix[i];
    }

    @Override
    public void setInventorySlotContents(int i, ItemStack itemStack) {
        craftMatrix[i] = itemStack;
        markDirty();
    }

    @Override
    public String getInventoryName() {
        return "craftingcraft:container.stoneCraftingTable";
    }

    @Override
    public boolean isCustomInventoryName() {
        return false;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer entityPlayer) {
        return true;
    }

    @Override
    public void openChest() {

    }

    @Override
    public void closeChest() {

    }

    @Override
    public boolean isItemValidForSlot(int i, ItemStack itemStack) {
        return false;
    }

    @Override
    public void readFromNBT(NBTTagCompound tagCompound) {
        super.readFromNBT(tagCompound);
        NBTTagList tagList = tagCompound.getTagList("Items", Constants.NBT.TAG_COMPOUND);
        for (int i = 0; i < tagList.tagCount(); i++) {
            NBTTagCompound itemCompound = tagList.getCompoundTagAt(i);
            setInventorySlotContents(itemCompound.getByte("Slot"), ItemStack.loadItemStackFromNBT(itemCompound));
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound tagCompound) {
        super.writeToNBT(tagCompound);
        NBTTagList tagList = new NBTTagList();
        for (int i = 0; i < getSizeInventory(); i++) {
            ItemStack itemStack = getStackInSlot(i);
            if (itemStack != null) {
                NBTTagCompound itemCompound = new NBTTagCompound();
                itemCompound.setByte("Slot", (byte) i);
                itemStack.writeToNBT(itemCompound);
                tagList.appendTag(itemCompound);
            }
        }
        tagCompound.setTag("Items", tagList);
    }
}
