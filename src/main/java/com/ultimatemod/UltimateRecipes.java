package com.ultimatemod;

import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * Рецепты, которые работают ТОЛЬКО в Ultimate Workbench.
 * Сетка 3x3: слоты 0-2 = ряд A, 3-5 = ряд B, 6-8 = ряд C.
 */
public final class UltimateRecipes {
    private UltimateRecipes() {
    }

    public static ItemStack match(Inventory grid) {
        if (grid.size() != 9) {
            return ItemStack.EMPTY;
        }

        // Обсидиановая Кирка:
        // A1 A2 A3 = Обсидиановый слиток, B2 = Железная палочка, C2 = Железная палочка
        Item ingot = ModItems.OBSIDIAN_INGOT;
        Item stick = ModItems.IRON_STICK;
        if (grid.getStack(0).isOf(ingot) && grid.getStack(1).isOf(ingot) && grid.getStack(2).isOf(ingot)
                && grid.getStack(3).isEmpty() && grid.getStack(4).isOf(stick) && grid.getStack(5).isEmpty()
                && grid.getStack(6).isEmpty() && grid.getStack(7).isOf(stick) && grid.getStack(8).isEmpty()) {
            return new ItemStack(ModItems.OBSIDIAN_PICK);
        }

        return ItemStack.EMPTY;
    }
}
