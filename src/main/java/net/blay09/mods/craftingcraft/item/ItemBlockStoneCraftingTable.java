package net.blay09.mods.craftingcraft.item;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

import java.util.List;

public class ItemBlockStoneCraftingTable extends ItemBlock {

    public ItemBlockStoneCraftingTable(Block block) {
        super(block);
        setHasSubtypes(true);
    }

    @Override
    public int getMetadata(int metadata) {
        return metadata;
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        switch(itemStack.getItemDamage()) {
            case 0: return "tile.craftingcraft:stoneCraftingTable";
            case 1: return "tile.craftingcraft:netherCraftingTable";
        }
        return "tile.craftingcraft:stoneCraftingTable";
    }

    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean flag) {
        super.addInformation(itemStack, entityPlayer, list, flag);
        list.add("\u00a77" + StatCollector.translateToLocal("tile.craftingcraft:stoneCraftingTable.tooltip"));
    }
}
