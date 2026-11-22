package com.ultimatemod;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.CraftingScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.server.world.ServerWorld;

/**
 * Обычный экран верстака (тип CRAFTING, поэтому клиентский мод не нужен),
 * но на сервере дополнительно проверяются рецепты Ultimate Workbench.
 */
public class UltimateCraftingScreenHandler extends CraftingScreenHandler {
    private final ScreenHandlerContext ultimateContext;

    public UltimateCraftingScreenHandler(int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
        super(syncId, playerInventory, context);
        this.ultimateContext = context;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return canUse(this.ultimateContext, player, ModBlocks.ULTIMATE_WORKBENCH);
    }

    @Override
    public void onContentChanged(Inventory inventory) {
        // Сначала обычные рецепты
        super.onContentChanged(inventory);

        // Если обычного рецепта нет, пробуем рецепты Ultimate Workbench
        this.ultimateContext.run((world, pos) -> {
            if (!(world instanceof ServerWorld)) {
                return;
            }
            if (!this.craftingResultInventory.getStack(0).isEmpty()) {
                return;
            }
            ItemStack result = UltimateRecipes.match(this.craftingInventory);
            if (!result.isEmpty()) {
                this.craftingResultInventory.setStack(0, result);
            }
        });
    }
}
