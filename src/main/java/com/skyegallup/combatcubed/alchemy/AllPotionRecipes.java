package com.skyegallup.combatcubed.alchemy;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.brewing.BrewingRecipeRegistry;

public class AllPotionRecipes {
    public static void addPotionRecipes() {  // must be called using enqueueWork()
        BrewingRecipeRegistry.addRecipe(  // giant
            Ingredient.of(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.AWKWARD)),
            Ingredient.of(Items.GOLDEN_APPLE),
            PotionUtils.setPotion(new ItemStack(Items.POTION), AllPotions.GIANT.get())
        );
        BrewingRecipeRegistry.addRecipe(  // long_giant
                Ingredient.of(PotionUtils.setPotion(new ItemStack(Items.POTION), AllPotions.GIANT.get())),
                Ingredient.of(Items.REDSTONE),
                PotionUtils.setPotion(new ItemStack(Items.POTION), AllPotions.LONG_GIANT.get())
        );
        BrewingRecipeRegistry.addRecipe(  // strong_giant
                Ingredient.of(PotionUtils.setPotion(new ItemStack(Items.POTION), AllPotions.GIANT.get())),
                Ingredient.of(Items.GLOWSTONE_DUST),
                PotionUtils.setPotion(new ItemStack(Items.POTION), AllPotions.STRONG_GIANT.get())
        );
    }
}
