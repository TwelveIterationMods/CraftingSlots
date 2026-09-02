package net.blay09.mods.craftingslots.fabric.datagen;

import net.blay09.mods.craftingslots.CraftingSlots;
import net.blay09.mods.craftingslots.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.advancements.Advancement;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.block.Blocks;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
        super(output, provider);
    }

    @Override
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider registries, BootstrapContext<Recipe<?>> recipes, BootstrapContext<Advancement> advancements) {
        return new RecipeProvider(recipes, advancements) {

            @Override
            public void buildRecipes() {
                shaped(RecipeCategory.TOOLS, ModItems.portableCraftingTable)
                        .pattern(" C")
                        .pattern("S ")
                        .define('C', Blocks.CRAFTING_TABLE)
                        .define('S', Items.STICK)
                        .unlockedBy("has_crafting_table", has(Blocks.CRAFTING_TABLE))
                        .save(output);

                shapeless(RecipeCategory.TOOLS, ModItems.inventoryCraftingTable)
                        .requires(ModItems.portableCraftingTable)
                        .unlockedBy("has_portable_crafting", has(ModItems.portableCraftingTable))
                        .save(output);

                shapeless(RecipeCategory.TOOLS, ModItems.portableCraftingTable)
                        .requires(ModItems.inventoryCraftingTable)
                        .unlockedBy("has_inventory_crafting", has(ModItems.inventoryCraftingTable))
                        .save(output, "craftingslots:inventory_to_portable_crafting");

            }
        };
    }

    @Override
    public String getName() {
        return CraftingSlots.MOD_ID;
    }
}