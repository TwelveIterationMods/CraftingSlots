package net.blay09.mods.craftingcraft.item;

import net.blay09.mods.craftingcraft.CraftingCraft;
import net.blay09.mods.craftingcraft.net.GuiHandler;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemPortableCraftingTable extends Item {

    public static final String name = "portable_crafting_table";

    public ItemPortableCraftingTable() {
        setRegistryName(CraftingCraft.MOD_ID, name);
        setUnlocalizedName(getRegistryName().toString());
        setHasSubtypes(true);
        setMaxStackSize(1);
        setCreativeTab(CraftingCraft.creativeTab);
    }

    @Override
    public void getSubItems(Item item, CreativeTabs tab, NonNullList<ItemStack> list) {
        list.add(new ItemStack(item, 1, 0));
        list.add(new ItemStack(item, 1, 1));
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        ItemStack heldItem = player.getHeldItem(hand);
        openPortableCrafting(player, heldItem);
        return new ActionResult<>(EnumActionResult.SUCCESS, heldItem);
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        openPortableCrafting(player, player.getHeldItem(hand));
        return EnumActionResult.SUCCESS;
    }

    public void openPortableCrafting(EntityPlayer entityPlayer, ItemStack itemStack) {
        if(!entityPlayer.world.isRemote) {
            if (itemStack.getItemDamage() == 1) {
                entityPlayer.openGui(CraftingCraft.instance, GuiHandler.GUI_INVENTORY_CRAFTING, entityPlayer.world, (int) entityPlayer.posX, (int) entityPlayer.posY, (int) entityPlayer.posZ);
            } else {
                entityPlayer.openGui(CraftingCraft.instance, GuiHandler.GUI_PORTABLE_CRAFTING, entityPlayer.world, (int) entityPlayer.posX, (int) entityPlayer.posY, (int) entityPlayer.posZ);
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public void registerModels(ItemModelMesher mesher) {
        ModelBakery.registerItemVariants(this, new ResourceLocation(CraftingCraft.MOD_ID, "portable_crafting_table"), new ResourceLocation(CraftingCraft.MOD_ID, "inventory_crafting_table"));

        mesher.register(this, 0, new ModelResourceLocation(CraftingCraft.MOD_ID + ":portable_crafting_table", "inventory"));
        mesher.register(this, 1, new ModelResourceLocation(CraftingCraft.MOD_ID + ":inventory_crafting_table", "inventory"));
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        switch(itemStack.getItemDamage()) {
            case 0: return "item.craftingcraft:portable_crafting_table";
            case 1: return "item.craftingcraft:inventory_crafting_table";
        }
        return super.getUnlocalizedName(itemStack);
    }

}
