package com.skyegallup.combatcubed.enchantments;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import org.jetbrains.annotations.NotNull;

public class FrostAspectEnchantment extends Enchantment {
    public static int DURATION_TICKS_PER_LEVEL = 4 * 20;  // duration per level

    protected FrostAspectEnchantment(Rarity rarity, EquipmentSlot[] slots) {
        super(rarity, CustomEnchantmentCategories.AXES, slots);
    }

    @Override
    public int getMinCost(int level) {
        return 10 + 20 * (level - 1);
    }

    @Override
    public int getMaxCost(int level) {
        return super.getMinCost(level) + 50;
    }

    @Override
    public int getMaxLevel() {
        return 2;
    }

    @Override
    protected boolean checkCompatibility(@NotNull Enchantment other) {
        return super.checkCompatibility(other) && other != Enchantments.FIRE_ASPECT;
    }

    public static int getEffectDuration(int level) {
        return DURATION_TICKS_PER_LEVEL * level;
    }
}
