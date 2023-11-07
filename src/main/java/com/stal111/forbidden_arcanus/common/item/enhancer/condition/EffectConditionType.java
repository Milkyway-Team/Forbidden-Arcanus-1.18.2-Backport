package com.stal111.forbidden_arcanus.common.item.enhancer.condition;

import com.mojang.serialization.Codec;

public record EffectConditionType<T extends EffectCondition>(Codec<T> codec) {
}
