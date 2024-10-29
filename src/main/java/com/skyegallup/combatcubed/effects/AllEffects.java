package com.skyegallup.combatcubed.effects;

import com.skyegallup.combatcubed.CombatCubedMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class AllEffects {
    public static DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(
            Registries.MOB_EFFECT,
            CombatCubedMod.ID
    );

    public static DeferredHolder<MobEffect, MobEffect> SNARE = EFFECTS.register(
            "snare",
            () -> new SnareMobEffect(MobEffectCategory.HARMFUL, 0xFFFFFF)
                    .addAttributeModifier(
                            Attributes.MOVEMENT_SPEED,
                            SnareMobEffect.EFFECT_UUID,
                            -0.1,
                            AttributeModifier.Operation.MULTIPLY_TOTAL
                    )
                    .addAttributeModifier(
                            Attributes.FLYING_SPEED,
                            SnareMobEffect.EFFECT_UUID,
                            -1.0,
                            AttributeModifier.Operation.MULTIPLY_TOTAL
                    )
    );
}
