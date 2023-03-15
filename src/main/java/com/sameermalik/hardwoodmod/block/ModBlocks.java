package com.sameermalik.hardwoodmod.block;

import com.sameermalik.hardwoodmod.HardwoodMod;
import com.sameermalik.hardwoodmod.block.custom.WithermelonCropBlock;
import com.sameermalik.hardwoodmod.block.custom.ZirconLampBlack;
import com.sameermalik.hardwoodmod.item.ModItems;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;


public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, HardwoodMod.MOD_ID);

    public static final RegistryObject<Block> ZIRCON_BLOCK = registerBlock("zircon_block",
            () -> new Block(BlockBehaviour.Properties
                    .of(Material.METAL)
                    .strength(6f)
                    .requiresCorrectToolForDrops()),
            new Item.Properties()
                    .stacksTo(64));

    public static final RegistryObject<Block> ZIRCON_LAMP = registerBlock("zircon_lamp",
            () -> new ZirconLampBlack(BlockBehaviour.Properties
                    .of(Material.STONE)
                    .strength(6f)
                    .lightLevel(state -> state.getValue(ZirconLampBlack.LIT) ? 15 : 0)),
            new Item.Properties()
                    .stacksTo(2));

    // there are different kinds of blocks
    public static final RegistryObject<Block> ZIRCON_ORE = registerBlock("zircon_ore",
            // this is a type of block that drops xp
            () -> new DropExperienceBlock(BlockBehaviour.Properties
                    .of(Material.METAL)
                    .strength(4f)
                    .requiresCorrectToolForDrops(), UniformInt.of(2,6)), // drops between 2-6 experiences
            new Item.Properties()
                    .stacksTo(64));

    public static final RegistryObject<Block> DEEPSLATE_ZIRCON_ORE = registerBlock("deepslate_zircon_ore",
            // this is a type of block that drops xp
            () -> new DropExperienceBlock(BlockBehaviour.Properties
                    .of(Material.METAL)
                    .strength(4f)
                    .requiresCorrectToolForDrops(), UniformInt.of(2,6)), // drops between 2-6 experiences
            new Item.Properties()
                    .stacksTo(64));

    // we dont want a separate item so we're only registering the block in the block deferred register
    // instead, we want to add the seeds and the drop as items in the Mod Items class
    public static final RegistryObject<Block> WITHERMELON_CROP = BLOCKS.register("withermelon_crop",
            // this is a type of block that drops xp
            () -> new WithermelonCropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT)));



    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> s, Item.Properties blockItemProperty){
        RegistryObject<T> new_block = BLOCKS.register(name, s);
        registerBlockItem(name, new_block, blockItemProperty);
        return new_block;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block, Item.Properties blockItemProperty){
        // register the block in the items
        return ModItems.ITEMS.register(name,
                () -> new BlockItem(block.get(), blockItemProperty));
    }

    public static void register(IEventBus event){
        BLOCKS.register(event);
    }
}
