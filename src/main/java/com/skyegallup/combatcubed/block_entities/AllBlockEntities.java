package com.skyegallup.combatcubed.block_entities;

import com.skyegallup.combatcubed.CombatCubedMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.HashSet;

public class AllBlockEntities {
    public static DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(
            Registries.BLOCK_ENTITY_TYPE.registry(),
            CombatCubedMod.ID
    );

    public static DeferredHolder<BlockEntityType<?>, BlockEntityType<BallistaBlockEntity>> BALLISTA = BLOCK_ENTITY_TYPES.register(
            "ballista",
            () -> new BlockEntityType<>(
                    BallistaBlockEntity::new,
                    new HashSet<>(),
                    null
            )
    );
}
