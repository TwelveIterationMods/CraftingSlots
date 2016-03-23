package net.blay09.mods.craftingcraft.item;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.translation.I18n;

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
    public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List<String> list, boolean flag) {
        super.addInformation(itemStack, entityPlayer, list, flag);
        list.add("\u00a77" + I18n.translateToLocal("tile.craftingcraft:craftingTableFrame.tooltip"));
    }
}
