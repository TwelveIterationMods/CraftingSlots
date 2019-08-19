package net.blay09.mods.craftingcraft.item;

import net.blay09.mods.craftingcraft.CraftingCraft;
import net.blay09.mods.craftingcraft.container.InventoryCraftingContainer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class PortableCraftingTableItem extends Item {

    public static final String name = "portable_crafting_table";

    public PortableCraftingTableItem() {
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
        return new INamedContainerProvider() {
            @Override
            public ITextComponent getDisplayName() {
                return new TranslationTextComponent("container.craftingcraft:inventory_crafting");
            }

            @Override
            public Container createMenu(int windowId, PlayerInventory playerInventory, PlayerEntity playerEntity) {
                return new InventoryCraftingContainer(windowId, playerInventory);
            }
        };
    }

}
