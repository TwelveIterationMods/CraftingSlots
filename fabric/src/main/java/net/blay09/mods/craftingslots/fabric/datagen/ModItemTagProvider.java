package net.blay09.mods.craftingslots.fabric.datagen;

import net.blay09.mods.craftingslots.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.IntrinsicHolderTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends IntrinsicHolderTagsProvider<Item> {
    public ModItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, Registries.ITEM, registriesFuture, (item) -> item.builtInRegistryHolder().key());
    }

    @Override
    protected void addTags(HolderLookup.Provider lookup) {
        tag(TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("curios", "charm")))
                .add(ModItems.inventoryCraftingTable, ModItems.portableCraftingTable);
        tag(TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("trinkets", "charm/charm")))
                .add(ModItems.inventoryCraftingTable, ModItems.portableCraftingTable);
    }
}
