package fr.spark.cif.blocks.industrial_furnace.control_panel;

import fr.spark.cif.init.CIF_entity_blocks_Register;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class ControlPanelBlock extends Block implements EntityBlock {

    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    public ControlPanelBlock(Properties properties) {
        super(properties);
    }

    private static final VoxelShape SHAPE_W = Block.box(12, 0, 0, 16, 16, 16);
    private static final VoxelShape SHAPE_E = Block.box(0, 0, 0, 4, 16, 16);
    private static final VoxelShape SHAPE_N = Block.box(0, 0, 12, 16, 16, 16);
    private static final VoxelShape SHAPE_S = Block.box(0, 0, 0, 16, 16, 4);

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new ControlPanelEntity(CIF_entity_blocks_Register.CONTROL_PANEL_ENTITY.get(), pos, state);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context){
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rotation){
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirror){
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder){
        builder.add(FACING);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context){
        switch (state.getValue(FACING)) {
            case NORTH: return SHAPE_N;
            case SOUTH: return SHAPE_S;
            case WEST:  return SHAPE_W;
            case EAST:  return SHAPE_E;
            default:    return SHAPE_N;
        }
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context){
        switch (state.getValue(FACING)) {
            case NORTH: return SHAPE_N;
            case SOUTH: return SHAPE_S;
            case WEST:  return SHAPE_W;
            case EAST:  return SHAPE_E;
            default:    return SHAPE_N;
        }
    }
}
