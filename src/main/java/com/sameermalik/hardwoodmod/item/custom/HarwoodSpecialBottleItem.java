package com.sameermalik.hardwoodmod.item.custom;

import com.sameermalik.hardwoodmod.effect.ModEffects;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.HoneyBottleItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class HarwoodSpecialBottleItem extends HoneyBottleItem {
    public HarwoodSpecialBottleItem(Properties properties) {
        super(properties);
    }

    private static final int DRINK_DURATION = 10;

    @Override
    public int getUseDuration(ItemStack itemStack) {
        return this.DRINK_DURATION;
    }

    public ItemStack finishUsingItem(ItemStack itemStack, Level level, LivingEntity livingEntity) {
        super.finishUsingItem(itemStack, level, livingEntity);
        if (livingEntity instanceof ServerPlayer serverplayer) {
            CriteriaTriggers.CONSUME_ITEM.trigger(serverplayer, itemStack);
            serverplayer.awardStat(Stats.ITEM_USED.get(this));
        }

        if (!level.isClientSide) {
            // remove all of the non-benefitial effects existing on the player
            for (MobEffectInstance me: livingEntity.getActiveEffects()){
                if(!me.getEffect().isBeneficial()){
                    livingEntity.removeEffect(me.getEffect());
                }
            }
            // apply all the necessary effects
            if(livingEntity.hasEffect(ModEffects.HARDWOOD_SPECIAL_EFFECT_TIER_1.get())){
                livingEntity.sendSystemMessage(Component.literal("Has Tier 1, giving tier 2"));
                livingEntity.removeEffect(ModEffects.HARDWOOD_SPECIAL_EFFECT_TIER_1.get());
                livingEntity.addEffect(new MobEffectInstance(ModEffects.HARDWOOD_SPECIAL_EFFECT_TIER_2.get(), 1000));
            }
            else if(livingEntity.hasEffect(ModEffects.HARDWOOD_SPECIAL_EFFECT_TIER_2.get())){
                livingEntity.sendSystemMessage(Component.literal("Has Tier 2, giving tier 3"));
                livingEntity.removeEffect(ModEffects.HARDWOOD_SPECIAL_EFFECT_TIER_2.get());
                livingEntity.addEffect(new MobEffectInstance(ModEffects.HARDWOOD_SPECIAL_EFFECT_TIER_3.get(), 1000));

            } else if(livingEntity.hasEffect(ModEffects.HARDWOOD_SPECIAL_EFFECT_TIER_3.get())){
                livingEntity.sendSystemMessage(Component.literal("Has Tier 3, death"));
                // kill player because of too many
                livingEntity.kill();
            } else{
                livingEntity.sendSystemMessage(Component.literal("Has Tier none, giving tier 1"));
                livingEntity.addEffect(new MobEffectInstance(ModEffects.HARDWOOD_SPECIAL_EFFECT_TIER_1.get(), 1000));
            }

        }

        if (itemStack.isEmpty()) {
            return new ItemStack(Items.GLASS_BOTTLE);
        } else {
            if (livingEntity instanceof Player && !((Player)livingEntity).getAbilities().instabuild) {
                ItemStack itemstack = new ItemStack(Items.GLASS_BOTTLE);
                Player player = (Player)livingEntity;
                if (!player.getInventory().add(itemstack)) {
                    player.drop(itemstack, false);
                }
            }

            return itemStack;
        }
    }
}
