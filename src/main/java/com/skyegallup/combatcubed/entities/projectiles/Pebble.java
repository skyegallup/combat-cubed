package com.skyegallup.combatcubed.entities.projectiles;

import com.skyegallup.combatcubed.entities.AllEntityTypes;
import com.skyegallup.combatcubed.items.AllItems;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.entity.projectile.ThrownEnderpearl;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class Pebble extends ThrowableProjectile implements ItemSupplier, IGuided {
    public static final float MIN_DAMAGE = 1f;
    public static final float MAX_DAMAGE = 5f;

    protected float damage = 7f;

    public Pebble(EntityType<? extends ThrowableProjectile> entityType, Level level) {
        super(entityType, level);
    }

    public Pebble(Level level, LivingEntity entity) {
        super(AllEntityTypes.PEBBLE.get(), entity, level);
    }

    @Override
    protected void onHitEntity(@NotNull EntityHitResult hitResult) {
        super.onHitEntity(hitResult);

        if (!this.level().isClientSide) {
            LivingEntity owner = (LivingEntity)this.getOwner();
            Entity target = hitResult.getEntity();
            if (!(owner != null && isGuided(owner) && areEntitiesAligned(target, owner))) {
                target.hurt(this.damageSources().thrown(this, this.getOwner()), damage);
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
    protected void defineSynchedData() {
        // we don't have any data to sync
    }


    @Override
    public ItemStack getItem() {
        return new ItemStack(AllItems.PEBBLE.get());
    }

    public void setDamageFromPower(float power) {
        this.damage = MIN_DAMAGE + ((MAX_DAMAGE - MIN_DAMAGE) * Mth.clamp(power, 0f, 1f));
    }
}
