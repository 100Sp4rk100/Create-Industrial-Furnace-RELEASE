package fr.spark.cif.blocks.chemical_cleaner;

import fr.spark.cif.init.CIF_entity_blocks_Register;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import org.jetbrains.annotations.Nullable;

import static com.simibubi.create.content.kinetics.base.DirectionalKineticBlock.FACING;

public class ChemicalCleanerBlock extends Block implements EntityBlock {
    public ChemicalCleanerBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH));
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return CIF_entity_blocks_Register.CHEMICAL_CLEANER_ENTITY.create(pos, state);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Direction direction = context.getHorizontalDirection();
        return this.defaultBlockState().setValue(FACING, direction);
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return type == CIF_entity_blocks_Register.CHEMICAL_CLEANER_ENTITY.get()
                ? (lvl, pos, blockState, blockEntity) -> {
            if (blockEntity instanceof ChemicalCleanerEntity chemicalCleanerEntity) {
                chemicalCleanerEntity.tick();
            }
        }
                : null;
    }

    @Override
    public void playerDestroy(Level level, Player player, BlockPos pos, BlockState state, @javax.annotation.Nullable BlockEntity be, ItemStack stack) {
        double x = pos.getX() + 0.5;
        double y = pos.getY() + 1.0;
        double z = pos.getZ() + 0.5;
        if (level != null && !level.isClientSide && be != null && be instanceof ChemicalCleanerEntity chemicalCleanerEntity) {
            Entity entity = new ItemEntity(level, x, y, z, chemicalCleanerEntity.getItemHandler().getStackInSlot(0));
            level.addFreshEntity(entity);
        }

        super.playerDestroy(level, player, pos, state, be, stack);
    }
}
