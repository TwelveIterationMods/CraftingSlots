package net.blay09.mods.craftingslots.menu;

import net.minecraft.network.protocol.game.ClientboundContainerSetSlotPacket;
import net.minecraft.recipebook.ServerPlaceRecipe;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedItemContents;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeType;
import org.jspecify.annotations.Nullable;

import java.util.List;

public abstract class CustomCraftingMenu extends AbstractCraftingMenu {

    private static final int WIDTH = 3;
    private static final int HEIGHT = 3;
    private final Inventory playerInventory;
    private boolean placingRecipe;

    protected CustomCraftingMenu(MenuType<?> type, int id, Inventory playerInventory) {
        super(type, id, WIDTH, HEIGHT);
        this.playerInventory = playerInventory;
    }

    @Override
    public RecipeBookMenu.PostPlaceAction handlePlacement(boolean useMaxItems, boolean b, RecipeHolder<?> recipeHolder, ServerLevel level, Inventory inventory) {
        RecipeHolder<CraftingRecipe> craftingRecipeHolder = (RecipeHolder<CraftingRecipe>) recipeHolder;
        beginPlacingRecipe();

        RecipeBookMenu.PostPlaceAction result;
        try {
            final var list = getInputGridSlots();
            result = placeRecipe(useMaxItems, b, inventory, list, craftingRecipeHolder);
        } finally {
            finishPlacingRecipe(level, craftingRecipeHolder);
        }

        return result;
    }

    protected PostPlaceAction placeRecipe(boolean useMaxItems, boolean flag, Inventory inventory, List<Slot> inputGridSlots, RecipeHolder<CraftingRecipe> craftingRecipeHolder) {
        return ServerPlaceRecipe.placeRecipe(new ServerPlaceRecipe.CraftingMenuAccess<>() {
            public void fillCraftSlotsStackedContents(StackedItemContents stackedItemContents) {
                CustomCraftingMenu.this.fillCraftSlotsStackedContents(stackedItemContents);
            }

            public void clearCraftingContent() {
                CustomCraftingMenu.this.getResultContainer().clearContent();
                CustomCraftingMenu.this.getCraftingContainer().clearContent();
            }

            public boolean recipeMatches(RecipeHolder<CraftingRecipe> recipeHolder) {
                return recipeHolder.value().matches(CustomCraftingMenu.this.getCraftingContainer().asCraftInput(), CustomCraftingMenu.this.owner().level());
            }
        }, WIDTH, HEIGHT, inputGridSlots, inputGridSlots, inventory, craftingRecipeHolder, useMaxItems, flag);
    }

    public abstract Slot getResultSlot();

    public abstract List<Slot> getInputGridSlots();

    protected abstract CraftingContainer getCraftingContainer();

    protected abstract ResultContainer getResultContainer();

    protected void slotChangedCraftingGrid(AbstractContainerMenu menu, ServerLevel level, Player player, CraftingContainer craftingContainer, ResultContainer resultContainer, @Nullable RecipeHolder<CraftingRecipe> recipeHolder) {
        final var craftInput = craftingContainer.asCraftInput();
        final var serverPlayer = (ServerPlayer) player;
        var itemStack = ItemStack.EMPTY;
        final var foundRecipe = level.getServer()
                .getRecipeManager()
                .getRecipeFor(RecipeType.CRAFTING, craftInput, level, recipeHolder);
        if (foundRecipe.isPresent()) {
            final var foundRecipeHolder = foundRecipe.get();
            final var craftingRecipe = foundRecipeHolder.value();
            if (resultContainer.setRecipeUsed(serverPlayer, foundRecipeHolder)) {
                final var assembledStack = craftingRecipe.assemble(craftInput);
                if (assembledStack.isItemEnabled(level.enabledFeatures())) {
                    itemStack = assembledStack;
                }
            }
        }

        resultContainer.setItem(getResultSlot().getContainerSlot(), itemStack);
        menu.setRemoteSlot(getResultSlot().index, itemStack);
        serverPlayer.connection.send(new ClientboundContainerSetSlotPacket(menu.containerId, menu.incrementStateId(), getResultSlot().index, itemStack));
    }

    @Override
    public void slotsChanged(Container container) {
        if (!placingRecipe) {
            if (playerInventory.player.level() instanceof ServerLevel serverLevel) {
                slotChangedCraftingGrid(this, serverLevel, playerInventory.player, getCraftingContainer(), getResultContainer(), null);
            }
        }
    }

    public void beginPlacingRecipe() {
        this.placingRecipe = true;
    }

    public void finishPlacingRecipe(ServerLevel level, RecipeHolder<CraftingRecipe> recipeHolder) {
        this.placingRecipe = false;
        slotChangedCraftingGrid(this, level, playerInventory.player, getCraftingContainer(), getResultContainer(), recipeHolder);
    }

    @Override
    public boolean canTakeItemForPickAll(ItemStack itemStack, Slot slot) {
        return slot.container != getResultContainer() && super.canTakeItemForPickAll(itemStack, slot);
    }

    @Override
    public RecipeBookType getRecipeBookType() {
        return RecipeBookType.CRAFTING;
    }

    protected Player owner() {
        return playerInventory.player;
    }

}
