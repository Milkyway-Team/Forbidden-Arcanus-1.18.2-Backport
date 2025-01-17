package com.stal111.forbidden_arcanus.util.backports;

import net.minecraft.network.chat.*;

public final class Components {
    public static Component immutableEmpty() {
        return TextComponent.EMPTY;
    }
    
    /** Use {@link #immutableEmpty()} when possible to prevent creating an extra object. */
    public static MutableComponent empty() {
        return TextComponent.EMPTY.copy();
    }
    
    public static MutableComponent literal(String str) {
        return new TextComponent(str);
    }
    
    public static MutableComponent translatable(String key) {
        return new TranslatableComponent(key);
    }
    
    public static MutableComponent translatable(String key, Object... args) {
        return new TranslatableComponent(key, args);
    }
    
    public static MutableComponent keybind(String name) {
        return new KeybindComponent(name);
    }
}
