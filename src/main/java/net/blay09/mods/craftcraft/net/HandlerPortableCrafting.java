package net.blay09.mods.craftcraft.net;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.blay09.mods.craftcraft.CraftCraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class HandlerPortableCrafting implements IMessageHandler<MessagePortableCrafting, IMessage> {

    @Override
    public IMessage onMessage(MessagePortableCrafting message, MessageContext ctx) {
        EntityPlayer entityPlayer = ctx.getServerHandler().playerEntity;
        for(int i = 0; i < entityPlayer.inventory.getSizeInventory(); i++) {
            ItemStack itemStack = entityPlayer.inventory.getStackInSlot(i);
            if(itemStack != null && itemStack.getItem() == CraftCraft.portableCraftingTable) {
                CraftCraft.portableCraftingTable.openPortableCrafting(entityPlayer, itemStack);
            }
        }
        return null;
    }

}
