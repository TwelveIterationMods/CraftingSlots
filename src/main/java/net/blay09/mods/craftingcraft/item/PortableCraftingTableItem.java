package net.blay09.mods.craftingcraft.item;

import net.blay09.mods.craftingcraft.CraftingCraft;
import net.blay09.mods.craftingcraft.network.GuiHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class PortableCraftingTableItem extends Item {

    public static final String name = "portable_crafting_table";
    public static final ResourceLocation registryName = new ResourceLocation(CraftingCraft.MOD_ID, name);

    public PortableCraftingTableItem() {
        super(new Item.Properties().group(CraftingCraft.itemGroup).maxStackSize(1));
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
        ItemStack heldItem = player.getHeldItem(hand);
        openPortableCrafting(player, heldItem);
        return new ActionResult<>(ActionResultType.SUCCESS, heldItem);
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        if (context.getPlayer() != null) {
            openPortableCrafting(context.getPlayer(), context.getItem());
        }

        return ActionResultType.SUCCESS;
    }

    protected void openPortableCrafting(PlayerEntity entityPlayer, ItemStack itemStack) {
        if (!entityPlayer.world.isRemote) {
            entityPlayer.openGui(CraftingCraft.instance, GuiHandler.GUI_PORTABLE_CRAFTING, entityPlayer.world, (int) entityPlayer.posX, (int) entityPlayer.posY, (int) entityPlayer.posZ);
        }
    }

}
