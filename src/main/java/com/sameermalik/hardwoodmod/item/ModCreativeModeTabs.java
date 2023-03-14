package com.sameermalik.hardwoodmod.item;

import com.sameermalik.hardwoodmod.HardwoodMod;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid= HardwoodMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModCreativeModeTabs {
    // creates a tab and the function below registers
    public static CreativeModeTab CREATIVE_TAB_1;
    public static CreativeModeTab CREATIVE_TAB_2;

    @SubscribeEvent
    public static void registerCreativeModeTabs(CreativeModeTabEvent.Register event) {
        CREATIVE_TAB_1 = event.registerCreativeModeTab(new ResourceLocation(HardwoodMod.MOD_ID, "tab1"), builder ->
                        // Set name of tab to display
                        builder.title(Component.translatable("creativemodetab.tutorial"))
                                // Set icon of creative tab
                                .icon(() -> new ItemStack(ModItems.ZIRCON.get()))
                // Add default items to tab
                        /* these functins return this so its stackable
                        .displayItems((enabledFlags, populator, hasPermissions) -> {
                            populator.accept(ModItems.ZIRCON.get());
                            populator.accept(ModItems.RAW_ZIRCON.get());
                        })
                        */

        );


        CREATIVE_TAB_2 = event.registerCreativeModeTab(new ResourceLocation(HardwoodMod.MOD_ID, "tab2"), builder ->
                // Set name of tab to display
                builder.title(Component.translatable("creativemodetab.tutorial2"))
                        // Set icon of creative tab
                        .icon(() -> new ItemStack(ModItems.RAW_ZIRCON.get()))
                        // add items manually as well just from the creation of the tab
                        .displayItems((enabledFlags, populator, hasPermissions) -> {
                            populator.accept(ModItems.RAW_ZIRCON.get());
                        })

        );

    }



}
