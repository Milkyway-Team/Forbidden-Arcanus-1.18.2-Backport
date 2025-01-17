package com.stal111.forbidden_arcanus.common.item.enhancer;

import com.stal111.forbidden_arcanus.core.registry.FARegistries;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.item.Item;

import java.util.HashMap;
import java.util.Optional;

public class EnhancerCache {
    private static final HashMap<Item, EnhancerDefinition> ENHANCER_BY_ITEM = new HashMap<>();
    
    public static void cacheEnhancers(RegistryAccess registryAccess) {
        ENHANCER_BY_ITEM.clear();
        
        Registry<EnhancerDefinition> registry = registryAccess.registryOrThrow(FARegistries.ENHANCER_DEFINITION);
        
        registry.holders().map(Holder::direct).forEach(definition -> {
            ENHANCER_BY_ITEM.put(definition.value().value().item(), definition.value().value());
        });
    }
    
    public static Optional<EnhancerDefinition> get(Item item) {
        return Optional.ofNullable(ENHANCER_BY_ITEM.get(item));
    }
}
