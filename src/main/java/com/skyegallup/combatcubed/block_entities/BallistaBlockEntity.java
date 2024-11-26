package com.skyegallup.combatcubed.block_entities;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.util.Lazy;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class BallistaBlockEntity extends BlockEntity {
    // see: https://www.mcjty.eu/docs/1.20.4_neo/ep2#the-block-entity-class
    public static final String ITEMS_TAG = "Inventory";

    private final ItemStackHandler items = createItemHandler();
    private final Lazy<IItemHandler> itemHandler = Lazy.of(() -> items);

    public BallistaBlockEntity(BlockPos pos, BlockState state) {
        super(AllBlockEntities.BALLISTA.get(), pos, state);
    }

    @Nonnull
    private ItemStackHandler createItemHandler() {
        return new ItemStackHandler(5) {
            @Override
            protected void onContentsChanged(int slot) {
                setChanged();
                level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), Block.UPDATE_ALL);
            }
        };
    }

    public IItemHandler getItemHandler() {
        return itemHandler.get();
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        saveClientData(tag);
    }

    private void saveClientData(CompoundTag tag) {
        tag.put(ITEMS_TAG, items.serializeNBT());
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        loadClientData(tag);
    }

    private void loadClientData(CompoundTag tag) {
        if (tag.contains(ITEMS_TAG)) {
            items.deserializeNBT(tag.getCompound(ITEMS_TAG));
        }
    }

    // The getUpdateTag()/handleUpdateTag() pair is called whenever the client receives a new chunk
    // it hasn't seen before. i.e. the chunk is loaded
    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag tag = super.getUpdateTag();
        saveClientData(tag);
        return tag;
    }

    @Override
    public void handleUpdateTag(CompoundTag tag) {
        if (tag != null) {
            loadClientData(tag);
        }
    }

    // The getUpdatePacket()/onDataPacket() pair is used when a block update happens on the client
    // (a blockstate change or an explicit notificiation of a block update from the server). It's
    // easiest to implement them based on getUpdateTag()/handleUpdateTag()
    @Nullable
    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        // This is called client side
        CompoundTag tag = pkt.getTag();
        // This will call loadClientData()
        if (tag != null) {
            handleUpdateTag(tag);
        }
    }
}
