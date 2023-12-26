package com.skyegallup.combatcubed.entities.projectiles;

import com.skyegallup.combatcubed.items.AllItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ObsidianArrow extends AbstractArrow {
    protected static final double BASE_DAMAGE = 2.5;
    protected static final ItemStack DEFAULT_ITEM_STACK = new ItemStack(AllItems.OBSIDIAN_ARROW.get());

    public ObsidianArrow(EntityType<? extends AbstractArrow> entityType, Level level) {
        super(entityType, level, DEFAULT_ITEM_STACK);
        setBaseDamage(BASE_DAMAGE);
    }

    public ObsidianArrow(EntityType<? extends AbstractArrow> entityType, double dx, double dy, double dz, Level level) {
        super(entityType, dx, dy, dz, level, DEFAULT_ITEM_STACK);
        setBaseDamage(BASE_DAMAGE);
    }

    public ObsidianArrow(EntityType<? extends AbstractArrow> entityType, LivingEntity attacker, Level level) {
        super(entityType, attacker, level, DEFAULT_ITEM_STACK);
        setBaseDamage(BASE_DAMAGE);
    }
}
