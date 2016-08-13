package net.blay09.mods.craftingcraft.client.render;

import net.blay09.mods.craftingcraft.CraftingCraft;
import net.blay09.mods.craftingcraft.block.BlockCraftingTableFrame;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockModelShapes;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.block.model.*;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.property.IExtendedBlockState;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class BlockModelCraftingTableFrame implements IBakedModel {

    public static final ModelResourceLocation modelResource = new ModelResourceLocation(CraftingCraft.MOD_ID + ":" + "craftingTableFrame");
    private final IBakedModel emptyModel;

    public BlockModelCraftingTableFrame(IBakedModel emptyModel) {
        this.emptyModel = emptyModel;
    }

    @Override
    public List<BakedQuad> getQuads(@Nullable IBlockState state, @Nullable EnumFacing side, long rand) {
		List<BakedQuad> quads = new ArrayList<>();
        if(state instanceof IExtendedBlockState) {
            IExtendedBlockState extendedState = (IExtendedBlockState) state;
            IBlockState visualState = extendedState.getValue(BlockCraftingTableFrame.VISUAL_BLOCK);
            if(visualState != null && visualState != Blocks.AIR.getDefaultState()) {
                try {
                    BlockRendererDispatcher dispatcher = Minecraft.getMinecraft().getBlockRendererDispatcher();
                    BlockModelShapes shapes = dispatcher.getBlockModelShapes();
                    IBakedModel visualModel = shapes.getModelForState(visualState);
                    quads.addAll(visualModel.getQuads(visualState, side, rand));
                } catch (Exception e) {
                    // Fail-safe for people who do state-dependent black magic (like ExUtils2 threaded XUBlockState loading)
                    // Just don't render their blocks instead of crashing
                }
            }
        }
        quads.addAll(emptyModel.getQuads(state, side, rand));
        return quads;
    }

    @Override
    public TextureAtlasSprite getParticleTexture() {
        return emptyModel.getParticleTexture();
    }

    @Override
    public boolean isAmbientOcclusion() {
        return emptyModel.isAmbientOcclusion();
    }

    @Override
    public boolean isGui3d() {
        return emptyModel.isGui3d();
    }

    @Override
    public boolean isBuiltInRenderer() {
        return emptyModel.isBuiltInRenderer();
    }

    @Override
    public ItemCameraTransforms getItemCameraTransforms() {
        return emptyModel.getItemCameraTransforms();
    }

	@Override
	public ItemOverrideList getOverrides() {
		return emptyModel.getOverrides();
	}
}
