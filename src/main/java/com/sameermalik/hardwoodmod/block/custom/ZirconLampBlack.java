package com.sameermalik.hardwoodmod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;

public class ZirconLampBlack extends Block {
    public static final BooleanProperty LIT = BooleanProperty.create("lit");

    public ZirconLampBlack(Properties p){
        super(p);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(LIT);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos blockPos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if(!level.isClientSide() && hand==InteractionHand.MAIN_HAND){
            // state.cycle cycles through the possible value that LIT can go through
            level.setBlock(blockPos, state.cycle(LIT), 3);
        }
        return super.use(state, level, blockPos, player, hand, hitResult);
    }
}
