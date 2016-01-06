package net.blay09.mods.craftingcraft.client.render;

import com.google.common.collect.ImmutableMap;
import net.blay09.mods.craftingcraft.CraftingCraft;
import net.blay09.mods.craftingcraft.block.BlockCraftingTableFrame;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockModelShapes;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.client.model.IFlexibleBakedModel;
import net.minecraftforge.client.model.ISmartBlockModel;
import net.minecraftforge.client.model.MultiModel;
import net.minecraftforge.common.property.IExtendedBlockState;

import java.util.List;

public class BlockModelCraftingTableFrame implements ISmartBlockModel {

    public static final ModelResourceLocation modelResource = new ModelResourceLocation(CraftingCraft.MOD_ID + ":" + "craftingTableFrame");
    private final IFlexibleBakedModel emptyModel;

    public BlockModelCraftingTableFrame(IFlexibleBakedModel emptyModel) {
        this.emptyModel = emptyModel;
    }

    @Override
    public IBakedModel handleBlockState(IBlockState state) {
        IBakedModel model = emptyModel;
        IBlockState airState = Blocks.air.getDefaultState();
        if(state instanceof IExtendedBlockState) {
            IExtendedBlockState extendedState = (IExtendedBlockState) state;
            IBlockState visualState = extendedState.getValue(BlockCraftingTableFrame.VISUAL_BLOCK);
            if(visualState != null && visualState != airState) {
                BlockRendererDispatcher dispatcher = Minecraft.getMinecraft().getBlockRendererDispatcher();
                BlockModelShapes shapes = dispatcher.getBlockModelShapes();
                IBakedModel visualModel = shapes.getModelForState(visualState);
                if(visualModel instanceof  ISmartBlockModel) {
                    visualModel = ((ISmartBlockModel) visualModel).handleBlockState(visualState);
                }
                model = new MultiModel.Baked(new IFlexibleBakedModel.Wrapper(visualModel, DefaultVertexFormats.BLOCK), ImmutableMap.of("overlay", emptyModel));
            }
        }
        return model;
    }

    @Override
    public TextureAtlasSprite getParticleTexture() {
        return emptyModel.getParticleTexture();
    }

    @Override
    public List<BakedQuad> getFaceQuads(EnumFacing p_177551_1_) {
        return null;
    }

    @Override
    public List<BakedQuad> getGeneralQuads() {
        return null;
    }

    @Override
    public boolean isAmbientOcclusion() {
        return false;
    }

    @Override
    public boolean isGui3d() {
        return false;
    }

    @Override
    public boolean isBuiltInRenderer() {
        return false;
    }

    @Override
    public ItemCameraTransforms getItemCameraTransforms() {
        return null;
    }
}
