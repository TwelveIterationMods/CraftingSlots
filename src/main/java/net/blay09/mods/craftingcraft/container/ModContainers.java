package net.blay09.mods.craftingcraft.container;

import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.fml.network.IContainerFactory;
import net.minecraftforge.registries.IForgeRegistry;

public class ModContainers {
    public static ContainerType<PortableCraftingContainer> portableCrafting;
    public static ContainerType<InventoryCraftingContainer> inventoryCrafting;

    public static void register(IForgeRegistry<ContainerType<?>> registry) {
        registry.register(portableCrafting = register("portable_crafting", (windowId, inv, data) -> new PortableCraftingContainer(windowId, inv)));
        registry.register(inventoryCrafting = register("inventory_crafting", (windowId, inv, data) -> new InventoryCraftingContainer(windowId, inv)));
    }

    @SuppressWarnings("unchecked")
    private static <T extends Container> ContainerType<T> register(String name, IContainerFactory<T> containerFactory) {
        return (ContainerType<T>) new ContainerType<>(containerFactory).setRegistryName(name);
    }
}
