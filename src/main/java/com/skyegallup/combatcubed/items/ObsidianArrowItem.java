package com.skyegallup.combatcubed.items;

import com.skyegallup.combatcubed.entities.AllEntityTypes;
import com.skyegallup.combatcubed.entities.projectiles.ObsidianArrow;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class ObsidianArrowItem extends ArrowItem {
    public ObsidianArrowItem(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull AbstractArrow createArrow(@NotNull Level level, @NotNull ItemStack itemStack, @NotNull LivingEntity attacker) {
        return new ObsidianArrow(AllEntityTypes.OBSIDIAN_ARROW.get(), attacker, level);
    }
}
