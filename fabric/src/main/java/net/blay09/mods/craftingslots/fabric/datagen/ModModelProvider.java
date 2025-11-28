package net.blay09.mods.craftingslots.fabric.datagen;

import net.blay09.mods.craftingslots.item.ModItems;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator) {
        itemModelGenerator.generateFlatItem(ModItems.inventoryCraftingTable.asItem(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.portableCraftingTable.asItem(), ModelTemplates.FLAT_ITEM);
    }

}
