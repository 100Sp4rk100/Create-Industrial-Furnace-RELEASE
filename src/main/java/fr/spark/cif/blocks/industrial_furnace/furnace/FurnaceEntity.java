package fr.spark.cif.blocks.industrial_furnace.furnace;

import com.simibubi.create.AllItems;
import com.simibubi.create.content.equipment.goggles.IHaveGoggleInformation;
import com.simibubi.create.foundation.blockEntity.SmartBlockEntity;
import com.simibubi.create.foundation.blockEntity.behaviour.BlockEntityBehaviour;
import com.simibubi.create.foundation.utility.Lang;
import fr.spark.cif.Cif;
import fr.spark.cif.blocks.industrial_furnace.controller.ControllerEntity;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static com.simibubi.create.content.kinetics.base.DirectionalKineticBlock.FACING;

public class FurnaceEntity extends SmartBlockEntity implements IHaveGoggleInformation {

    public int debugMod;

    public FurnaceEntity(BlockEntityType<?> typeIn, BlockPos pos, BlockState state) {
        super(typeIn, pos, state);

        this.debugMod = 1;
    }

    @Override
    public void addBehaviours(List<BlockEntityBehaviour> behaviours) {}

    @Override
    public void tick(){
        super.tick();
    }

    @Override
    public void read(CompoundTag tag, boolean clientPacket) {
        super.read(tag, clientPacket);

        debugMod = tag.getInt("debugMod");

    }

    @Override
    public void write(CompoundTag tag, boolean clientPacket) {
        super.write(tag, clientPacket);

        tag.putInt("debugMod", debugMod);

    }


    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == ForgeCapabilities.ITEM_HANDLER) {
            BlockEntity be = getBlockEntityBehind();
            if (be instanceof ControllerEntity controller && controller.checkStructure()) {
                return controller.getCapability(cap, side);
            }
        }
        return super.getCapability(cap, side);
    }

    private BlockEntity getBlockEntityBehind(){
        return level.getBlockEntity(worldPosition.relative(level.getBlockState(worldPosition).getValue(FACING).getOpposite()));
    }

    @Override
    public boolean addToGoggleTooltip(List<Component> tooltip, boolean isPlayerSneaking) {
        if (level == null){
            return isPlayerSneaking;
        }
        BlockEntity be = getBlockEntityBehind();
        if (be instanceof ControllerEntity controller) {
            if(controller.checkStructure()){

                if (!controller.on){
                    Lang.builder(Cif.MODID)
                            .translate("gui.goggles.furnace.of")
                            .style(ChatFormatting.DARK_RED)
                            .forGoggles(tooltip);

                }else if (!controller.isHot){
                    Lang.builder(Cif.MODID)
                            .translate("gui.goggles.furnace.preheating")
                            .style(ChatFormatting.DARK_RED)
                            .forGoggles(tooltip);
                }

                if (controller.explode){
                    Lang.builder(Cif.MODID)
                            .translate("gui.goggles.furnace.explosion")
                            .style(ChatFormatting.DARK_RED)
                            .forGoggles(tooltip);
                }

                boolean all = controller.checkRequirement()
                        && controller.canPutHotWater(ControllerEntity.WATER_CONSUMER)
                        && controller.canTakeWater(ControllerEntity.WATER_CONSUMER);

                setCurrentViewModGoggle(tooltip);

                if (debugMod == 1 || (debugMod == 3 && all)){
                    Lang.builder(Cif.MODID)
                            .translate("gui.goggles.furnace.slots")
                            .style(ChatFormatting.GOLD)
                            .forGoggles(tooltip);

                    addGoggleInfoINP(controller, tooltip, 1, controller.slot1, controller.slot1Tick);
                    addGoggleInfoOUT(controller, tooltip, controller.slot5);

                    addGoggleInfoINP(controller, tooltip, 2, controller.slot2, controller.slot2Tick);
                    addGoggleInfoOUT(controller, tooltip, controller.slot6);

                    addGoggleInfoINP(controller, tooltip, 3, controller.slot3, controller.slot3Tick);
                    addGoggleInfoOUT(controller, tooltip, controller.slot7);

                    addGoggleInfoINP(controller, tooltip, 4, controller.slot4, controller.slot4Tick);
                    addGoggleInfoOUT(controller, tooltip, controller.slot8);

                }
                if (debugMod == 2 || (debugMod == 3 && !all)){
                    Lang.builder(Cif.MODID)
                            .translate("gui.goggles.furnace.tocheck")
                            .style(ChatFormatting.GOLD)
                            .forGoggles(tooltip);

                    addNotAll("gui.goggles.furnace.tocheck.overstressed", tooltip, !controller.isOverstressed());
                    addNotAll("gui.goggles.furnace.tocheck.speedrequirement", tooltip, controller.isSpeedRequirementFulfilled());
                    addNotAll("gui.goggles.furnace.tocheck.waterinput", tooltip, controller.isWaterInput());
                    addNotAll("gui.goggles.furnace.tocheck.hotwateroutput", tooltip, controller.isWaterOut());
                    addNotAll("gui.goggles.furnace.tocheck.fuelinput", tooltip, controller.isFuelInput());

                    addNotAll("gui.goggles.furnace.tocheck.takefuelinput", tooltip, controller.canTakeFuel(ControllerEntity.FUEL_CONSUMER));
                    addNotAll("gui.goggles.furnace.tocheck.takewaterinput", tooltip, controller.canTakeWater(ControllerEntity.WATER_CONSUMER));
                    addNotAll("gui.goggles.furnace.tocheck.puthotwateroutput", tooltip, controller.canPutHotWater(ControllerEntity.WATER_CONSUMER));
                }

            }else {
                Lang.builder(Cif.MODID)
                        .translate("gui.goggles.furnace.invalid")
                        .style(ChatFormatting.DARK_RED)
                        .forGoggles(tooltip);
            }
        }


        return isPlayerSneaking;
    }

    @Override
    public ItemStack getIcon(boolean isPlayerSneaking){
        BlockEntity be = getBlockEntityBehind();
        if (be instanceof ControllerEntity controller) {
            if (!controller.checkStructure()) {
               return Items.BARRIER.getDefaultInstance();
            }
        }
        return AllItems.GOGGLES.asStack();
    }

    private void addNotAll(String langKey, List<Component> toolip, boolean bool){
        Lang.builder(Cif.MODID)
                .translate(langKey)
                .style(notAllColor(bool))
                .forGoggles(toolip);
    }

    private ChatFormatting notAllColor(boolean bool){
        if (bool){
            return ChatFormatting.BLUE;
        }
        return ChatFormatting.RED;
    }

    private void addGoggleInfoINP(ControllerEntity controller, List<Component> tooltip, Integer index, ItemStack stack, int ticks){
        Lang.text(index + " -> ")
                .style(ChatFormatting.AQUA)
                .add(Lang.itemName(stack)
                        .style(ChatFormatting.GREEN))
                .add(Lang.text("(x"+stack.getCount()+")").style(ChatFormatting.RED))
                .forGoggles(tooltip);

    }

    private void addGoggleInfoOUT(ControllerEntity controller, List<Component> tooltip, ItemStack stack){
        Lang.builder(Cif.MODID)
                .translate("gui.goggles.furnace.slots.out")
                .style(ChatFormatting.GOLD)
                .forGoggles(tooltip);

        Lang.itemName(stack).style(ChatFormatting.GREEN)
                .add(Lang.text("(x"+stack.getCount()+")").style(ChatFormatting.RED))
                .forGoggles(tooltip);
    }

    private void setCurrentViewModGoggle(List<Component> tooltip){
        String key = "";

        switch (debugMod){
            case 1 -> key = "gui.goggles.furnace.mod.content";
            case 2 -> key = "gui.goggles.furnace.mod.tocheck";
            case 3 -> key = "gui.goggles.furnace.mod.automatic";
        }

        Lang.builder(Cif.MODID)
                .translate("gui.goggles.furnace.mod")
                .style(ChatFormatting.DARK_PURPLE)
                .add(Lang.builder(Cif.MODID).translate(key)
                        .style(ChatFormatting.LIGHT_PURPLE))
                .forGoggles(tooltip);
    }
}
