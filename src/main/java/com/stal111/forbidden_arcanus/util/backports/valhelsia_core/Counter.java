package com.stal111.forbidden_arcanus.util.backports.valhelsia_core;

public interface Counter<T extends Number> {
    T getValue();
    void setValue(T value);
}
