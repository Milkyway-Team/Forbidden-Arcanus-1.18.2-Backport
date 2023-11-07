package com.stal111.forbidden_arcanus.common.item.enhancer;

import com.mojang.serialization.Codec;

public record EnhancerEffectType<T extends EnhancerEffect>(Codec<T> codec, EnhancerTarget target) {
}
