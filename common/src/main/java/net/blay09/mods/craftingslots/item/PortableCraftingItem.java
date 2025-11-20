package net.blay09.mods.craftingslots.item;

import net.blay09.mods.balm.Balm;
import net.blay09.mods.craftingslots.menu.PortableCraftingMenu;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

public class PortableCraftingItem extends Item {

    public PortableCraftingItem(Properties properties) {
        super(properties.stacksTo(1));
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        openPortableCrafting(player);
        return InteractionResult.SUCCESS;
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        if (context.getPlayer() != null) {
            openPortableCrafting(context.getPlayer());
        }

        return InteractionResult.SUCCESS;
    }

    private void openPortableCrafting(Player player) {
        if (!player.level().isClientSide()) {
            Balm.networking().openMenu(player, getCraftingContainerProvider());
        }
    }

    protected MenuProvider getCraftingContainerProvider() {
        return PortableCraftingMenu.provider;
    }

}
