package com.skyegallup.combatcubed.entities.projectiles;

import com.skyegallup.combatcubed.items.AllItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.neoforged.neoforge.event.EventHooks;
import org.jetbrains.annotations.NotNull;

public class ThrownFireCharge extends Pebble {
    public ThrownFireCharge(EntityType<? extends ThrowableProjectile> entityType, Level level) {
        super(entityType, level);
    }

    public ThrownFireCharge(Level level, LivingEntity entity) {
        super(level, entity);
    }

    @Override
    protected void onHitEntity(@NotNull EntityHitResult hitResult) {
        super.onHitEntity(hitResult);

        if (!this.level().isClientSide) {
            LivingEntity owner = (LivingEntity)this.getOwner();
            Entity target = hitResult.getEntity();
            if (!(owner != null && isGuided(owner) && areEntitiesAligned(target, owner))) {
                target.setSecondsOnFire(5);
            }
        }
    }

    @Override
    protected void onHitBlock(BlockHitResult hitResult) {
        // code pulled from SmallFireball::onHitBlock
        super.onHitBlock(hitResult);

        if (!this.level().isClientSide) {
            Entity entity = this.getOwner();
            if (!(entity instanceof Mob) || EventHooks.getMobGriefingEvent(this.level(), entity)) {
                BlockPos blockpos = hitResult.getBlockPos().relative(hitResult.getDirection());
                if (this.level().isEmptyBlock(blockpos)) {
                    this.level().setBlockAndUpdate(blockpos, BaseFireBlock.getState(this.level(), blockpos));
                }
            }
        }
    }

    @Override
    public ItemStack getItem() {
        return new ItemStack(Items.FIRE_CHARGE);
    }
}
