package com.sameermalik.hardwoodmod.effect;

import com.sameermalik.hardwoodmod.HardwoodMod;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryObject;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, HardwoodMod.MOD_ID);

    public static final RegistryObject<MobEffect> HARDWOOD_SPECIAL_EFFECT_TIER_1 = MOB_EFFECTS.register("harwood_special_effect_tier_1",
            () -> new HarwoodSpecialEffect(MobEffectCategory.NEUTRAL, 3124687, 1));

    public static final RegistryObject<MobEffect> HARDWOOD_SPECIAL_EFFECT_TIER_2 = MOB_EFFECTS.register("harwood_special_effect_tier_2",
            () -> new HarwoodSpecialEffect(MobEffectCategory.NEUTRAL, 3125687, 2));

    public static final RegistryObject<MobEffect> HARDWOOD_SPECIAL_EFFECT_TIER_3 = MOB_EFFECTS.register("harwood_special_effect_tier_3",
            () -> new HarwoodSpecialEffect(MobEffectCategory.NEUTRAL, 3126687, 3));

    public static void register(IEventBus b){
        MOB_EFFECTS.register(b);
    }



}
