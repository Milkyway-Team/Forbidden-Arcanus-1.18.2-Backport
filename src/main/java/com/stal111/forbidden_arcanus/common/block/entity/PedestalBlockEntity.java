package com.stal111.forbidden_arcanus.common.block.entity;

import com.stal111.forbidden_arcanus.common.network.NetworkHandler;
import com.stal111.forbidden_arcanus.common.network.clientbound.UpdatePedestalPacket;
import com.stal111.forbidden_arcanus.core.init.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Pedestal Block Entity <br>
 * Forbidden Arcanus - com.stal111.forbidden_arcanus.common.block.entity.PedestalBlockEntity
 *
 * @author stal111
 * @version 1.17.1 - 2.0.0
 * @since 2021-06-25
 */
public class PedestalBlockEntity extends BlockEntity {

    private static final int DEFAULT_ITEM_HEIGHT = 120;

    private ItemStack stack = ItemStack.EMPTY;

    private final float hoverStart;
    private int ticksExisted;
    private int itemHeight = DEFAULT_ITEM_HEIGHT;

    public PedestalBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.PEDESTAL.get(), pos, state);
        this.hoverStart = (float) (Math.random() * Math.PI * 2.0D);
    }

    public static void clientTick(Level level, BlockPos pos, BlockState state, PedestalBlockEntity blockEntity) {
        blockEntity.ticksExisted++;
    }

    public void setStack(ItemStack stack) {
        this.stack = stack;
    }

    public void setStackAndSync(ItemStack stack, Level level) {
        this.stack = stack;

        if (!level.isClientSide()) {
            NetworkHandler.sendToTrackingChunk(level.getChunkAt(this.getBlockPos()), new UpdatePedestalPacket(this.getBlockPos(), stack, this.itemHeight));
        }
    }

    public ItemStack getStack() {
        return this.stack;
    }

    public boolean hasStack() {
        return !this.stack.isEmpty();
    }

    public void clearStack(Level level) {
        this.setItemHeight(DEFAULT_ITEM_HEIGHT);

        this.setStackAndSync(ItemStack.EMPTY, level);
    }

    public float getItemHover(float partialTicks) {
        return (this.ticksExisted + partialTicks) / 20.0F + this.hoverStart;
    }

    public int getItemHeight() {
        return this.itemHeight;
    }

    public void setItemHeight(int itemHeight) {
        this.itemHeight = itemHeight;
    }

    @Override
    public void load(@Nonnull CompoundTag compound) {
        super.load(compound);

        if (compound.contains("Stack")) {
            this.stack = ItemStack.of(compound.getCompound("Stack"));
            this.itemHeight = compound.getInt("ItemHeight");
        }
    }

    @Override
    public void saveAdditional(@Nonnull CompoundTag compound) {
        super.saveAdditional(compound);

        if (this.stack != ItemStack.EMPTY) {
            compound.put("Stack", this.stack.save(new CompoundTag()));
            compound.putInt("ItemHeight", this.itemHeight);
        }
    }

    @Nullable
    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Nonnull
    @Override
    public CompoundTag getUpdateTag() {
        return this.saveWithoutMetadata();
    }

    @Override
    public AABB getRenderBoundingBox() {
        return new AABB(this.getBlockPos()).expandTowards(0.0D, 1.0D, 0.0D);
    }
}
