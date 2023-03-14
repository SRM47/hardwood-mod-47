package com.sameermalik.hardwoodmod.item;

import com.sameermalik.hardwoodmod.HardwoodMod;
import com.sameermalik.hardwoodmod.block.ModBlocks;
import com.sameermalik.hardwoodmod.item.custom.AlcoholBottleItem;
import com.sameermalik.hardwoodmod.item.custom.HarwoodSpecialBottleItem;
import com.sameermalik.hardwoodmod.item.custom.WithermelonTeaItem;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, HardwoodMod.MOD_ID);

    // add item
    public static final RegistryObject<Item> ZIRCON = ITEMS.register("zircon",
            () ->  new Item(new Item.Properties()));

    // Registered on the MOD event bus

    // add another item
    public static final RegistryObject<Item> RAW_ZIRCON = ITEMS.register("raw_zircon",
            () -> new Item(new Item.Properties()));


    public static final RegistryObject<Item> WITHERMELON_SEEDS = ITEMS.register("withermelon_seeds",
            () -> new ItemNameBlockItem(ModBlocks.WITHERMELON_CROP.get(),
                    new Item.Properties()));

    // the withermelon fruit, raw, it will have a 40% change of inflicting wither for 100 sconds
    public static final RegistryObject<Item> WITHERMELON = ITEMS.register("withermelon",
            () -> new Item(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .saturationMod(0.3f)
                            .nutrition(3)
                            .effect(() -> new MobEffectInstance(MobEffects.WITHER, 100), 0.47f)
                            .build())));

    // bottle item because it's a tea ina  bottle
    // defined in the withermelon tea class, drinking withermelon tea will allow you to remove the wither effect

    public static final RegistryObject<Item> WITHERMELON_TEA = ITEMS.register("withermelon_tea",
            () -> new WithermelonTeaItem(new Item.Properties()
                    .stacksTo(16)
                    .food(new FoodProperties.Builder()
                            .saturationMod(0.4f)
                            .nutrition(2)
                            .build())));

    // define alcohol item
    public static final RegistryObject<Item> ALCOHOL_BOTTLE = ITEMS.register("alcohol_bottle",
            () -> new AlcoholBottleItem(new Item.Properties()
                    .stacksTo(1)
                    .food(new FoodProperties.Builder()
                            .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, 500), 1f)
                            .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 500), 1f)
                            .build())));

    public static final RegistryObject<Item> THE_HARWOOD_SPECIAL = ITEMS.register("harwood_special",
            () -> new HarwoodSpecialBottleItem(new Item.Properties()
                    .stacksTo(1)));

    /* for a custom creative tab we call this function

    @SubscribeEvent
    public void buildContents(CreativeModeTabEvent.Register event) {
        event.registerCreativeModeTab(new ResourceLocation(HardwoodMod.MOD_ID, "example"), builder ->
                // Set name of tab to display
                builder.title(Component.translatable("item_group." + HardwoodMod.MOD_ID + ".example"))
                        // Set icon of creative tab
                        .icon(() -> new ItemStack(ZIRCON.get()))
                        // Add default items to tab
                        .displayItems((enabledFlags, populator, hasPermissions) -> {
                            populator.accept(ZIRCON.get());
                        })
        );
    }
    */


    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
