package com.stal111.forbidden_arcanus.util.backports;

public interface HolderOwner<T> {
    default boolean canSerializeIn(HolderOwner<T> var1) {
        return var1 == this;
    }
}
