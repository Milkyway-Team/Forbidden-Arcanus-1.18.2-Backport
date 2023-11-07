package com.stal111.forbidden_arcanus.common.block.entity.forge.essence;

import com.mojang.datafixers.util.Either;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.stal111.forbidden_arcanus.util.backports.Components;
import com.stal111.forbidden_arcanus.util.backports.StringRepresentable;
import net.minecraft.network.chat.MutableComponent;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Function;

public enum EssenceType implements StringRepresentable {
    AUREAL("aureal"),
    SOULS("souls"),
    BLOOD("blood"),
    EXPERIENCE("experience");
    
    public static final StringRepresentable.EnumCodec<EssenceType> CODEC = StringRepresentable.fromEnum(EssenceType::values);
    
    @SuppressWarnings("UnclearExpression")
    public static final Codec<List<EssenceType>> ONE_OR_MULTIPLE_CODEC = Codec.either(EssenceType.CODEC, EssenceType.CODEC.listOf()).flatComapMap(either -> {
        return either.map(List::of, Function.identity());
    }, list -> {
        return list.size() == 1 ? DataResult.success(Either.left(list.get(0))) : DataResult.error("Must have exactly one element: " + list);
    });
    
    private final String name;
    private final MutableComponent component;
    
    EssenceType(String name) {
        this.name = name;
        this.component = Components.translatable("forbidden_arcanus.essence." + name);
    }
    
    public MutableComponent getComponent() {
        return this.component;
    }
    
    @Override
    public @NotNull String getSerializedName() {
        return this.name;
    }
}
