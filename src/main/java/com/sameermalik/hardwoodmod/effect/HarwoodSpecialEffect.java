package com.sameermalik.hardwoodmod.effect;

import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;

public class HarwoodSpecialEffect extends MobEffect {
    private final int tier;

    protected HarwoodSpecialEffect(MobEffectCategory mobEffectCategory, int i, int tier) {
        // this is a beneficial
        super(mobEffectCategory, i);
        this.tier = tier;
        init_mob_effect(this.tier);
    }

    public void init_mob_effect(int tier) {
        // apply the lasting effects depending on the tier
        if (tier == 1) {
            //speed1
            addAttributeModifier(
                    Attributes.MOVEMENT_SPEED,
                    "91AEAA56-376B-4498-935B-2F7F68070635",
                    0.2F,
                    AttributeModifier.Operation.MULTIPLY_TOTAL
            );
            //haste1
            addAttributeModifier(
                    Attributes.ATTACK_SPEED,
                    "AF8B6E3F-3328-4C0A-AA36-5BA2BB9DBEF3",
                    (double) 0.1F,
                    AttributeModifier.Operation.MULTIPLY_TOTAL
            );
        } else if (tier == 2) {
            //speed1
            addAttributeModifier(
                    Attributes.MOVEMENT_SPEED,
                    "91AEAA56-376B-4498-935B-2F7F68070635",
                    0.2F,
                    AttributeModifier.Operation.MULTIPLY_TOTAL
            );
            //haste2
            addAttributeModifier(
                    Attributes.ATTACK_SPEED,
                    "AF8B6E3F-3328-4C0A-AA36-5BA2BB9DBEF3",
                    (double) 0.2F,
                    AttributeModifier.Operation.MULTIPLY_TOTAL
            );
            //strength1
            addAttributeModifier(
                    Attributes.ATTACK_DAMAGE,
                    "648D7064-6A60-4F59-8ABE-C2C23A6DD7A9",
                    3.0D,
                    AttributeModifier.Operation.ADDITION
            );
            //extra hearts1
            addAttributeModifier(
                    Attributes.MAX_HEALTH,
                    "5D6F0BA2-1186-46AC-B896-C61C5CEE99CC",
                    4.0D,
                    AttributeModifier.Operation.ADDITION
            );
        } else if (tier >= 3) {
            //speed2
            //haste2
            //strength2
            //extra hearts2
            //damage resistance (absorption)
            //slow falling
            //speed2
            addAttributeModifier(
                    Attributes.MOVEMENT_SPEED,
                    "91AEAA56-376B-4498-935B-2F7F68070635",
                    0.4F,
                    AttributeModifier.Operation.MULTIPLY_TOTAL
            );
            //haste2
            addAttributeModifier(
                    Attributes.ATTACK_SPEED,
                    "AF8B6E3F-3328-4C0A-AA36-5BA2BB9DBEF3",
                    (double) 0.2F,
                    AttributeModifier.Operation.MULTIPLY_TOTAL
            );
            //strength2
            addAttributeModifier(
                    Attributes.ATTACK_DAMAGE,
                    "648D7064-6A60-4F59-8ABE-C2C23A6DD7A9",
                    5.0D,
                    AttributeModifier.Operation.ADDITION
            );
            //extra hearts2
            addAttributeModifier(
                    Attributes.MAX_HEALTH,
                    "5D6F0BA2-1186-46AC-B896-C61C5CEE99CC",
                    8.0D,
                    AttributeModifier.Operation.ADDITION
            );
            // absorption dealt with in addAttributeModifiers function below
        }

    }

    @Override
    public void applyEffectTick(LivingEntity livingEntity, int i) {

        // livingEntity.sendSystemMessage(Component.literal(livingEntity.getHealth() + " out of " + livingEntity.getMaxHealth()));
        if (livingEntity.getHealth() < livingEntity.getMaxHealth()) {
            livingEntity.sendSystemMessage(Component.literal("Tick!"));
            if      (tier == 1) {livingEntity.heal(1.0F); }
            else if (tier == 2) {livingEntity.heal(2.0F); }
            else if (tier >= 3) {livingEntity.heal(3.0F); }
        }
        super.applyEffectTick(livingEntity, i);
    }

    @Override
    public boolean isDurationEffectTick(int i1, int i2) {
        int k = 50 >> i2;
        if (k > 0) {
            return i1 % k == 0;
        } else {
            return true;
        }
    }

    public void applyInstantaneousEffects(LivingEntity livingEntity, int i){
        // I will put all of the instatnateous effects hara because this method is only called once
        if (tier == 2) {
            livingEntity.setHealth(2.0F * (int) (livingEntity.getHealth() / 3));
        } else if (tier >= 3){
            livingEntity.setHealth(livingEntity.getHealth()-18); // substract 9 hearts
            // only do this if tier is 3
            // copied from AbsorptionMobEffect Class
            livingEntity.setAbsorptionAmount(livingEntity.getAbsorptionAmount() + (float)(4 * (i + 1)));
        }
    }

    public void addAttributeModifiers(LivingEntity livingEntity, AttributeMap attributeMap, int i) {

        applyInstantaneousEffects(livingEntity, i);
        super.addAttributeModifiers(livingEntity, attributeMap, i);
    }
    public void removeAttributeModifiers(LivingEntity livingEntity, AttributeMap attributeMap, int i) {
        // for absorption (copied from the AbsorptionMobEffect)
        if (tier >= 3) {
            livingEntity.setAbsorptionAmount(livingEntity.getAbsorptionAmount() - (float) (4 * (i + 1)));
        }
        super.removeAttributeModifiers(livingEntity, attributeMap, i);
        if (livingEntity.getHealth() > livingEntity.getMaxHealth()) {
            livingEntity.setHealth(livingEntity.getMaxHealth());
        }

    }

}
