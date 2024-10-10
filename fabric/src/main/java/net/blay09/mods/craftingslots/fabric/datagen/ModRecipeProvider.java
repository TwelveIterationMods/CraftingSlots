package net.blay09.mods.craftingslots.fabric.datagen;

import net.blay09.mods.craftingslots.CraftingSlots;
import net.blay09.mods.craftingslots.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> provider) {
        super(output, provider);
    }

    @Override
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider registryLookup, RecipeOutput exporter) {
        return new RecipeProvider(registryLookup, exporter) {

            @Override
            public void buildRecipes() {
                shaped(RecipeCategory.TOOLS, ModItems.portableCraftingTable)
                        .pattern(" C")
                        .pattern("S ")
                        .define('C', Blocks.CRAFTING_TABLE)
                        .define('S', Items.STICK)
                        .unlockedBy("has_crafting_table", has(Blocks.CRAFTING_TABLE))
                        .save(exporter);

                shapeless(RecipeCategory.TOOLS, ModItems.inventoryCraftingTable)
                        .requires(ModItems.portableCraftingTable)
                        .unlockedBy("has_portable_crafting", has(ModItems.portableCraftingTable))
                        .save(exporter);

                shapeless(RecipeCategory.TOOLS, ModItems.portableCraftingTable)
                        .requires(ModItems.inventoryCraftingTable)
                        .unlockedBy("has_inventory_crafting", has(ModItems.inventoryCraftingTable))
                        .save(exporter, "craftingslots:inventory_to_portable_crafting");

            }
        };
    }

    @Override
    public String getName() {
        return CraftingSlots.MOD_ID;
    }
}