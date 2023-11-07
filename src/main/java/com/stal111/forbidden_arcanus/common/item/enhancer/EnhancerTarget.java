package com.stal111.forbidden_arcanus.common.item.enhancer;

import com.stal111.forbidden_arcanus.util.backports.Components;
import com.stal111.forbidden_arcanus.util.backports.StringRepresentable;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;

public enum EnhancerTarget implements StringRepresentable {
    HEPHAESTUS_FORGE("hephaestus_forge", "item.forbidden_arcanus.enhancer.hephaestus_forge_effect"),
    CLIBANO("clibano", "item.forbidden_arcanus.enhancer.clibano_effect");
    
    public static final StringRepresentable.EnumCodec<EnhancerTarget> CODEC = StringRepresentable.fromEnum(EnhancerTarget::values);
    
    private final String name;
    private final Component title;
    
    EnhancerTarget(String name, String title) {
        this.name = name;
        this.title = Components.translatable(title).withStyle(ChatFormatting.GRAY);
    }
    
    @Override
    public @NotNull String getSerializedName() {
        return this.name;
    }
    
    public Component getTitle() {
        return this.title;
    }
}
