package com.stal111.forbidden_arcanus.common.item.enhancer;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.stal111.forbidden_arcanus.core.registry.FARegistries;
import com.stal111.forbidden_arcanus.util.backports.StringRepresentable;
import com.stal111.forbidden_arcanus.util.AdditionalCodecs;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.RegistryFileCodec;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.Map;

public record EnhancerDefinition(Item item, Map<EnhancerTarget, Component> description, List<EnhancerEffect> effects) {
    
    public static final Codec<EnhancerDefinition> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
            ForgeRegistries.ITEMS.getCodec().fieldOf("item").forGetter(definition -> {
                return definition.item;
            }),
            Codec.simpleMap(EnhancerTarget.CODEC, AdditionalCodecs.COMPONENT, StringRepresentable.keys(EnhancerTarget.values())).fieldOf("description").forGetter(definition -> {
                return definition.description;
            }),
            EnhancerEffect.DIRECT_CODEC.listOf().fieldOf("effects").forGetter(definition -> {
                return definition.effects;
            })
    ).apply(instance, EnhancerDefinition::new));
    
    public static final Codec<Holder<EnhancerDefinition>> REFERENCE_CODEC = RegistryFileCodec.create(FARegistries.ENHANCER_DEFINITION, CODEC);
    
    public static final Codec<EnhancerDefinition> NETWORK_CODEC = RecordCodecBuilder.create((instance) -> instance.group(
            ForgeRegistries.ITEMS.getCodec().fieldOf("item").forGetter(definition -> {
                return definition.item;
            }),
            Codec.simpleMap(EnhancerTarget.CODEC, AdditionalCodecs.COMPONENT, StringRepresentable.keys(EnhancerTarget.values())).fieldOf("description").forGetter(definition -> {
                return definition.description;
            })
    ).apply(instance, (item, description) -> {
        return new EnhancerDefinition(item, description, ImmutableList.of());
    }));
}
