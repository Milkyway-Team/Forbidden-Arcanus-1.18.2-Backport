package com.stal111.forbidden_arcanus.core.init;

import com.stal111.forbidden_arcanus.ForbiddenArcanus;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.registries.RegistryObject;

public class ModSounds {
	
	public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, ForbiddenArcanus.MOD_ID);
	
	//dark_bolt_launch
	public static final RegistryObject<SoundEvent> DARK_BOLT_LAUNCH = SOUNDS.register("dark_bolt_launch",
			() -> new SoundEvent(new ResourceLocation(ForbiddenArcanus.MOD_ID, "dark_bolt_launch")));
	
	//dark_bolt_hit
	public static final RegistryObject<SoundEvent> DARK_BOLT_HIT = SOUNDS.register("dark_bolt_hit",
			() -> new SoundEvent(new ResourceLocation(ForbiddenArcanus.MOD_ID, "dark_bolt_hit")));
	
	//pixi_activated
	public static final RegistryObject<SoundEvent> PIXIE_ACTIVATED = SOUNDS.register("pixi_activated",
			() -> new SoundEvent(new ResourceLocation(ForbiddenArcanus.MOD_ID, "pixi_activated")));
	
	//runic_tenebris_core_activated
	public static final RegistryObject<SoundEvent> RUNIC_TENEBRIS_CORE_ACTIVATED = SOUNDS.register("runic_tenebris_core_activated",
			() -> new SoundEvent(new ResourceLocation(ForbiddenArcanus.MOD_ID, "runic_tenebris_core_activated")));
	
	//runic_tenebris_core_ambient
	public static final RegistryObject<SoundEvent> RUNIC_TENEBRIS_CORE_AMBIENT = SOUNDS.register("runic_tenebris_core_ambient",
			() -> new SoundEvent(new ResourceLocation(ForbiddenArcanus.MOD_ID, "runic_tenebris_core_ambient")));
	
	//clibano_fire_crackle
	public static final RegistryObject<SoundEvent> CLIBANO_FIRE_CRACKLE = SOUNDS.register("clibano_fire_crackle",
			() -> new SoundEvent(new ResourceLocation(ForbiddenArcanus.MOD_ID, "clibano_fire_crackle")));
	
	//clibano_soul_fire_crackle
	public static final RegistryObject<SoundEvent> CLIBANO_SOUL_FIRE_CRACKLE = SOUNDS.register("clibano_soul_fire_crackle",
			() -> new SoundEvent(new ResourceLocation(ForbiddenArcanus.MOD_ID, "clibano_soul_fire_crackle")));
	
	//blacksmith_gavel_ritual_start
	public static final RegistryObject<SoundEvent> BLACKSMITH_GAVEL_RITUAL_START = SOUNDS.register("blacksmith_gavel_ritual_start",
			() -> new SoundEvent(new ResourceLocation(ForbiddenArcanus.MOD_ID, "blacksmith_gavel_ritual_start")));
}
