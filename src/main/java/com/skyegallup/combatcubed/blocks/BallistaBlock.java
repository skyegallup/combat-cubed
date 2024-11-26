package com.skyegallup.combatcubed.blocks;

import com.skyegallup.combatcubed.block_entities.BallistaBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class BallistaBlock extends Block implements EntityBlock {
    public BallistaBlock(Properties properties) {
        super(properties);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new BallistaBlockEntity(pos, state);
    }
}
