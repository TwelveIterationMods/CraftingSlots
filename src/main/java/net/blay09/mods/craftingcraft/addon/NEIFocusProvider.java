package net.blay09.mods.craftingcraft.addon;

import codechicken.nei.LayoutManager;
import net.blay09.mods.craftingcraft.client.GuiFocusProvider;

public class NEIFocusProvider implements GuiFocusProvider {
    @Override
    public boolean isTextboxFocused() {
        return LayoutManager.getInputFocused() != null;
    }
}
