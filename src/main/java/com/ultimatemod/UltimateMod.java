package com.ultimatemod;

import net.fabricmc.api.ModInitializer;

public class UltimateMod implements ModInitializer {
    public static final String MOD_ID = "ultimatemod";

    @Override
    public void onInitialize() {
        ModBlocks.init();
        ModItems.init();
    }
}
