package net.blay09.mods.craftingcraft.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;

public class ContainerInventoryCrafting extends Container {

	private final EntityPlayer entityPlayer;
	private InventoryCrafting craftMatrix;
	private IInventory craftResult = new InventoryCraftResult();

	public ContainerInventoryCrafting(EntityPlayer entityPlayer) {
		this.entityPlayer = entityPlayer;
		IInventory inventoryPlayer = new InventoryCraftingWrapper(entityPlayer.inventory, this);
		craftMatrix = new InventoryIntegratedCrafting(this, entityPlayer.inventory);

		// Crafting Matrix
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				addSlotToContainer(new Slot(inventoryPlayer, (j + 6) + i * 9 + 9, 119 + j * 18, 20 + i * 18));
			}
		}

		// Inventory
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 6; j++) {
				addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9, 8 + j * 18, 20 + i * 18));
			}
		}

		// Hotbar
		for (int i = 0; i < 9; i++) {
			int x = 8 + i * 18;
			if (i >= 6) {
				x += 3;
			}
			addSlotToContainer(new Slot(inventoryPlayer, i, x, 78));
		}

		addSlotToContainer(new SlotCrafting(entityPlayer, craftMatrix, craftResult, 0, 193, 38));

		onCraftMatrixChanged(craftMatrix);
	}


	@Override
	public boolean canInteractWith(EntityPlayer entityPlayer) {
		return true;
	}

	@Override
	public void onCraftMatrixChanged(IInventory p_75130_1_) {
		craftResult.setInventorySlotContents(0, CraftingManager.getInstance().findMatchingRecipe(craftMatrix, entityPlayer.world));
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slotIndex) {
		final int CRAFTING_GRID_START = 0;
		final int CRAFTING_GRID_END = 9;
		final int CRAFTING_RESULT_SLOT = 36;
		ItemStack itemStack = ItemStack.field_190927_a;
		Slot slot = inventorySlots.get(slotIndex);
		if (slot != null && slot.getHasStack()) {
			ItemStack slotStack = slot.getStack();
			itemStack = slotStack.copy();
			if (slotIndex < CRAFTING_GRID_END) {
				if (!this.mergeItemStack(slotStack, CRAFTING_GRID_END, inventorySlots.size() - 1, true)) {
					return ItemStack.field_190927_a;
				}
			} else if (slotIndex == CRAFTING_RESULT_SLOT) {
				slotStack.getItem().onCreated(slotStack, player.world, player);
				if (!this.mergeItemStack(slotStack, CRAFTING_GRID_END, inventorySlots.size() - 1, true)) {
					return ItemStack.field_190927_a;
				}
				slot.onSlotChange(slotStack, itemStack);
			} else if (!this.mergeItemStack(slotStack, CRAFTING_GRID_START, CRAFTING_GRID_END, false)) {
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