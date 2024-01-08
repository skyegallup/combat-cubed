package com.skyegallup.combatcubed.enchantments;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.event.entity.living.LivingAttackEvent;

public class EnchantmentEffectHandlers {
    public static void onLivingAttack(LivingAttackEvent event) {
        Entity attacker = event.getSource().getEntity();
        LivingEntity target = event.getEntity();

        if (attacker instanceof LivingEntity) {
            handleFrostAspect((LivingEntity)attacker, target);
        }
    }

    private static void handleFrostAspect(LivingEntity attacker, LivingEntity target) {
        ItemStack weapon = attacker.getMainHandItem();
        if (weapon != ItemStack.EMPTY) {
            int enchantLevel = weapon.getEnchantmentLevel(AllEnchantments.FROST_ASPECT.get());
            if (enchantLevel > 0) {
                float targetHealth = target.getHealth();
                if (targetHealth > 0f) {
                    int effectDurationTicks = FrostAspectEnchantment.getEffectDuration(enchantLevel);

                    // apply Mining Fatigue I
                    target.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, effectDurationTicks, 1));

                    // apply freezing effect
                    if (target.canFreeze()) {
                        target.setTicksFrozen(target.getTicksRequiredToFreeze() + effectDurationTicks);
                    }

                    // extinguish fire
                    target.setRemainingFireTicks(0);
                }
            }
        }
    }
}
