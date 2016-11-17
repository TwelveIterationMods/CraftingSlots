package net.blay09.mods.craftingcraft.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;

public class ContainerPortableCrafting extends Container {

    private final EntityPlayer entityPlayer;
    private final InventoryCrafting craftMatrix = new InventoryCrafting(this, 3, 3);
    private final IInventory craftResult = new InventoryCraftResult();

    public ContainerPortableCrafting(EntityPlayer entityPlayer) {
        this.entityPlayer = entityPlayer;
        addSlotToContainer(new SlotCrafting(entityPlayer, craftMatrix, craftResult, 0, 124, 35));

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                addSlotToContainer(new Slot(craftMatrix, j + i * 3, 30 + j * 18, 17 + i * 18));
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                addSlotToContainer(new Slot(entityPlayer.inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
        for (int i = 0; i < 9; i++) {
            addSlotToContainer(new Slot(entityPlayer.inventory, i, 8 + i * 18, 142));
        }
        onCraftMatrixChanged(craftMatrix);
    }

    @Override
    public void onCraftMatrixChanged(IInventory inventory) {
        craftResult.setInventorySlotContents(0, CraftingManager.getInstance().findMatchingRecipe(craftMatrix, entityPlayer.world));
    }

    @Override
    public boolean canInteractWith(EntityPlayer entityPlayer) {
        return true;
    }

    @Override
    public void onContainerClosed(EntityPlayer entityPlayer) {
        super.onContainerClosed(entityPlayer);
        for (int i = 0; i < 9; i++) {
            ItemStack itemStack = craftMatrix.removeStackFromSlot(i);
            if (!itemStack.func_190926_b()) {
                if (!entityPlayer.inventory.addItemStackToInventory(itemStack) && !entityPlayer.world.isRemote) {
                    entityPlayer.dropItem(itemStack, false);
                }
            }
        }
        entityPlayer.inventoryContainer.detectAndSendChanges();
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slotIndex) {
        final int CRAFTING_GRID_START = 1;
        final int CRAFTING_GRID_END = 10;
        final int CRAFTING_RESULT_SLOT = 0;
        final int HOTBAR_START = 37;
        final int HOTBAR_END = 46;
        ItemStack itemStack = ItemStack.field_190927_a;
        Slot slot = this.inventorySlots.get(slotIndex);
        if (slot != null && slot.getHasStack()) {
            ItemStack slotStack = slot.getStack();
            itemStack = slotStack.copy();

            if (slotIndex == CRAFTING_RESULT_SLOT) {
                slotStack.getItem().onCreated(slotStack, player.world, player);
                if (!this.mergeItemStack(slotStack, CRAFTING_GRID_END, HOTBAR_END, true)) {
                    return ItemStack.field_190927_a;
                }

                slot.onSlotChange(slotStack, itemStack);
            } else if (slotIndex >= CRAFTING_GRID_END && slotIndex < HOTBAR_START) {
                if (!this.mergeItemStack(slotStack, HOTBAR_START, HOTBAR_END, false)) {
                    return ItemStack.field_190927_a;
                }
            } else if (slotIndex >= HOTBAR_START && slotIndex < HOTBAR_END) {
                if (!this.mergeItemStack(slotStack, CRAFTING_GRID_END, HOTBAR_START, false)) {
                    return ItemStack.field_190927_a;
                }
            } else if (!this.mergeItemStack(slotStack, CRAFTING_GRID_END, HOTBAR_END, false)) {
                return ItemStack.field_190927_a;
            }

            if (slotStack.func_190926_b()) {
                slot.putStack(ItemStack.field_190927_a);
            } else {
                slot.onSlotChanged();
            }

            if (slotStack.func_190916_E() == itemStack.func_190916_E()) {
                return ItemStack.field_190927_a;
            }

            ItemStack resultStack = slot.func_190901_a(player, slotStack);
            if (slotIndex == CRAFTING_RESULT_SLOT) {
                player.dropItem(resultStack, false);
            }
        }

        return itemStack;
    }

    @Override
    public boolean canMergeSlot(ItemStack itemStack, Slot slot) {
        return slot.inventory != craftResult && super.canMergeSlot(itemStack, slot);
    }

}
