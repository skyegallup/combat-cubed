package com.skyegallup.combatcubed.mixins;

import com.llamalad7.mixinextras.injector.WrapWithCondition;
import com.skyegallup.combatcubed.entities.projectiles.IGuided;
import net.minecraft.world.effect.HealOrHarmMobEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.entity.projectile.ThrownPotion;
import net.minecraft.world.level.Level;
import org.checkerframework.common.reflection.qual.Invoke;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(ThrownPotion.class)
public abstract class ThrownPotionMixins extends ThrowableItemProjectile implements IGuided {
    public ThrownPotionMixins(EntityType<? extends ThrownPotion> entityType, Level level) {
        super(entityType, level);
    }

    @WrapWithCondition(at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;addEffect(Lnet/minecraft/world/effect/MobEffectInstance;Lnet/minecraft/world/entity/Entity;)Z"), method = "applySplash")
    private boolean addDurationEffects(LivingEntity target, MobEffectInstance effectInstance, Entity _areaEffectCloud) {
        return combat_cubed$shouldApply(target, (LivingEntity)getOwner(), effectInstance.getEffect());
    }

    @WrapWithCondition(at = @At(value = "INVOKE", target = "Lnet/minecraft/world/effect/MobEffect;applyInstantenousEffect(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/entity/LivingEntity;ID)V"), method = "applySplash")
    private boolean addInstantaneousEffects(MobEffect effect, Entity _areaEffectCloud, Entity _owner, LivingEntity target, int amplifier, double d) {
        return combat_cubed$shouldApply(target, (LivingEntity)getOwner(), effect);
    }

    // TODO: adjust water bottle effects

    @Unique
    private boolean combat_cubed$shouldApply(LivingEntity target, LivingEntity attacker, MobEffect effect) {
        if (attacker != null && isGuided(attacker)) {
            boolean isBeneficial = effect.isBeneficial();
            if (effect instanceof HealOrHarmMobEffect && target.isInvertedHealAndHarm()) {
                isBeneficial = !isBeneficial;
            }

            if (isBeneficial) {
                return areEntitiesAligned(target, attacker);
            } else {
                return !areEntitiesAligned(target, attacker);
            }
        } else {
            return true;
        }
    }
}
