package net.blay09.mods.craftingcraft.item;

import net.blay09.mods.craftingcraft.CraftingCraft;
import net.blay09.mods.craftingcraft.container.PortableCraftingContainer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class PortableCraftingItem extends Item {

    public static final String name = "portable_crafting";

    public PortableCraftingItem() {
        super(new Item.Properties().group(CraftingCraft.itemGroup).maxStackSize(1));
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
        ItemStack heldItem = player.getHeldItem(hand);
        openPortableCrafting(player);
        return new ActionResult<>(ActionResultType.SUCCESS, heldItem);
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        if (context.getPlayer() != null) {
            openPortableCrafting(context.getPlayer());
        }

        return ActionResultType.SUCCESS;
    }

    private void openPortableCrafting(PlayerEntity player) {
        if (!player.world.isRemote) {
            NetworkHooks.openGui((ServerPlayerEntity) player, getCraftingContainerProvider());
        }
    }

    protected INamedContainerProvider getCraftingContainerProvider() {
        return PortableCraftingContainer.provider;
    }

}
