package com.skyegallup.combatcubed.enchantments;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.MendingEnchantment;
import org.jetbrains.annotations.NotNull;

public class GuidanceEnchantment extends Enchantment {
    protected GuidanceEnchantment(Rarity rarity, EquipmentSlot[] slots) {
        super(rarity, CustomEnchantmentCategories.SLINGS, slots);
    }

    @Override
    public int getMinCost(int level) {
        return 20;
    }

    @Override
    public int getMaxCost(int level) {
        return 50;
    }

    @Override
    protected boolean checkCompatibility(@NotNull Enchantment other) {
        return !(other instanceof MendingEnchantment) && super.checkCompatibility(other);
    }
}
