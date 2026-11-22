package com.ultimatemod;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public final class ModBlocks {
    public static final Block ULTIMATE_WORKBENCH = registerBlock("ultimate_workbench");

    private ModBlocks() {
    }

    private static Block registerBlock(String name) {
        RegistryKey<Block> key = RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(UltimateMod.MOD_ID, name));
        Block block = new UltimateWorkbenchBlock(
                AbstractBlock.Settings.create()
                        .registryKey(key)
                        .strength(2.5f)
                        .sounds(BlockSoundGroup.WOOD));
        return Registry.register(Registries.BLOCK, key, block);
    }

    public static void init() {
    }
}
