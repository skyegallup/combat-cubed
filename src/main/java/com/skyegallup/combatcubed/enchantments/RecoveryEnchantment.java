package com.skyegallup.combatcubed.enchantments;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.MendingEnchantment;
import net.minecraft.world.item.enchantment.ThornsEnchantment;
import org.jetbrains.annotations.NotNull;

public class RecoveryEnchantment extends Enchantment {
    protected RecoveryEnchantment(Rarity rarity, EquipmentSlot[] slots) {
        super(rarity, CustomEnchantmentCategories.SHIELDS, slots);
    }

    @Override
    public int getMinCost(int level) {
        return 8 + 18 * (level - 1);
    }

    @Override
    public int getMaxCost(int level) {
        return getMinCost(level) + 40;
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    protected boolean checkCompatibility(@NotNull Enchantment other) {
        return !(other instanceof ThornsEnchantment) && super.checkCompatibility(other);
    }


}
