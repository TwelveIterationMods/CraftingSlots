package net.blay09.mods.craftingcraft.addon;

import codechicken.nei.OffsetPositioner;
import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;
import codechicken.nei.recipe.DefaultOverlayHandler;
import net.blay09.mods.craftingcraft.client.GuiCraftCrafting;
import net.blay09.mods.craftingcraft.client.GuiInventoryCrafting;

public class NEICraftingCraftConfig implements IConfigureNEI {

    @Override
    public void loadConfig() {
        API.registerGuiOverlay(GuiCraftCrafting.class, "crafting", new OffsetPositioner(5, 11));
        API.registerGuiOverlayHandler(GuiCraftCrafting.class, new DefaultOverlayHandler(5, 11), "crafting");

        API.registerGuiOverlay(GuiInventoryCrafting.class, "crafting", new OffsetPositioner(94, 14));
        API.registerGuiOverlayHandler(GuiInventoryCrafting.class, new InventoryCraftingOverlayHandler(94, 14), "crafting");
    }

    @Override
    public String getName() {
        return "CraftingCraft";
    }

    @Override
    public String getVersion() {
        return "1.0";
    }

}
