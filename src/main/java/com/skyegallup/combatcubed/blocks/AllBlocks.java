package com.skyegallup.combatcubed.blocks;

import com.skyegallup.combatcubed.CombatCubedMod;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class AllBlocks {
    public static DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(CombatCubedMod.ID);

    public static DeferredBlock<Block> BALLISTA = BLOCKS.registerBlock(
            "ballista",
            BallistaBlock::new,
            BlockBehaviour.Properties.of()
    );
}
