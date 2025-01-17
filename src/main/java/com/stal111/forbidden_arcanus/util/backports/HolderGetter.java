package com.stal111.forbidden_arcanus.util.backports;

import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;

import java.util.Optional;

public interface HolderGetter<T> {
    Optional<Holder.Reference<T>> get(ResourceKey<T> var1);
    default Holder.Reference<T> getOrThrow(ResourceKey<T> var1) {
        return (Holder.Reference<T>)this.get(var1).orElseThrow(() -> new IllegalStateException("Missing element " + var1));
    }
    Optional<HolderSet.Named<T>> get(TagKey<T> var1);
    default HolderSet.Named<T> getOrThrow(TagKey<T> var1) {
        return (HolderSet.Named<T>)this.get(var1).orElseThrow(() -> new IllegalStateException("Missing tag " + var1));
    }
    public interface Provider {
        <T> Optional<HolderGetter<T>> lookup(ResourceKey<? extends Registry<? extends T>> var1);
        default <T> HolderGetter<T> lookupOrThrow(ResourceKey<? extends Registry<? extends T>> var1) {
            return (HolderGetter<T>)this.lookup(var1).orElseThrow(() -> new IllegalStateException("Registry " + var1.location() + " not found"));
        }
    }
}
