package net.blay09.mods.craftingcraft.net;

import net.blay09.mods.craftingcraft.CraftingCraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import javax.annotation.Nullable;

public class HandlerPortableCrafting implements IMessageHandler<MessagePortableCrafting, IMessage> {

    @Override
    @Nullable
    public IMessage onMessage(MessagePortableCrafting message, MessageContext ctx) {
        EntityPlayer entityPlayer = ctx.getServerHandler().playerEntity;
        for(int i = 0; i < entityPlayer.inventory.getSizeInventory(); i++) {
            ItemStack itemStack = entityPlayer.inventory.getStackInSlot(i);
            if(!itemStack.func_190926_b() && itemStack.getItem() == CraftingCraft.portableCraftingTable) {
                CraftingCraft.portableCraftingTable.openPortableCrafting(entityPlayer, itemStack);
            }
        }
        return null;
    }

}
