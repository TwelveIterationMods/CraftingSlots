package net.blay09.mods.craftingcraft.item;

import net.blay09.mods.craftingcraft.CraftingCraft;
import net.blay09.mods.craftingcraft.net.GuiHandler;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import java.util.List;

public class ItemPortableCraftingTable extends Item {

    private IIcon iconInventoryCraftingTable;

    public ItemPortableCraftingTable() {
        setUnlocalizedName("craftingcraft:portableCraftingTable");
        setTextureName("craftingcraft:portableCraftingTable");
        setHasSubtypes(true);
        setMaxStackSize(1);
        setCreativeTab(CraftingCraft.creativeTab);
    }

    @Override
    public void getSubItems(Item item, CreativeTabs creativeTab, List list) {
        list.add(new ItemStack(item, 1, 0));
        list.add(new ItemStack(item, 1, 1));
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer) {
        openPortableCrafting(entityPlayer, itemStack);
        return itemStack;
    }

    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer entityPlayer, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        openPortableCrafting(entityPlayer, itemStack);
        return true;
    }

    public void openPortableCrafting(EntityPlayer entityPlayer, ItemStack itemStack) {
        if(!entityPlayer.worldObj.isRemote) {
            if (itemStack.getItemDamage() == 1) {
                entityPlayer.openGui(CraftingCraft.instance, GuiHandler.GUI_INVENTORY_CRAFTING, entityPlayer.worldObj, (int) entityPlayer.posX, (int) entityPlayer.posY, (int) entityPlayer.posZ);
            } else {
                entityPlayer.openGui(CraftingCraft.instance, GuiHandler.GUI_PORTABLE_CRAFTING, entityPlayer.worldObj, (int) entityPlayer.posX, (int) entityPlayer.posY, (int) entityPlayer.posZ);
            }
        }
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        switch(itemStack.getItemDamage()) {
            case 0: return "item.craftingcraft:portableCraftingTable";
            case 1: return "item.craftingcraft:inventoryCraftingTable";
        }
        return super.getUnlocalizedName(itemStack);
    }

    @Override
    public void registerIcons(IIconRegister iconRegister) {
        super.registerIcons(iconRegister);
        iconInventoryCraftingTable = iconRegister.registerIcon("craftingcraft:inventoryCraftingTable");
    }

    @Override
    public IIcon getIconFromDamage(int i) {
        switch(i) {
            case 0: return itemIcon;
            case 1: return iconInventoryCraftingTable;
        }
        return itemIcon;
    }
}
