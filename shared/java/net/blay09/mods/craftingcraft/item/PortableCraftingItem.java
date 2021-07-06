package net.blay09.mods.craftingcraft.item;

import net.blay09.mods.craftingcraft.container.PortableCraftingMenu;
import net.blay09.mods.forbic.item.ForbicItem;
import net.blay09.mods.forbic.network.ForbicNetworking;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

public class PortableCraftingItem extends ForbicItem {

    public static final String name = "portable_crafting";

    public PortableCraftingItem(Properties properties) {
        super(properties.stacksTo(1));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack heldItem = player.getItemInHand(hand);
        openPortableCrafting(player);
        return new InteractionResultHolder<>(InteractionResult.SUCCESS, heldItem);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        if (context.getPlayer() != null) {
            openPortableCrafting(context.getPlayer());
        }

        return InteractionResult.SUCCESS;
    }

    private void openPortableCrafting(Player player) {
        if (!player.level.isClientSide) {
            ForbicNetworking.openGui(player, getCraftingContainerProvider());
        }
    }

    protected MenuProvider getCraftingContainerProvider() {
        return PortableCraftingMenu.provider;
    }

}
