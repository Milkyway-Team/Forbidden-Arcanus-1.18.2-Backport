package com.stal111.forbidden_arcanus.data;

import com.google.gson.JsonElement;
import com.mojang.serialization.JsonOps;
import com.stal111.forbidden_arcanus.ForbiddenArcanus;
import com.stal111.forbidden_arcanus.data.client.ModBlockStateProvider;
import com.stal111.forbidden_arcanus.data.client.ModItemModelProvider;
import com.stal111.forbidden_arcanus.data.client.ModSoundsProvider;
import com.stal111.forbidden_arcanus.data.particle.ParticleDataProvider;
import com.stal111.forbidden_arcanus.data.recipes.ApplyModifierRecipeProvider;
import com.stal111.forbidden_arcanus.data.recipes.ClibanoRecipeProvider;
import com.stal111.forbidden_arcanus.data.server.ModRecipeProvider;
import com.stal111.forbidden_arcanus.data.server.loot.ModLootModifierProvider;
import com.stal111.forbidden_arcanus.data.server.loot.ModLootTableProvider;
import com.stal111.forbidden_arcanus.data.server.tags.*;
import com.stal111.forbidden_arcanus.data.worldgen.modifier.ModBiomeModifiers;
import net.minecraft.core.RegistryAccess;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.RegistryOps;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.JsonCodecProvider;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.valhelsia.valhelsia_core.core.data.DataProviderInfo;

/**
 * @author stal111
 * @since 2021-01-26
 */
@Mod.EventBusSubscriber(modid = ForbiddenArcanus.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        RegistryOps<JsonElement> ops = RegistryOps.create(JsonOps.INSTANCE, RegistryAccess.builtinCopy());

        DataProviderInfo info = DataProviderInfo.of(generator, existingFileHelper, ForbiddenArcanus.REGISTRY_MANAGER);

        // Client Providers
        generator.addProvider(event.includeClient(), new ModBlockStateProvider(generator, existingFileHelper));
        generator.addProvider(event.includeClient(), new ModItemModelProvider(generator, existingFileHelper));

        generator.addProvider(event.includeClient(), new ModSoundsProvider(generator, existingFileHelper));
        generator.addProvider(event.includeServer(), new ParticleDataProvider(info));

        // Server Providers
        ModBlockTagsProvider blockTagsProvider = new ModBlockTagsProvider(generator, existingFileHelper);
        generator.addProvider(event.includeServer(), blockTagsProvider);
        generator.addProvider(event.includeServer(), new ModItemTagsProvider(generator, blockTagsProvider, existingFileHelper));
        generator.addProvider(event.includeServer(), new ModEnchantmentTagsProvider(generator, existingFileHelper));
        generator.addProvider(event.includeServer(), new ModEntityTypeTagsProvider(generator, existingFileHelper));
        generator.addProvider(event.includeServer(), new ModBiomeTagsProvider(generator, existingFileHelper));

        generator.addProvider(event.includeServer(), new ModLootTableProvider(generator));

        generator.addProvider(event.includeServer(), new ModRecipeProvider(info));
        generator.addProvider(event.includeServer(), new ClibanoRecipeProvider(info));
        generator.addProvider(event.includeServer(), new ApplyModifierRecipeProvider(info));

        generator.addProvider(event.includeServer(), new ModLootModifierProvider(generator));

        generator.addProvider(event.includeServer(), JsonCodecProvider.forDatapackRegistry(
                generator, existingFileHelper, ForbiddenArcanus.MOD_ID, ops, ForgeRegistries.Keys.BIOME_MODIFIERS, new ModBiomeModifiers(info, ops).getModifiers()));
    }
}
