package com.stal111.forbidden_arcanus.util.backports.valhelsia_core.registry.helper;

import com.google.common.collect.ImmutableList;
import com.stal111.forbidden_arcanus.util.backports.valhelsia_core.registry.RegistryClass;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;

import java.util.function.Supplier;

public abstract class RegistryHelper<T, R extends RegistryClass> {
    
    private final ResourceKey<? extends Registry<T>> registry;
    private final String modId;
    
    protected RegistryHelper(ResourceKey<? extends Registry<T>> registry, String modId) {
        this.registry = registry;
        this.modId = modId;
    }
    
    public ResourceKey<? extends Registry<T>> getRegistry() {
        return this.registry;
    }
    
    public String getModId() {
        return this.modId;
    }
    
    public abstract ImmutableList<Supplier<R>> getRegistryClasses();
}
