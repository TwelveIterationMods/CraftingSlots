package net.blay09.mods.craftingcraft.client;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.blay09.mods.craftingcraft.block.TileEntityCraftingTableFrame;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockRendererCraftingTableFrame implements ISimpleBlockRenderingHandler {

    public static final int RENDER_ID = RenderingRegistry.getNextAvailableRenderId();

    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
        Tessellator tessellator = Tessellator.instance;
        tessellator.setColorOpaque_F(1f, 1f, 1f);
        tessellator.startDrawingQuads();
        tessellator.setNormal(0f, -1f, 0f);
        renderer.renderFaceXNeg(block, 0, 0, 0, block.getIcon(ForgeDirection.EAST.ordinal(), metadata));
        renderer.renderFaceXPos(block, 0, 0, 0, block.getIcon(ForgeDirection.WEST.ordinal(), metadata));
        renderer.renderFaceZNeg(block, 0, 0, 0, block.getIcon(ForgeDirection.NORTH.ordinal(), metadata));
        renderer.renderFaceZPos(block, 0, 0, 0, block.getIcon(ForgeDirection.SOUTH.ordinal(), metadata));
        renderer.renderFaceYNeg(block, 0, 0, 0, block.getIcon(ForgeDirection.DOWN.ordinal(), metadata));
        renderer.renderFaceYPos(block, 0, 0, 0, block.getIcon(ForgeDirection.UP.ordinal(), metadata));
        tessellator.draw();
    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        TileEntityCraftingTableFrame tileEntity = (TileEntityCraftingTableFrame) world.getTileEntity(x, y, z);
        Block visualBlock = tileEntity.getVisualBlock();
        if (visualBlock != null) {
            renderAllFaces(renderer, world, visualBlock, tileEntity.getVisualMetadata(), x, y, z);
        }
        renderAllFaces(renderer, world, block, world.getBlockMetadata(x, y, z), x, y, z);
        return true;
    }

    public void renderAllFaces(RenderBlocks renderer, IBlockAccess world, Block block, int metadata, int x, int y, int z) {
        int l = block.colorMultiplier(world, x, y, z);
        if (l != 16777215) {
            float r = (float) (l >> 16 & 255) / 255.0F;
            float g  = (float) (l >> 8 & 255) / 255.0F;
            float b = (float) (l & 255) / 255.0F;
            Tessellator.instance.setColorOpaque_F(r, g, b);
        } else {
            Tessellator.instance.setColorOpaque_F(1f, 1f, 1f);
        }
        renderer.renderFaceXNeg(block, x, y, z, block.getIcon(ForgeDirection.EAST.ordinal(), metadata));
        renderer.renderFaceXPos(block, x, y, z, block.getIcon(ForgeDirection.WEST.ordinal(), metadata));
        renderer.renderFaceZNeg(block, x, y, z, block.getIcon(ForgeDirection.NORTH.ordinal(), metadata));
        renderer.renderFaceZPos(block, x, y, z, block.getIcon(ForgeDirection.SOUTH.ordinal(), metadata));
        renderer.renderFaceYNeg(block, x, y, z, block.getIcon(ForgeDirection.DOWN.ordinal(), metadata));
        renderer.renderFaceYPos(block, x, y, z, block.getIcon(ForgeDirection.UP.ordinal(), metadata));
    }

    @Override
    public boolean shouldRender3DInInventory(int modelId) {
        return true;
    }

    @Override
    public int getRenderId() {
        return RENDER_ID;
    }

}
