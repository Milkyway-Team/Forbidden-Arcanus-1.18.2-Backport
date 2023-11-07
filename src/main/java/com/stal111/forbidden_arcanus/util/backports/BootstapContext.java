package com.stal111.forbidden_arcanus.util.backports;

import com.mojang.serialization.Lifecycle;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;

public interface BootstapContext<T> {
    Holder.Reference<T> register(ResourceKey<T> var1, T var2, Lifecycle var3);
    default Holder.Reference<T> register(ResourceKey<T> var1, T var2) {
        return this.register(var1, (T)var2, Lifecycle.stable());
    }
    <S> HolderGetter<S> lookup(ResourceKey<? extends Registry<? extends S>> var1);
}
