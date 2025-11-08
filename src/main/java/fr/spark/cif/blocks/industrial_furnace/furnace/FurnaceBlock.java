package fr.spark.cif.blocks.industrial_furnace.furnace;

import fr.spark.cif.blocks.industrial_furnace.controller.ControllerEntity;
import fr.spark.cif.init.CIF_entity_blocks_Register;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static com.simibubi.create.content.kinetics.base.DirectionalKineticBlock.FACING;

public class FurnaceBlock extends Block implements EntityBlock {
    public FurnaceBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH));
    }

    @Override
    @Nullable
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new FurnaceEntity(CIF_entity_blocks_Register.FURNACE_ENTITY_BLOCK.get(), pos, state);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Direction direction = context.getHorizontalDirection().getOpposite();
        return this.defaultBlockState().setValue(FACING, direction);
    }

    public static ItemStack getAnyCookingResult(Level level, ItemStack input) {
        if (level == null || input == null || input.isEmpty()) return ItemStack.EMPTY;

        List<RecipeType<? extends AbstractCookingRecipe>> cookingTypes = ControllerEntity.COOKING_TYPES;

        SimpleContainer container = new SimpleContainer(input);

        for (RecipeType<? extends AbstractCookingRecipe> type : cookingTypes) {
            var recipeOpt = level.getRecipeManager().getRecipeFor(type, container, level);
            if (recipeOpt.isPresent()) {
                AbstractCookingRecipe recipe = recipeOpt.get();
                return recipe.getResultItem(level.registryAccess()).copy();
            }
        }

        return ItemStack.EMPTY;
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return type == CIF_entity_blocks_Register.FURNACE_ENTITY_BLOCK.get()
                ? (lvl, pos, blockState, blockEntity) -> {
            if (blockEntity instanceof FurnaceEntity furnaceEntity) {
                furnaceEntity.tick();
            }
        }
                : null;
    }
}
