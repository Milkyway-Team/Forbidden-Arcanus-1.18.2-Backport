package com.stal111.forbidden_arcanus.util.backports.valhelsia_core;

import com.stal111.forbidden_arcanus.util.backports.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;
import net.minecraftforge.registries.RegistryManager;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public record DataProviderInfo(DataGenerator output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper fileHelper, RegistryManager registryManager) {
    
    public static DataProviderInfo of(GatherDataEvent event, RegistryManager registryManager) {
        return new DataProviderInfo(event.getGenerator(), (CompletableFuture<HolderLookup.Provider>) event.getGenerator().getOutputFolder(), event.getExistingFileHelper(), registryManager);
    }
    
    public ResourceLocation location(String name) {
        return new ResourceLocation(this.registryManager.getName(), name);
    }
}
