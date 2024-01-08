package com.skyegallup.combatcubed.alchemy;

import com.skyegallup.combatcubed.CombatCubedMod;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.alchemy.Potion;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class AllPotions {
    public static DeferredRegister<Potion> POTIONS = DeferredRegister.create(BuiltInRegistries.POTION, CombatCubedMod.ID);

    public static DeferredHolder<Potion, Potion> GIANT = POTIONS.register(
        "giant",
        () -> new Potion(
            "giant",
            new MobEffectInstance(MobEffects.HEALTH_BOOST, 20 * 60),
            new MobEffectInstance(MobEffects.HEAL, 1)
        )
    );
    public static DeferredHolder<Potion, Potion> LONG_GIANT = POTIONS.register(
        "long_giant",
        () -> new Potion(
            "giant",
            new MobEffectInstance(MobEffects.HEALTH_BOOST, 20 * 60 * 2),
            new MobEffectInstance(MobEffects.HEAL, 1)
        )
    );
    public static DeferredHolder<Potion, Potion> STRONG_GIANT = POTIONS.register(
        "strong_giant",
            () -> new Potion(
                "giant",
                new MobEffectInstance(MobEffects.HEALTH_BOOST, 20 * 60, 1),
                new MobEffectInstance(MobEffects.HEAL, 1, 1)
            )
    );
}
