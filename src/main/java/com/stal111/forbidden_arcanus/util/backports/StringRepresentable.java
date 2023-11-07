package com.stal111.forbidden_arcanus.util.backports;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.DynamicOps;
import com.mojang.serialization.Keyable;
import net.minecraft.util.ExtraCodecs;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.annotation.Nullable;

public interface StringRepresentable {
    int PRE_BUILT_MAP_THRESHOLD = 16;
    String getSerializedName();
    static <E extends Enum<E> & StringRepresentable> StringRepresentable.EnumCodec<E> fromEnum(Supplier<E[]> var0) {
        Enum[] var1 = (Enum[])var0.get();
        if (var1.length > 16) {
            Map var2 = (Map) Arrays.stream(var1).collect(Collectors.toMap(var0x -> ((StringRepresentable)var0x).getSerializedName(), var0x -> var0x));
            return new EnumCodec<>((E[])var1, var1x -> var1x == null ? null : (E) var2.get(var1x));
        } else {
            return new EnumCodec<>((E[])var1, var1x -> {
                for(Enum var5 : var1) {
                    if (((StringRepresentable)var5).getSerializedName().equals(var1x)) {
                        return (E) var5;
                    }
                }
                return null;
            });
        }
    }
    static Keyable keys(final StringRepresentable[] var0) {
        return new Keyable() {
            @Override
            public <T> Stream<T> keys(DynamicOps<T> var1) {
                return Arrays.stream(var0).map(StringRepresentable::getSerializedName).map(var1::createString);
            }
        };
    }
    @Deprecated
    public static class EnumCodec<E> implements Codec<E> {
        private Codec<E> codec;
        private Function<String, E> resolver;
        public EnumCodec(E[] var1, Function<String, E> var2) {
            this.codec = ExtraCodecs.orCompressed(
                    ExtraCodecs.stringResolverCodec(var0 -> ((StringRepresentable)var0).getSerializedName(), var2),
                    ExtraCodecs.idResolverCodec(var0 -> ((Enum)var0).ordinal(), var1x -> var1x >= 0 && var1x < var1.length ? var1[var1x] : null, -1)
            );
            this.resolver = var2;
        }
        @Override
        public <T> DataResult<Pair<E, T>> decode(DynamicOps<T> var1, T var2) {
            return this.codec.decode(var1, (T)var2);
        }
        @Nullable
        public E byName(@Nullable String var1) {
            return (E)this.resolver.apply(var1);
        }
        // $FF: synthetic method
        @Override
        public DataResult encode(Object var1, DynamicOps var2, Object var3) {
            return this.encode((E)var1, var2, var3);
        }
    }
}
