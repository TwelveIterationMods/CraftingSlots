//package net.blay09.mods.craftingcraft.addon;
//
//import net.blay09.mods.craftingcraft.container.InventoryCraftingMenu;
//import net.blay09.mods.craftingtweaks.api.CraftingTweaksAPI;
//import net.blay09.mods.craftingtweaks.api.DefaultProviderV2;
//import net.blay09.mods.craftingtweaks.api.TweakProvider;
//import net.minecraft.client.Minecraft;
//import net.minecraft.client.gui.screen.inventory.ContainerScreen;
//import net.minecraft.entity.player.PlayerEntity;
//import net.minecraft.inventory.IInventory;
//import net.minecraft.inventory.container.Slot;
//import net.minecraft.item.ItemStack;
//import net.minecraftforge.api.distmarker.Dist;
//import net.minecraftforge.api.distmarker.OnlyIn;
//import net.minecraftforge.client.event.GuiScreenEvent;
//
//public class InventoryCraftingTweakProvider implements TweakProvider<InventoryCraftingMenu> {
//
//    private DefaultProviderV2 defaultProvider = CraftingTweaksAPI.createDefaultProviderV2();
//
//    @Override
//    public boolean load() {
//        return true;
//    }
//
//    @Override
//    public boolean requiresServerSide() {
//        return false;
//    }
//
//    @Override
//    public int getCraftingGridStart(PlayerEntity entityPlayer, InventoryCraftingMenu container, int id) {
//        return 1;
//    }
//
//    @Override
//    public int getCraftingGridSize(PlayerEntity entityPlayer, InventoryCraftingMenu container, int id) {
//        return 9;
//    }
//
//    @Override
//    public void clearGrid(PlayerEntity entityPlayer, InventoryCraftingMenu container, int id, boolean forced) {
//        int start = getCraftingGridStart(entityPlayer, container, id);
//        int size = getCraftingGridSize(entityPlayer, container, id);
//        for (int i = start; i < start + size; i++) {
//            container.transferStackInSlot(entityPlayer, i);
//            if (forced && container.inventorySlots.get(i).getHasStack()) {
//                entityPlayer.dropItem(container.inventorySlots.get(i).getStack(), false);
//                container.inventorySlots.get(i).putStack(ItemStack.EMPTY);
//            }
//        }
//    }
//
//    @Override
//    public void rotateGrid(PlayerEntity entityPlayer, InventoryCraftingMenu container, int id, boolean counterClockwise) {
//        defaultProvider.rotateGrid(this, id, entityPlayer, container, counterClockwise);
//    }
//
//    @Override
//    public void balanceGrid(PlayerEntity entityPlayer, InventoryCraftingMenu container, int id) {
//        defaultProvider.balanceGrid(this, id, entityPlayer, container);
//    }
//
//    @Override
//    public void spreadGrid(PlayerEntity entityPlayer, InventoryCraftingMenu container, int id) {
//        defaultProvider.spreadGrid(this, id, entityPlayer, container);
//    }
//
//    @Override
//    public boolean canTransferFrom(PlayerEntity entityPlayer, InventoryCraftingMenu container, int i, Slot slot) {
//        return defaultProvider.canTransferFrom(entityPlayer, container, slot);
//    }
//
//    @Override
//    public boolean transferIntoGrid(PlayerEntity entityPlayer, InventoryCraftingMenu container, int id, Slot slot) {
//        return defaultProvider.transferIntoGrid(this, id, entityPlayer, container, slot);
//    }
//
//    @Override
//    public ItemStack putIntoGrid(PlayerEntity entityPlayer, InventoryCraftingMenu container, int id, ItemStack itemStack, int index) {
//        return defaultProvider.putIntoGrid(this, id, entityPlayer, container, itemStack, index);
//    }
//
//    @Override
//    public IInventory getCraftMatrix(PlayerEntity entityPlayer, InventoryCraftingMenu container, int id) {
//        return container.inventorySlots.get(getCraftingGridStart(entityPlayer, container, id)).inventory;
//    }
//
//    @Override
//    @OnlyIn(Dist.CLIENT)
//    public void initGui(ContainerScreen<InventoryCraftingMenu> containerScreen, GuiScreenEvent.InitGuiEvent initGuiEvent) {
//        int craftingGridStart = getCraftingGridStart(Minecraft.getInstance().player, containerScreen.getContainer(), 0);
//        Slot firstSlot = containerScreen.getContainer().getSlot(craftingGridStart);
//        initGuiEvent.addWidget(CraftingTweaksAPI.createRotateButtonRelative(0, containerScreen, firstSlot.xPos, firstSlot.yPos - 18));
//        initGuiEvent.addWidget(CraftingTweaksAPI.createBalanceButtonRelative(0, containerScreen, firstSlot.xPos + 18, firstSlot.yPos - 18));
//        initGuiEvent.addWidget(CraftingTweaksAPI.createClearButtonRelative(0, containerScreen, firstSlot.xPos + 36, firstSlot.yPos - 18));
//    }
//
//    @Override
//    public String getModId() {
//        return "craftingcraft";
//    }
//
//}
