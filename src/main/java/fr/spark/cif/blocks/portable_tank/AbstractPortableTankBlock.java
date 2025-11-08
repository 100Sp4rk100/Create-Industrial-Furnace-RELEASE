package fr.spark.cif.blocks.portable_tank;

import com.tterrag.registrate.util.entry.BlockEntityEntry;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractPortableTankBlock extends Block implements EntityBlock {
    public AbstractPortableTankBlock(Properties properties) {
        super(properties);
    }

    @Nullable
    protected abstract BlockEntityEntry<? extends AbstractPortableTankEntity> getEntry();


    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return getEntry().create(pos, state);
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return type == getEntry().get()
                ? (lvl, pos, blockState, blockEntity) -> {
            if (blockEntity instanceof AbstractPortableTankEntity portableTankEntity) {
                portableTankEntity.tick();
            }
        }
                : null;
    }

    @Override
    public void setPlacedBy(Level worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity entity, ItemStack stack) {
        super.setPlacedBy(worldIn, pos, state, entity, stack);

        if (worldIn.isClientSide)
            return;
        if (stack.isEmpty())
            return;

        if (worldIn.getBlockEntity(pos) instanceof AbstractPortableTankEntity portableTankEntity) {

            portableTankEntity.setTank(stack.getTagElement("Fluid"));
        }
    }

    @Override
    public ItemStack getCloneItemStack(BlockGetter blockGetter, BlockPos pos, BlockState state) {
        Item item = asItem();

        ItemStack stack = new ItemStack(item);
        if (blockGetter.getBlockEntity(pos) instanceof AbstractPortableTankEntity portableTankEntity) {
            CompoundTag tag = stack.getOrCreateTag();
            FluidTank tank = portableTankEntity.tank.getPrimaryHandler();
            if(tank!= null){
                CompoundTag fluidTank = new CompoundTag();
                tag.put("Fluid", tank.writeToNBT(fluidTank));
            }

            stack.setTag(tag);
        }

        return stack;
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult ray) {

        if (!player.isCreative()) {
            return InteractionResult.PASS;
        }

        ItemStack stack = player.getItemInHand(hand);

        if (stack.getCapability(ForgeCapabilities.FLUID_HANDLER_ITEM).isPresent()) {

            IFluidHandlerItem fluidHandler = stack.getCapability(ForgeCapabilities.FLUID_HANDLER_ITEM).orElse(null);
            FluidStack fluid = fluidHandler.getFluidInTank(0);

            if (!fluid.isEmpty() && level.getBlockEntity(pos) instanceof AbstractPortableTankEntity tank) {
                player.playSound(SoundEvents.BUCKET_EMPTY, 1.0F, 1.0F);
                if (!level.isClientSide) {
                    tank.getTank().fill(fluid, IFluidHandler.FluidAction.EXECUTE);
                }
                return InteractionResult.sidedSuccess(level.isClientSide);
            }
        }

        return super.use(state, level, pos, player, hand, ray);
    }

}
