package net.blay09.mods.craftingcraft.container;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.CraftResultInventory;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ICraftingRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.network.play.server.SSetSlotPacket;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Optional;

public abstract class CraftingContainer extends Container {

    private final PlayerInventory playerInventory;

    protected CraftingContainer(@Nullable ContainerType<?> type, int id, PlayerInventory playerInventory) {
        super(type, id);
        this.playerInventory = playerInventory;
    }

    @Override
    public void onCraftMatrixChanged(IInventory inventory) {
        CraftingInventory craftMatrix = getCraftMatrix();
        CraftResultInventory craftResult = getCraftResult();
        World world = playerInventory.player.world;
        if (!world.isRemote) {
            ServerPlayerEntity player = (ServerPlayerEntity) playerInventory.player;
            ItemStack itemstack = ItemStack.EMPTY;
            Optional<ICraftingRecipe> optional = world.getServer().getRecipeManager().getRecipe(IRecipeType.CRAFTING, craftMatrix, world);
            if (optional.isPresent()) {
                ICraftingRecipe icraftingrecipe = optional.get();
                if (craftResult.canUseRecipe(world, player, icraftingrecipe)) {
                    itemstack = icraftingrecipe.getCraftingResult(craftMatrix);
                }
            }

            craftResult.setInventorySlotContents(0, itemstack);
            player.connection.sendPacket(new SSetSlotPacket(windowId, 0, itemstack));
        }
    }

    protected abstract CraftingInventory getCraftMatrix();

    protected abstract CraftResultInventory getCraftResult();


}
