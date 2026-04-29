package net.blay09.mods.craftingslots.fabric.datagen;

import net.blay09.mods.craftingslots.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagsProvider.ItemTagsProvider {
    public ModItemTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider lookup) {
        builder(TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath("curios", "charm")))
                .add(ModItems.inventoryCraftingTable.asResourceKey(), ModItems.portableCraftingTable.asResourceKey());
        builder(TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath("trinkets", "charm/charm")))
                .add(ModItems.inventoryCraftingTable.asResourceKey(), ModItems.portableCraftingTable.asResourceKey());
    }
}
