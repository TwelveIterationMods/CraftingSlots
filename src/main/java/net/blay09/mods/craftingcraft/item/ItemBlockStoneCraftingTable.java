package net.blay09.mods.craftingcraft.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

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

}
