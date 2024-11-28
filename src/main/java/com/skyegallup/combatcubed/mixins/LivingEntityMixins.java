package com.skyegallup.combatcubed.mixins;

import com.llamalad7.mixinextras.injector.WrapWithCondition;
import com.skyegallup.combatcubed.effects.AllEffects;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.Attackable;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.extensions.ILivingEntityExtension;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixins extends Entity implements Attackable, ILivingEntityExtension {
    @Shadow public abstract boolean hasEffect(MobEffect p_21024_);

    public LivingEntityMixins(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    @WrapWithCondition(method = "setSprinting", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/ai/attributes/AttributeInstance;addTransientModifier(Lnet/minecraft/world/entity/ai/attributes/AttributeModifier;)V"))
    public boolean onlySprintIfNotAllowed(AttributeInstance instance, AttributeModifier modifier) {
        return !this.hasEffect(AllEffects.SNARE.get());
    }
}
