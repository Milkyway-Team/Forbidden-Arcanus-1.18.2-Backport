package com.stal111.forbidden_arcanus.util.backports;

public class FeatureFlag {
    final FeatureFlagUniverse universe;
    final long mask;
    FeatureFlag(FeatureFlagUniverse var1, int var2) {
        this.universe = var1;
        this.mask = 1L << var2;
    }
}
