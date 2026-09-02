package net.blay09.mods.craftingslots.menu;

import net.blay09.mods.balm.world.BalmMenuProvider;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Prediction;
import net.minecraft.util.Unit;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedItemContents;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class PortableCraftingMenu extends CustomCraftingMenu {

    private static final int RESULT_SLOT = 0;
    private static final int CRAFT_SLOT_START = 1;
    private static final int CRAFT_SLOT_COUNT = 9;
    private static final int CRAFT_SLOT_END = CRAFT_SLOT_START + CRAFT_SLOT_COUNT;

    public static final MenuProvider provider = new BalmMenuProvider<Unit>() {
        @Override
        public Unit getScreenOpeningData(ServerPlayer serverPlayer) {
            return Unit.INSTANCE;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, Unit> getScreenStreamCodec() {
            return StreamCodec.unit(Unit.INSTANCE);
        }

        @Override
        public Component getDisplayName() {
            return Component.translatable("container.craftingslots.portable_crafting");
        }

        @Override
        public AbstractContainerMenu createMenu(int windowId, Inventory playerInventory, Player playerEntity) {
            return new PortableCraftingMenu(windowId, playerInventory);
        }
    };

    private final CraftingContainer craftingContainer = new TransientCraftingContainer(this, 3, 3);
    private final ResultContainer resultContainer = new ResultContainer();

    public PortableCraftingMenu(int windowId, Inventory playerInventory) {
        super(ModMenus.portableCrafting.value(), windowId, playerInventory);
        addSlot(new ResultSlot(playerInventory.player, craftingContainer, resultContainer, RESULT_SLOT, 124, 35));

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                addSlot(new Slot(craftingContainer, j + i * 3, 30 + j * 18, 17 + i * 18));
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
        slotsChanged(craftingContainer);
    }

    @Override
    public void removed(Player player) {
        super.removed(player);
        clearContainer(player, craftingContainer);
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack slotStack = slot.getItem();
            itemStack = slotStack.copy();
            if (index == 0) {
                slotStack.getItem().onCraftedBy(slotStack, player);
                if (!this.moveItemStackTo(slotStack, 10, 46, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onQuickCraft(slotStack, itemStack);
            } else if (index >= 10 && index < 46) {
                if (!this.moveItemStackTo(slotStack, 1, 10, false)) {
                    if (index < 37) {
                        if (!this.moveItemStackTo(slotStack, 37, 46, false)) {
                            return ItemStack.EMPTY;
                        }
                    } else if (!this.moveItemStackTo(slotStack, 10, 37, false)) {
                        return ItemStack.EMPTY;
                    }
                }
            } else if (!this.moveItemStackTo(slotStack, 10, 46, false)) {
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
            if (index == 0) {
                player.drop(slotStack, false, Prediction.SERVER_ONLY);
            }
        }

        return itemStack;
    }

    @Override
    public boolean canTakeItemForPickAll(ItemStack itemStack, Slot slot) {
        return slot.container != resultContainer && super.canTakeItemForPickAll(itemStack, slot);
    }

    @Override
    public Slot getResultSlot() {
        return slots.get(RESULT_SLOT);
    }

    @Override
    public List<Slot> getInputGridSlots() {
        return slots.subList(CRAFT_SLOT_START, CRAFT_SLOT_END);
    }

    @Override
    public CraftingContainer getCraftingContainer() {
        return craftingContainer;
    }

    @Override
    protected ResultContainer getResultContainer() {
        return resultContainer;
    }

    @Override
    public void fillCraftSlotsStackedContents(StackedItemContents stackedItemContents) {
        craftingContainer.fillStackedContents(stackedItemContents);
    }
}
