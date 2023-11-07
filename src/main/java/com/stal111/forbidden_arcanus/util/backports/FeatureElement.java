package com.stal111.forbidden_arcanus.util.backports;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Set;

public interface FeatureElement {
    Set<ResourceKey<? extends Registry<? extends FeatureElement>>> FILTERED_REGISTRIES = Set.of();
    FeatureFlagSet requiredFeatures();
    default boolean isEnabled(FeatureFlagSet var1) {
        return this.requiredFeatures().isSubsetOf(var1);
    }
}
