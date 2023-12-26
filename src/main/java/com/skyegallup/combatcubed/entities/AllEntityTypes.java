package com.skyegallup.combatcubed.entities;

import com.skyegallup.combatcubed.CombatCubedMod;
import com.skyegallup.combatcubed.entities.projectiles.ObsidianArrow;
import com.skyegallup.combatcubed.entities.projectiles.Pebble;
import com.skyegallup.combatcubed.entities.projectiles.ThrownFireCharge;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class AllEntityTypes {
    public static DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, CombatCubedMod.ID);

    public static final DeferredHolder<EntityType<?>, EntityType<ObsidianArrow>> OBSIDIAN_ARROW = ENTITY_TYPES.register(
        "obsidian_arrow",
        () -> EntityType.Builder.of((EntityType<ObsidianArrow> entityType, Level level) -> new ObsidianArrow(entityType, level), MobCategory.MISC)
            .sized(.5f, .5f)
            .clientTrackingRange(4)
            .updateInterval(20)
            .build("obsidian_arrow")
    );

    public static final DeferredHolder<EntityType<?>, EntityType<Pebble>> PEBBLE = ENTITY_TYPES.register(
        "pebble",
        () -> EntityType.Builder.of((EntityType<Pebble> entityType, Level level) -> new Pebble(entityType, level), MobCategory.MISC)
            .sized(.5f, .5f)
            .clientTrackingRange(4)
            .updateInterval(20)
            .build("pebble")
    );

    public static final DeferredHolder<EntityType<?>, EntityType<ThrownFireCharge>> THROWN_FIRE_CHARGE = ENTITY_TYPES.register(
        "thrown_fire_charge",
            () -> EntityType.Builder.of((EntityType<ThrownFireCharge> entityType, Level level) -> new ThrownFireCharge(entityType, level), MobCategory.MISC)
                .sized(.5f, .5f)
                .clientTrackingRange(4)
                .updateInterval(20)
                .build("thrown_fire_charge")
    );
}
