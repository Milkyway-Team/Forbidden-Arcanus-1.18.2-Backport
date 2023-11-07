package com.stal111.forbidden_arcanus.util.backports.valhelsia_core.registry;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.stal111.forbidden_arcanus.util.backports.BootstapContext;
import com.stal111.forbidden_arcanus.util.backports.valhelsia_core.DataProviderInfo;
import com.stal111.forbidden_arcanus.util.backports.valhelsia_core.registry.helper.MappedRegistryHelper;
import com.stal111.forbidden_arcanus.util.backports.valhelsia_core.registry.helper.RegistryHelper;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import org.apache.commons.lang3.function.TriFunction;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public abstract class RegistryCollector {
    private final Map<ResourceKey<? extends Registry<?>>, RegistryHelper<?, ?>> registryHelpers = new HashMap<>();
    
    private final String modId;
    
    public RegistryCollector(String modId) {
        this.modId = modId;
        
        this.collect();
    }
    
    /**
     * Register all {@link RegistryHelper}s here.
     */
    protected abstract void collect();
    
    @SafeVarargs
    public final void addBlockHelper(Supplier<RegistryClass>... registryClasses) {
        this.addMappedHelper(Registry.BLOCK, BlockRegistryHelper::new, registryClasses);
    }
    
    @SafeVarargs
    public final void addItemHelper(Supplier<RegistryClass>... registryClasses) {
        this.addMappedHelper(Registry.ITEM, ItemRegistryHelper::new, registryClasses);
    }
    
    @SafeVarargs
    public final <T> void addMappedHelper(ResourceKey<Registry<T>> key, Supplier<RegistryClass>... registryClasses) {
        this.registryHelpers.put(key, new MappedRegistryHelper<>(key, this.modId, ImmutableList.copyOf(registryClasses)));
    }
    
    @SafeVarargs
    public final <T> void addMappedHelper(ResourceKey<Registry<T>> key, TriFunction<ResourceKey<Registry<T>>, String, ImmutableList<Supplier<RegistryClass>>, MappedRegistryHelper<T>> registryHelper, Supplier<RegistryClass>... registryClasses) {
        this.registryHelpers.put(key, registryHelper.apply(key, this.modId, ImmutableList.copyOf(registryClasses)));
    }
    
    @SafeVarargs
    public final <T> void addDatapackHelper(ResourceKey<Registry<T>> key, BiFunction<DataProviderInfo, BootstapContext<T>, DatapackRegistryClass<T>>... classCollectors) {
        this.registryHelpers.put(key, new DatapackRegistryHelper<>(key, this.modId, (info, context) -> Arrays.stream(classCollectors).map(function -> function.apply(info, (BootstapContext<T>) context)).collect(Collectors.toUnmodifiableList())));
    }
    
    public final <T> void addDatapackHelper(ResourceKey<Registry<T>> key, TriFunction<ResourceKey<Registry<T>>, String, DatapackClassCollector, DatapackRegistryHelper<T>> registryHelper, DatapackClassCollector classCollector) {
        this.registryHelpers.put(key, registryHelper.apply(key, this.modId, classCollector));
    }
    
    protected ImmutableMap<ResourceKey<? extends Registry<?>>, RegistryHelper<?, ?>> getHelpers() {
        return ImmutableMap.copyOf(this.registryHelpers);
    }
    
    protected String getModId() {
        return this.modId;
    }
    
    @FunctionalInterface
    public interface DatapackClassCollector {
        List<DatapackRegistryClass<?>> collect(DataProviderInfo info, BootstapContext<?> context);
    }
}
