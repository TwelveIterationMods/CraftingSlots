package net.blay09.mods.craftingslots.container;

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
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

public abstract class CustomCraftingMenu extends AbstractContainerMenu {

    private final Inventory playerInventory;

    protected CustomCraftingMenu(MenuType<?> type, int id, Inventory playerInventory) {
        super(type, id);
        this.playerInventory = playerInventory;
    }

    @Override
    public void slotsChanged(Container container) {
        CraftingContainer craftMatrix = getCraftMatrix();
        final var recipeInput = craftMatrix.asCraftInput();
        ResultContainer craftResult = getCraftResult();
        Level level = playerInventory.player.level();
        if (!level.isClientSide) {
            ServerPlayer player = (ServerPlayer) playerInventory.player;
            ItemStack itemstack = ItemStack.EMPTY;
            RecipeHolder<CraftingRecipe> recipe = level.getServer().getRecipeManager().getRecipeFor(RecipeType.CRAFTING, recipeInput, level).orElse(null);
            if (recipe != null) {
                if (craftResult.setRecipeUsed(player, recipe)) {
                    itemstack = recipe.value().assemble(recipeInput, level.registryAccess());
                }
            }

            craftResult.setItem(0, itemstack);
            player.connection.send(new ClientboundContainerSetSlotPacket(containerId, incrementStateId(), 0, itemstack));
        }
    }

    protected abstract CraftingContainer getCraftMatrix();

    protected abstract ResultContainer getCraftResult();


}
