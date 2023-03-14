package com.sameermalik.hardwoodmod.event;

import com.sameermalik.hardwoodmod.HardwoodMod;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = HardwoodMod.MOD_ID)
public class ModEvents {

    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent e){

        /*
        // If a play hits a cow, they get the Wither effect for 100 ticks
        if(e.getEntity() instanceof Cow){
            if(e.getSource().getEntity() instanceof Player player){
                player.sendSystemMessage(Component.literal("Oh my goodness! WITHER"));
                if(!player.hasEffect(MobEffects.WITHER)){
                    player.sendSystemMessage(Component.literal("You don't have wither so I give it!"));
                    player.addEffect(new MobEffectInstance(MobEffects.WITHER, 100), player);

                }

            }
        }
        */

    }

}
