package com.skyegallup.combatcubed.entities.projectiles;

import com.skyegallup.combatcubed.enchantments.AllEnchantments;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.scores.Team;

public interface IGuided {
    default boolean isGuided(LivingEntity owner) {
        return EnchantmentHelper.getEnchantmentLevel(AllEnchantments.GUIDANCE.get(), owner) > 0;
    }

    default boolean areEntitiesAligned(Entity target, LivingEntity attacker) {
        Team attackerTeam = attacker.getTeam();
        Team targetTeam = target.getTeam();

        if (target.is(attacker)) {
            return true;
        } else if (attackerTeam != null && attackerTeam.isAlliedTo(targetTeam)) {
            return true;
        } else {
            boolean attackerIsPlayer = attacker instanceof Player;
            boolean targetIsPlayer = target instanceof Player;
            return attackerIsPlayer && targetIsPlayer;
        }
    }
}
