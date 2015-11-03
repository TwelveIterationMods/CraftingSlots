package net.blay09.mods.craftingcraft.item;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

import java.util.List;

public class ItemBlockCraftingTableFrame extends ItemBlock {

    public ItemBlockCraftingTableFrame(Block block) {
        super(block);
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        return "tile.craftingcraft:craftingTableFrame";
    }

    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean flag) {
        super.addInformation(itemStack, entityPlayer, list, flag);
        list.add("\u00a77" + StatCollector.translateToLocal("tile.craftingcraft:craftingTableFrame.tooltip"));
    }
}
