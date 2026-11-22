package com.ultimatemod;

import java.util.function.Function;

import net.fabricmc.fabric.api.item.v1.DefaultItemComponentEvents;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public final class ModItems {
    // ---- Названия предметов (без lang-файлов, задаются прямо здесь) ----
    private static final String NAME_OBSIDIAN_INGOT = "Обсидиановый Слиток";
    private static final String NAME_IRON_STICK = "Железная палочка";
    private static final String NAME_ULTIMATE_WORKBENCH = "Ultimate Workbench";
    private static final String NAME_OBSIDIAN_PICK = "Обсидиановая Кирка";

    // ---- Материал кирки: прочность, скорость, урон, зачаровываемость ----
    public static final TagKey<Item> REPAIRS_OBSIDIAN_TOOLS =
            TagKey.of(RegistryKeys.ITEM, Identifier.of(UltimateMod.MOD_ID, "repairs_obsidian_tools"));

    public static final ToolMaterial OBSIDIAN_MATERIAL = new ToolMaterial(
            ToolMaterial.NETHERITE.incorrectBlocksForDrops(), // ломает всё, что ломает незерит
            2000,   // прочность
            8.5f,   // скорость добычи
            3.5f,   // бонус к урону
            15,     // зачаровываемость
            REPAIRS_OBSIDIAN_TOOLS);

    // ---- Предметы ----
    public static final Item OBSIDIAN_INGOT = register("obsidian_ingot", Item::new, new Item.Settings());
    public static final Item IRON_STICK = register("iron_stick", Item::new, new Item.Settings());
    public static final Item OBSIDIAN_PICK = register("obsidian_pick", Item::new,
            new Item.Settings().pickaxe(OBSIDIAN_MATERIAL, 1.0f, -2.8f));
    public static final Item ULTIMATE_WORKBENCH = register("ultimate_workbench",
            settings -> new BlockItem(ModBlocks.ULTIMATE_WORKBENCH, settings), new Item.Settings());

    private ModItems() {
    }

    private static Item register(String name, Function<Item.Settings, Item> factory, Item.Settings settings) {
        RegistryKey<Item> key = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(UltimateMod.MOD_ID, name));
        return Registry.register(Registries.ITEM, key, factory.apply(settings.registryKey(key)));
    }

    public static void init() {
        // Названия предметов (вместо lang-файлов)
        DefaultItemComponentEvents.MODIFY.register(context -> {
            context.modify(OBSIDIAN_INGOT, b -> b.add(DataComponentTypes.ITEM_NAME, Text.literal(NAME_OBSIDIAN_INGOT)));
            context.modify(IRON_STICK, b -> b.add(DataComponentTypes.ITEM_NAME, Text.literal(NAME_IRON_STICK)));
            context.modify(ULTIMATE_WORKBENCH, b -> b.add(DataComponentTypes.ITEM_NAME, Text.literal(NAME_ULTIMATE_WORKBENCH)));
            context.modify(OBSIDIAN_PICK, b -> b.add(DataComponentTypes.ITEM_NAME, Text.literal(NAME_OBSIDIAN_PICK)));
        });

        // Креативные вкладки
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.add(OBSIDIAN_INGOT);
            entries.add(IRON_STICK);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(entries -> entries.add(OBSIDIAN_PICK));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(entries -> entries.add(ULTIMATE_WORKBENCH));
    }
}
