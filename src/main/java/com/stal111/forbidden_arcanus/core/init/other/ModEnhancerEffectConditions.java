package com.stal111.forbidden_arcanus.core.init.other;

import com.mojang.serialization.Codec;
import com.stal111.forbidden_arcanus.ForbiddenArcanus;
import com.stal111.forbidden_arcanus.common.item.enhancer.condition.EffectCondition;
import com.stal111.forbidden_arcanus.common.item.enhancer.condition.EffectConditionType;
import com.stal111.forbidden_arcanus.common.item.enhancer.condition.TimeCondition;
import com.stal111.forbidden_arcanus.core.registry.FARegistries;
import com.stal111.forbidden_arcanus.util.backports.valhelsia_core.registry.RegistryClass;
import com.stal111.forbidden_arcanus.util.backports.valhelsia_core.registry.helper.MappedRegistryHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModEnhancerEffectConditions implements RegistryClass {
    
    public static final MappedRegistryHelper<EffectConditionType<?>> HELPER = ForbiddenArcanus.REGISTRY_MANAGER.getMappedHelper(FARegistries.ENHANCER_EFFECT_CONDITION);
    
    public static final RegistryObject<EffectConditionType<TimeCondition>> TIME = register("time", TimeCondition.CODEC);
    
    public static <T extends EffectCondition> RegistryObject<EffectConditionType<T>> register(String name, Codec<T> codec) {
        return HELPER.register(name, () -> new EffectConditionType<>(codec));
    }
}
