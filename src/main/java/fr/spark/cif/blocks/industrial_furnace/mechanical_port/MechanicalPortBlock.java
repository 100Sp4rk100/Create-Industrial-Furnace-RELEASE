package fr.spark.cif.blocks.industrial_furnace.mechanical_port;

import com.simibubi.create.content.kinetics.base.DirectionalKineticBlock;
import fr.spark.cif.init.CIF_entity_blocks_Register;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import org.jetbrains.annotations.Nullable;

public class MechanicalPortBlock extends DirectionalKineticBlock implements EntityBlock {
    private static DirectionProperty rotationalFace = BlockStateProperties.FACING;
    public MechanicalPortBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(rotationalFace, Direction.UP));
    }

    @Override
    @Nullable
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new MechanicalPortEntity(CIF_entity_blocks_Register.MECHANICAL_PORT_ENTITY.get(), pos, state);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(rotationalFace, Direction.UP);
    }

    @Override
    public boolean hideStressImpact() {
        return false;
    }

    @Override
    public boolean hasShaftTowards(LevelReader world, BlockPos pos, BlockState state, Direction face) {
        return face == Direction.UP;
    }

    @Override
    public Direction.Axis getRotationAxis(BlockState state) {
        return state.getValue(rotationalFace).getAxis();
    }

    @Override
    public SpeedLevel getMinimumRequiredSpeedLevel() {
        return SpeedLevel.FAST;
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return type == CIF_entity_blocks_Register.MECHANICAL_PORT_ENTITY.get()
                ? (lvl, pos, blockState, blockEntity) -> {
            if (blockEntity instanceof MechanicalPortEntity mechanicalPortEntity) {
                mechanicalPortEntity.tick();
            }
        }
                : null;
    }

}
