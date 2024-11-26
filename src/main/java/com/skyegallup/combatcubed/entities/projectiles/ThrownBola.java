package com.skyegallup.combatcubed.entities.projectiles;

import com.skyegallup.combatcubed.effects.AllEffects;
import com.skyegallup.combatcubed.entities.AllEntityTypes;
import com.skyegallup.combatcubed.items.AllItems;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

public class ThrownBola extends ThrowableProjectile implements ItemSupplier {
    public ThrownBola(EntityType<? extends ThrowableProjectile> entityType, Level level) {
        super(entityType, level);
    }

    public ThrownBola(Level level, LivingEntity entity) {
        super(AllEntityTypes.THROWN_BOLA.get(), entity, level);
    }

    @Override
    protected void onHitEntity(@NotNull EntityHitResult hitResult) {
        super.onHitEntity(hitResult);

        if (!this.level().isClientSide) {
            LivingEntity owner = (LivingEntity)this.getOwner();
            Entity target = hitResult.getEntity();

            if (target instanceof LivingEntity) {
                MobEffectInstance effectInstance = new MobEffectInstance(AllEffects.SNARE.get(), 5);
                ((LivingEntity) target).addEffect(effectInstance);
            }
        }
    }

    @Override
    protected void onHit(HitResult hitResult) {
        super.onHit(hitResult);
        if (!this.level().isClientSide) {
            // TODO: send entity event to spawn particles
            this.discard();
        }
    }

    @Override
    public void tick() {
        super.tick();

        // Apply slow falling behavior (borrowed from Chicken class)
        Vec3 vec3 = this.getDeltaMovement();
        if (vec3.y < 0.0) {
            this.setDeltaMovement(vec3.multiply(1.0, 0.7, 1.0));
        }
    }

    @Override
    protected void defineSynchedData() {
        // we don't have any data to sync
    }


    @Override
    public ItemStack getItem() {
        return new ItemStack(AllItems.BOLA.get());
    }
}
