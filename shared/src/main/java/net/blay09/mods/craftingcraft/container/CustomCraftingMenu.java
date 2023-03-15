package net.blay09.mods.craftingcraft.container;

import net.minecraft.network.protocol.game.ClientboundContainerSetSlotPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.ResultContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

import java.util.Optional;

public abstract class CustomCraftingMenu extends AbstractContainerMenu {

    private final Inventory playerInventory;

    protected CustomCraftingMenu(MenuType<?> type, int id, Inventory playerInventory) {
        super(type, id);
        this.playerInventory = playerInventory;
    }

    @Override
    public void slotsChanged(Container container) {
        CraftingContainer craftMatrix = getCraftMatrix();
        ResultContainer craftResult = getCraftResult();
        Level level = playerInventory.player.level;
        if (!level.isClientSide) {
            ServerPlayer player = (ServerPlayer) playerInventory.player;
            ItemStack itemstack = ItemStack.EMPTY;
            Optional<CraftingRecipe> optional = level.getServer().getRecipeManager().getRecipeFor(RecipeType.CRAFTING, craftMatrix, level);
            if (optional.isPresent()) {
                CraftingRecipe recipe = optional.get();
                if (craftResult.setRecipeUsed(level, player, recipe)) {
                    itemstack = recipe.assemble(craftMatrix, level.registryAccess());
                }
            }

            craftResult.setItem(0, itemstack);
            player.connection.send(new ClientboundContainerSetSlotPacket(containerId, incrementStateId(), 0, itemstack));
        }
    }

    protected abstract CraftingContainer getCraftMatrix();

    protected abstract ResultContainer getCraftResult();


}
