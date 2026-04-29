package fr.spark.cif.blocks.industrial_furnace.control_panel;

import com.simibubi.create.api.equipment.goggles.IHaveGoggleInformation;
import com.simibubi.create.foundation.blockEntity.SmartBlockEntity;
import com.simibubi.create.foundation.blockEntity.behaviour.BlockEntityBehaviour;
import com.simibubi.create.foundation.utility.CreateLang;
import fr.spark.cif.Cif;
import fr.spark.cif.blocks.industrial_furnace.controller.ControllerEntity;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;
import java.util.List;

public class ControlPanelEntity extends SmartBlockEntity implements IHaveGoggleInformation {

    private int VIEW;
    public ItemStack SLOT1;
    public ItemStack SLOT2;
    public ItemStack SLOT3;
    public ItemStack SLOT4;
    public ItemStack SLOT5;
    public ItemStack SLOT6;
    public ItemStack SLOT7;
    public ItemStack SLOT8;

    public ControlPanelEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        VIEW = 0;
        SLOT1 = ItemStack.EMPTY;
        SLOT2 = ItemStack.EMPTY;
        SLOT3 = ItemStack.EMPTY;
        SLOT4 = ItemStack.EMPTY;
        SLOT5 = ItemStack.EMPTY;
        SLOT6 = ItemStack.EMPTY;
        SLOT7 = ItemStack.EMPTY;
        SLOT8 = ItemStack.EMPTY;
    }

    @Override
    public void addBehaviours(List<BlockEntityBehaviour> behaviours) {}

    public List<ItemStack> getRenderStack(){
        List<ItemStack> stacks = new ArrayList<>();
        if(isInputView()){
            stacks.add(SLOT1);
            stacks.add(SLOT2);
            stacks.add(SLOT3);
            stacks.add(SLOT4);
        }else if (isOutputView()){
            stacks.add(SLOT5);
            stacks.add(SLOT6);
            stacks.add(SLOT7);
            stacks.add(SLOT8);
        }else {
            stacks.add(ItemStack.EMPTY);
            stacks.add(ItemStack.EMPTY);
            stacks.add(ItemStack.EMPTY);
            stacks.add(ItemStack.EMPTY);
        }

        return stacks;
    }

    public void switchView(){
        if (VIEW == 0 || VIEW == 1) VIEW ++;
        else VIEW = 0;
    }

    public void setVirtualSlot(List<ItemStack> stacks){
        if (isInputView()){
            SLOT1 = stacks.get(0);
            SLOT2 = stacks.get(1);
            SLOT3 = stacks.get(2);
            SLOT4 = stacks.get(3);
        }else if (isOutputView()){
            SLOT5 = stacks.get(0);
            SLOT6 = stacks.get(1);
            SLOT7 = stacks.get(2);
            SLOT8 = stacks.get(3);
        }
    }

    public boolean isInputView(){
        return VIEW == 1;
    }

    public boolean isOutputView(){
        return VIEW == 2;
    }

    @Override
    protected void write(CompoundTag compound, boolean clientPacket) {
        super.write(compound, clientPacket);
        compound.putInt("view", VIEW);
    }

    @Override
    protected void read(CompoundTag compound, boolean clientPacket) {
        super.read(compound, clientPacket);
        VIEW = compound.getInt("view");
    }

    @Override
    public boolean addToGoggleTooltip(List<Component> tooltip, boolean isPlayerSneaking) {
        if (level == null){
            return isPlayerSneaking;
        }

        CreateLang.builder(Cif.MODID)
                .translate("gui.goggles.control_panel.mod_txt")
                .style(ChatFormatting.GREEN)
                .forGoggles(tooltip);

        if (VIEW == 1) {

            CreateLang.builder(Cif.MODID)
                    .translate("gui.goggles.control_panel.mod_input")
                    .style(ChatFormatting.BLUE)
                    .forGoggles(tooltip);
        } else if (VIEW == 2) {

            CreateLang.builder(Cif.MODID)
                    .translate("gui.goggles.control_panel.mod_output")
                    .style(ChatFormatting.BLUE)
                    .forGoggles(tooltip);

        }else {

            CreateLang.builder(Cif.MODID)
                    .translate("gui.goggles.control_panel.mod_nothing")
                    .style(ChatFormatting.RED)
                    .forGoggles(tooltip);

        }

        return isPlayerSneaking;
    }
}
