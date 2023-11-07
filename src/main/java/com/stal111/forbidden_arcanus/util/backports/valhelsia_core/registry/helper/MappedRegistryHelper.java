package com.stal111.forbidden_arcanus.util.backports.valhelsia_core.registry.helper;

import com.google.common.collect.ImmutableList;
import com.google.errorprone.annotations.DoNotCall;
import com.stal111.forbidden_arcanus.util.backports.valhelsia_core.registry.RegistryClass;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.Collection;
import java.util.function.Supplier;

public class MappedRegistryHelper<T> extends RegistryHelper<T, RegistryClass> {
    
    private final DeferredRegister<T> deferredRegister;
    private final ImmutableList<Supplier<RegistryClass>> registryClasses;
    
    public MappedRegistryHelper(ResourceKey<? extends Registry<T>> registry, String modId, ImmutableList<Supplier<RegistryClass>> registryClasses) {
        super(registry, modId);
        this.deferredRegister = this.createDeferredRegister(registry, modId);
        this.registryClasses = registryClasses;
    }
    
    private DeferredRegister<T> createDeferredRegister(ResourceKey<? extends Registry<?>> key, String modId) {
        return DeferredRegister.create(key.location(), modId);
    }
    
    public Collection<RegistryObject<T>> getRegistryObjects() {
        return this.deferredRegister.getEntries();
    }
    
    /**
     * Registers the {@link DeferredRegister}.
     * You will never need to call this method yourself.
     *
     * @param eventBus the mod event bus
     */
    @DoNotCall
    public final void registerDeferredRegister(IEventBus eventBus) {
        this.deferredRegister.register(eventBus);
    }
    
    public final <O extends T> RegistryObject<O> register(String name, Supplier<O> object) {
        return this.deferredRegister.register(name, object);
    }
    
    @Override
    public ImmutableList<Supplier<RegistryClass>> getRegistryClasses() {
        return this.registryClasses;
    }
}
