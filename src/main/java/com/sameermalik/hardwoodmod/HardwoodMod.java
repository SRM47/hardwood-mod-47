package com.sameermalik.hardwoodmod;

import com.mojang.logging.LogUtils;
import com.sameermalik.hardwoodmod.block.ModBlocks;
import com.sameermalik.hardwoodmod.effect.ModEffects;
import com.sameermalik.hardwoodmod.event.loot.ModLootModifiers;
import com.sameermalik.hardwoodmod.item.ModCreativeModeTabs;
import com.sameermalik.hardwoodmod.item.ModItems;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(HardwoodMod.MOD_ID)
public class HardwoodMod {
    /* Original code;
    // Define mod id in a common place for everything to reference
    public static final String MODID = "examplemod";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
    // Create a Deferred Register to hold Blocks which will all be registered under the "examplemod" namespace
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    // Create a Deferred Register to hold Items which will all be registered under the "examplemod" namespace
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

    // Creates a new Block with the id "examplemod:example_block", combining the namespace and path
    public static final RegistryObject<Block> EXAMPLE_BLOCK = BLOCKS.register("example_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    // Creates a new BlockItem with the id "examplemod:example_block", combining the namespace and path
    public static final RegistryObject<Item> EXAMPLE_BLOCK_ITEM = ITEMS.register("example_block", () -> new BlockItem(EXAMPLE_BLOCK.get(), new Item.Properties()));
    */
    public static final String MOD_ID = "hardwoodmod";
    private static final Logger LOGGER = LogUtils.getLogger();
    public HardwoodMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        // global loot tables
        ModLootModifiers.register(modEventBus);
        // register custom effects
        ModEffects.register(modEventBus);

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);



        
        modEventBus.addListener(this::addCreative);

    }

    private void addCreative(CreativeModeTabEvent.BuildContents event) {
        // Add to ingredients tab
        if (event.getTab() == CreativeModeTabs.INGREDIENTS) {
            event.accept(ModItems.ZIRCON);
            event.accept(ModItems.RAW_ZIRCON);// Takes in an ItemLike, assumes block has registered item
            event.accept(ModItems.WITHERMELON);
            event.accept(ModItems.WITHERMELON_SEEDS);
            event.accept(ModItems.WITHERMELON_TEA);
            event.accept(ModItems.ALCOHOL_BOTTLE);
            event.accept(ModItems.THE_HARWOOD_SPECIAL);
        }



        // Add to ingredients tab
        // this is our custom tab which we created in the other class
        if (event.getTab() == ModCreativeModeTabs.CREATIVE_TAB_1) {
            // event.accept(ModItems.ZIRCON); // Takes in an ItemLike, assumes block has registered item
            // register the items
            for(RegistryObject r: ModItems.ITEMS.getEntries()){
                event.accept((Item) r.get());
            }
            // register the blocks
            for(RegistryObject r: ModBlocks.BLOCKS.getEntries()){
                event.accept((Block) r.get());
            }
        }

        // we aren't adding CREATIVE_TAB_2 because we've already initialized the object in that creative tab

        if (event.getTab() == CreativeModeTabs.BUILDING_BLOCKS){
            event.accept(ModBlocks.ZIRCON_BLOCK);
            event.accept(ModBlocks.ZIRCON_ORE);
            event.accept(ModBlocks.ZIRCON_LAMP);
        }
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        BrewingRecipeRegistry.addRecipe(Ingredient.of(Items.POTION), Ingredient.of(Items.WHEAT), new ItemStack(ModItems.ALCOHOL_BOTTLE.get()));
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.WITHERMELON_CROP.get(), RenderType.cutout());
        }
    }
}
