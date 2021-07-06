package net.blay09.mods.craftingcraft.container;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;

public class PortableCraftingMenu extends CustomCraftingMenu {

    public static final MenuProvider provider = new MenuProvider() {
        @Override
        public Component getDisplayName() {
            return new TranslatableComponent("container.craftingcraft.portable_crafting");
        }

        @Override
        public AbstractContainerMenu createMenu(int windowId, Inventory playerInventory, Player playerEntity) {
            return new PortableCraftingMenu(windowId, playerInventory);
        }
    };

    private final CraftingContainer craftMatrix = new CraftingContainer(this, 3, 3);
    private final ResultContainer craftResult = new ResultContainer();

    public PortableCraftingMenu(int windowId, Inventory playerInventory) {
        super(ModMenus.portableCrafting.get(), windowId, playerInventory);
        addSlot(new ResultSlot(playerInventory.player, craftMatrix, craftResult, 0, 124, 35));

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                addSlot(new Slot(craftMatrix, j + i * 3, 30 + j * 18, 17 + i * 18));
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
        for (int i = 0; i < 9; i++) {
            addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
        slotsChanged(craftMatrix);
    }

    @Override
    public void removed(Player player) {
        super.removed(player);
        clearContainer(player, craftMatrix);
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }

    @Override
    public ItemStack quickMoveStack(Player player, int slotIndex) {
        final int CRAFTING_GRID_START = 1;
        final int CRAFTING_GRID_END = 10;
        final int CRAFTING_RESULT_SLOT = 0;
        final int HOTBAR_START = 37;
        final int HOTBAR_END = 46;
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(slotIndex);
        if (slot != null && slot.hasItem()) {
            ItemStack slotStack = slot.getItem();
            itemStack = slotStack.copy();

            if (slotIndex == CRAFTING_RESULT_SLOT) {
                slotStack.getItem().onCraftedBy(slotStack, player.level, player);
                if (!this.moveItemStackTo(slotStack, CRAFTING_GRID_END, HOTBAR_END, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onQuickCraft(slotStack, itemStack);
            } else if (slotIndex >= CRAFTING_GRID_END && slotIndex < HOTBAR_START) {
                if (!this.moveItemStackTo(slotStack, HOTBAR_START, HOTBAR_END, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (slotIndex >= HOTBAR_START && slotIndex < HOTBAR_END) {
                if (!this.moveItemStackTo(slotStack, CRAFTING_GRID_END, HOTBAR_START, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(slotStack, CRAFTING_GRID_END, HOTBAR_END, false)) {
                return ItemStack.EMPTY;
            }

            if (slotStack.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (slotStack.getCount() == itemStack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(player, slotStack);
            if (slotIndex == CRAFTING_RESULT_SLOT) {
                player.drop(slotStack, false);
            }
        }

        return itemStack;
    }

    @Override
    public boolean canTakeItemForPickAll(ItemStack itemStack, Slot slot) {
        return slot.container != craftResult && super.canTakeItemForPickAll(itemStack, slot);
    }

    @Override
    public CraftingContainer getCraftMatrix() {
        return craftMatrix;
    }

    @Override
    protected ResultContainer getCraftResult() {
        return craftResult;
    }

}
