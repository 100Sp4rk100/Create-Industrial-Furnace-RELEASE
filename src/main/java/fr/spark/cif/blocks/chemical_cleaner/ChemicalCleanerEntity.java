package fr.spark.cif.blocks.chemical_cleaner;

import com.simibubi.create.api.equipment.goggles.IHaveGoggleInformation;
import com.simibubi.create.foundation.blockEntity.SmartBlockEntity;
import com.simibubi.create.foundation.blockEntity.behaviour.BehaviourType;
import com.simibubi.create.foundation.blockEntity.behaviour.BlockEntityBehaviour;
import com.simibubi.create.foundation.blockEntity.behaviour.fluid.SmartFluidTankBehaviour;
import com.simibubi.create.foundation.utility.CreateLang;
import fr.spark.cif.init.CIF_fluids_Register;
import fr.spark.cif.Cif;
import fr.spark.cif.init.CIF_items_Register;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ChemicalCleanerEntity extends SmartBlockEntity implements IHaveGoggleInformation {
    SmartFluidTankBehaviour inputTank;
    SmartFluidTankBehaviour outputTank;

    public static final Integer capacity = 16000;
    public static final DirectionProperty FACING = BlockStateProperties.FACING;

    public static final Item fuel = CIF_items_Register.ACTIVATED_CARBON.get();
    public static final int fuel_consumer = 3;

    private int tick;

    private ItemStack current;

    protected final ItemStackHandler itemHandler = new ItemStackHandler(1){
        @Override
        protected void onContentsChanged(int slot) {
            super.onContentsChanged(slot);
            setChanged();
            updateCurrent();
            sendData();
        }

        @Override
        public boolean isItemValid(int slot, @NotNull ItemStack stack) {
            return stack.getItem().equals(fuel);
        }

        @Override
        public @NotNull ItemStack extractItem(int slot, int amount, boolean simulate) {
            return super.extractItem(slot, amount, simulate);
        }
    };

    protected LazyOptional<IItemHandler> handlerCap = LazyOptional.of(() -> itemHandler);

    public ChemicalCleanerEntity(BlockEntityType<?> typeIn, BlockPos pos, BlockState state) {
        super(typeIn, pos, state);

        this.tick = 0;
        this.current = ItemStack.EMPTY;
    }

    @Override
    public void addBehaviours(List<BlockEntityBehaviour> behaviours) {
        inputTank = new SmartFluidTankBehaviour(new BehaviourType<>("input"),  this, 1, capacity, false);
        outputTank = new SmartFluidTankBehaviour(new BehaviourType<>("output"),  this, 1, capacity, false);

        behaviours.add(inputTank);
        behaviours.add(outputTank);
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {

        if (cap == ForgeCapabilities.FLUID_HANDLER && side.equals(getBlockState().getValue(FACING)))
            return inputTank.getCapability()
                    .cast();
        else if (cap == ForgeCapabilities.FLUID_HANDLER && side.equals(getBlockState().getValue(FACING).getOpposite())) {
            return outputTank.getCapability()
                    .cast();
        }else if (cap == ForgeCapabilities.ITEM_HANDLER) {
            return handlerCap.cast();
        }

        return super.getCapability(cap, side);
    }

    @Override
    public void tick() {
        super.tick();

        if (level != null && !level.isClientSide) {
            updateCurrent();
        }

        if (tick >= 20) {

            if (inputTank.getPrimaryHandler().getFluidInTank(0).isEmpty() || itemHandler.getStackInSlot(0).isEmpty()) return;

            if (inputTank.getPrimaryHandler().getFluidInTank(0).getFluid().getFluidType().equals(CIF_fluids_Register.HOT_WATER.getType())){

                int default_amount = inputTank.getPrimaryHandler().getFluidInTank(0).getAmount();
                int amount = (int) (default_amount  * 0.7);

                if (amount == 0) return;

                if (amount > 700){
                    amount = 700;
                    default_amount = 1000;
                }

                if (outputTank.getPrimaryHandler().getSpace() + amount >= 0) {
                    FluidStack fluidStack = new FluidStack(Fluids.WATER, amount);
                    outputTank.getPrimaryHandler().fill(fluidStack, IFluidHandler.FluidAction.EXECUTE);
                    inputTank.getPrimaryHandler().drain(default_amount, IFluidHandler.FluidAction.EXECUTE);

                    itemHandler.getStackInSlot(0).shrink(fuel_consumer);

                    tick = 0;
                }

            }

        }else if (outputTank.getPrimaryHandler().getSpace() > 0
                && itemHandler.getStackInSlot(0).getItem().equals(fuel)
                && itemHandler.getStackInSlot(0).getCount() >= fuel_consumer){

            tick ++;
        }
    }

    @Override
    protected void write(CompoundTag compound, boolean clientPacket) {
        super.write(compound, clientPacket);

        compound.putInt("tick", tick);

        CompoundTag currentTag = new CompoundTag();
        if (!current.isEmpty()) {
            current.save(currentTag);
            compound.put("current", currentTag);
        }

    }

    @Override
    protected void read(CompoundTag compound, boolean clientPacket) {
        super.read(compound, clientPacket);

        tick = compound.getInt("tick");

        if (compound.contains("current")) {
            current = ItemStack.of(compound.getCompound("current"));
        } else {
            current = ItemStack.EMPTY;
        }

    }

    @Override
    public boolean addToGoggleTooltip(List<Component> tooltip, boolean isPlayerSneaking) {

        CreateLang.itemName(current).style(ChatFormatting.GOLD)
                .add(CreateLang.text(" x" + current.getCount()).style(ChatFormatting.YELLOW))
                .forGoggles(tooltip);

        CreateLang.builder(Cif.MODID).translate("gui.goggles.chemical_cleaner.input").style(ChatFormatting.GREEN).forGoggles(tooltip);
        CreateLang.fluidName(inputTank.getPrimaryHandler().getFluidInTank(0)).forGoggles(tooltip);
        CreateLang.text(inputTank.getPrimaryHandler().getFluidInTank(0).getAmount() + "/ " + capacity + "mb").style(ChatFormatting.AQUA).forGoggles(tooltip);

        CreateLang.builder(Cif.MODID).translate("gui.goggles.chemical_cleaner.output").style(ChatFormatting.RED).forGoggles(tooltip);
        CreateLang.fluidName(outputTank.getPrimaryHandler().getFluidInTank(0)).forGoggles(tooltip);
        CreateLang.text(outputTank.getPrimaryHandler().getFluidInTank(0).getAmount() + "/ " + capacity + "mb").style(ChatFormatting.AQUA).forGoggles(tooltip);

        return isPlayerSneaking;
    }

    public ItemStackHandler getItemHandler(){
        return itemHandler;
    }

    private void updateCurrent(){
        current = itemHandler.getStackInSlot(0).copy();
        if (!level.isClientSide) {
            sendData();
        }
    }

}
