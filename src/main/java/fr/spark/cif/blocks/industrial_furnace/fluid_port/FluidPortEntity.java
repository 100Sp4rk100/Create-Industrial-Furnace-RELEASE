package fr.spark.cif.blocks.industrial_furnace.fluid_port;

import com.simibubi.create.api.equipment.goggles.IHaveGoggleInformation;
import com.simibubi.create.foundation.blockEntity.SmartBlockEntity;
import com.simibubi.create.foundation.blockEntity.behaviour.BlockEntityBehaviour;
import com.simibubi.create.foundation.blockEntity.behaviour.fluid.SmartFluidTankBehaviour;
import com.simibubi.create.foundation.utility.CreateLang;
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

import java.util.List;

import static com.simibubi.create.content.kinetics.base.DirectionalKineticBlock.FACING;

public class FluidPortEntity extends SmartBlockEntity implements IHaveGoggleInformation {

    SmartFluidTankBehaviour tank;
    public String block_Type;

    public static final String FUEL_TYPE = "Fuel";
    public static final String WATER_TYPE = "Water";
    public static final String OUT_TYPE = "Output";
    public String CURRENT_FLUID_NAME;
    public static final Integer capacity = 4000;
    private Component fluidContent;

    public FluidPortEntity(BlockEntityType<?> typeIn, BlockPos pos, BlockState state) {
        super(typeIn, pos, state);

        this.block_Type = FUEL_TYPE;
        this.fluidContent = CreateLang.builder(Cif.MODID).translate("gui.goggles.fluid_port.NoContent").style(ChatFormatting.AQUA).component();
        this.CURRENT_FLUID_NAME = "Air";
        this.autoriseFluid();
        this.updateInternal();
    }

    @Override
    public void addBehaviours(List<BlockEntityBehaviour> behaviours) {
        tank = SmartFluidTankBehaviour.single(this, capacity);
        behaviours.add(tank);
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
        if (cap == ForgeCapabilities.FLUID_HANDLER && side.equals(getBlockState().getValue(FACING)))
            return tank.getCapability()
                    .cast();
        return super.getCapability(cap, side);
    }

    @Override
    public void tick() {
        super.tick();
        autoriseFluid();
        updateInternal();
        CURRENT_FLUID_NAME = CreateLang.fluidName(tank.getPrimaryHandler().getFluidInTank(0)).string();
    }

    @Override
    protected void write(CompoundTag compound, boolean clientPacket) {
        super.write(compound, clientPacket);
        compound.putString("block_Type", block_Type);
        compound.putString("current_fluid", CURRENT_FLUID_NAME);
    }

    @Override
    protected void read(CompoundTag compound, boolean clientPacket) {
        super.read(compound, clientPacket);
        block_Type = compound.getString("block_Type");
        CURRENT_FLUID_NAME = compound.getString("current_fluid");
    }

    public SmartFluidTankBehaviour getTank(){
        return tank;
    }

    public void onRedstoneSignal(boolean bool){
        System.out.println("Redstone signal: " + bool);
    }

    private String getBlock_Type() {
        if (block_Type.equals(FUEL_TYPE)) return CreateLang.builder(Cif.MODID).translate("gui.goggles.fluid_port.type.fuel").string();
        else if (block_Type.equals(WATER_TYPE)) return CreateLang.builder(Cif.MODID).translate("gui.goggles.fluid_port.type.water").string();
        else return CreateLang.builder(Cif.MODID).translate("gui.goggles.fluid_port.type.out").string();
    }

    @Override
    public boolean addToGoggleTooltip(List<Component> tooltip, boolean isPlayerSneaking) {
        CreateLang.builder(Cif.MODID)
                .translate("gui.goggles.fluid_port.type")
                .style(ChatFormatting.GOLD)
                .add(CreateLang.text(getBlock_Type()).style(ChatFormatting.AQUA))
                .forGoggles(tooltip);

        CreateLang.builder(Cif.MODID)
                .translate("gui.goggles.fluid_port.content")
                .style(ChatFormatting.GREEN)
                .add(fluidContent)
                .forGoggles(tooltip);

        CreateLang.number(tank.getPrimaryHandler().getFluidInTank(0).getAmount()).style(ChatFormatting.AQUA)
                .add(CreateLang.text("mb")).style(ChatFormatting.YELLOW)
                .add(CreateLang.text("/ ").style(ChatFormatting.DARK_GRAY))
                .add(CreateLang.number(capacity).style(ChatFormatting.BLUE))
                .add(CreateLang.text("mb").style(ChatFormatting.BLUE))
                .forGoggles(tooltip);

        CreateLang.text(CURRENT_FLUID_NAME).forGoggles(tooltip);

        return isPlayerSneaking;
    }

    public void autoriseFluid() {
        if (block_Type != null) {
            if (block_Type.equals(FUEL_TYPE)){
                tank.allowInsertion();
                tank.forbidExtraction();
            } else if (block_Type.equals(WATER_TYPE)) {
                tank.allowInsertion();
                tank.forbidExtraction();
            } else if (block_Type.equals(OUT_TYPE)) {
                tank.allowExtraction();
                tank.forbidInsertion();
            }
        }else {
            tank.allowInsertion();
            tank.forbidExtraction();
        }
    }

    private void updateInternal() {
        FluidStack fluidStack = tank.getPrimaryHandler().getFluidInTank(0);
        if (!fluidStack.isEmpty()) {
            fluidContent = CreateLang.fluidName(fluidStack)
                    .style(ChatFormatting.AQUA)
                    .component();
        } else {
            fluidContent = CreateLang.builder(Cif.MODID)
                    .translate("gui.goggles.fluid_port.NoContent")
                    .style(ChatFormatting.AQUA)
                    .component();
        }
    }
}