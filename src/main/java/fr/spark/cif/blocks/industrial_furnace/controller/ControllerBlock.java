package fr.spark.cif.blocks.industrial_furnace.controller;

import fr.spark.cif.init.CIF_entity_blocks_Register;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class ControllerBlock extends Block implements EntityBlock {
    public ControllerBlock(Properties properties) {
        super(properties);
    }

    @Override
    @Nullable
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new ControllerEntity(CIF_entity_blocks_Register.CONTROLLER_ENTITY_BLOCK.get(), pos, state);
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return type == CIF_entity_blocks_Register.CONTROLLER_ENTITY_BLOCK.get()
                ? (lvl, pos, blockState, blockEntity) -> {
            if (blockEntity instanceof ControllerEntity controllerEntity) {
                controllerEntity.tick();
            }
        }
                : null;
    }
}
