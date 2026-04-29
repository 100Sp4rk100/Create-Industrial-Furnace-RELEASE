package fr.spark.cif.blocks.portable_tank;

import com.simibubi.create.content.equipment.goggles.IHaveGoggleInformation;
import com.simibubi.create.foundation.blockEntity.SmartBlockEntity;
import com.simibubi.create.foundation.blockEntity.behaviour.BlockEntityBehaviour;
import com.simibubi.create.foundation.blockEntity.behaviour.fluid.SmartFluidTankBehaviour;
import com.simibubi.create.foundation.utility.Lang;
import fr.spark.cif.Cif;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.templates.FluidTank;

import java.util.List;

public abstract class AbstractPortableTankEntity extends SmartBlockEntity implements IHaveGoggleInformation {
    SmartFluidTankBehaviour tank;
    private Integer capacity;

    public AbstractPortableTankEntity(BlockEntityType<?> typeIn, BlockPos pos, BlockState state) {
        super(typeIn, pos, state);
        this.capacity = getCapacity();
    }

    protected abstract int getCapacity();

    @Override
    public void addBehaviours(List<BlockEntityBehaviour> behaviours) {
        tank = SmartFluidTankBehaviour.single(this, getCapacity());
        behaviours.add(tank);
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
        if (cap == ForgeCapabilities.FLUID_HANDLER)
            return tank.getCapability()
                    .cast();
        return super.getCapability(cap, side);
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    protected void write(CompoundTag compound, boolean clientPacket) {
        super.write(compound, clientPacket);
        compound.put("Fluid", tank.getPrimaryHandler().getFluidInTank(0).writeToNBT(new CompoundTag()));
    }

    @Override
    protected void read(CompoundTag compound, boolean clientPacket) {
        super.read(compound, clientPacket);
        if (compound.get("Fluid") instanceof CompoundTag compoundtag){
            FluidStack stack = compound.get("Fluid") instanceof CompoundTag ? FluidStack.loadFluidStackFromNBT(compoundtag) : null;
            if (stack != null && !stack.isEmpty()){
                tank.getPrimaryHandler().setFluid(stack);
            }
        }
    }

    @Override
    public boolean addToGoggleTooltip(List<Component> tooltip, boolean isPlayerSneaking) {
        Lang.builder(Cif.MODID).translate("gui.goggles.portable_tank.name").style(ChatFormatting.AQUA).forGoggles(tooltip);
        Lang.fluidName(tank.getPrimaryHandler().getFluidInTank(0)).forGoggles(tooltip);
        
        ChatFormatting color;
        if (tank.getPrimaryHandler().getSpace() != 0){
            if (tank.getPrimaryHandler().getSpace() >= getCapacity()/2){
                color = ChatFormatting.GREEN;
            }else {
                color = ChatFormatting.YELLOW;
            }
        }else {
            color =  ChatFormatting.RED;
        }

        Lang.text(tank.getPrimaryHandler().getFluidInTank(0).getAmount() + "/ " + getCapacity() + "mb").style(color).forGoggles(tooltip);
        return isPlayerSneaking;
    }

    public void setTank(CompoundTag compound){
        if (compound == null) return;
        FluidStack stack = FluidStack.loadFluidStackFromNBT(compound);
        if (stack !=null)tank.getPrimaryHandler().setFluid(stack);
    }

    public FluidTank getTank(){
        return tank.getPrimaryHandler();
    }
}
