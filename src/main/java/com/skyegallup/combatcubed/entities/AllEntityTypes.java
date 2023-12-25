package com.skyegallup.combatcubed.entities;

import com.skyegallup.combatcubed.CombatCubedMod;
import com.skyegallup.combatcubed.entities.projectiles.Pebble;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class AllEntityTypes {
    public static DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, CombatCubedMod.ID);

    public static final DeferredHolder<EntityType<?>, EntityType<Pebble>> PEBBLE = ENTITY_TYPES.register(
        "pebble",
        () -> EntityType.Builder.of((EntityType<Pebble> entityType, Level level) -> new Pebble(entityType, level), MobCategory.MISC)
            .sized(.5f, .5f)
            .build("pebble")
    );
}
