package fr.spark.cif.events;

import com.simibubi.create.Create;
import fr.spark.cif.blocks.chemical_cleaner.ChemicalCleanerEntity;
import fr.spark.cif.blocks.industrial_furnace.control_panel.ControlPanelEntity;
import fr.spark.cif.blocks.industrial_furnace.controller.ControllerEntity;
import fr.spark.cif.blocks.industrial_furnace.fluid_port.FluidPortEntity;
import fr.spark.cif.blocks.industrial_furnace.furnace.FurnaceBlock;
import fr.spark.cif.blocks.industrial_furnace.furnace.FurnaceEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.ForgeRegistries;

import static com.simibubi.create.content.kinetics.base.DirectionalKineticBlock.FACING;

public class RightClickEvent {

    @SubscribeEvent
    public static void RightClickBlock(PlayerInteractEvent.RightClickBlock event) {

        Player player = event.getEntity();
        Level level = event.getLevel();
        BlockPos pos = event.getPos();

        BlockEntity blockEntity = level.getBlockEntity(pos);

        if (blockEntity instanceof FurnaceEntity furnaceEntity) {

            furnace_block(event, furnaceEntity, blockEntity, player, level, pos);

        } else if (blockEntity instanceof FluidPortEntity) {

            fluid_port_block(event, blockEntity, player, level, pos);

        } else if (blockEntity instanceof ChemicalCleanerEntity chemicalCleanerEntity) {

            chemical_cleaner(event, chemicalCleanerEntity, player, level, pos);

        } else if (blockEntity instanceof ControlPanelEntity controlPanelEntity) {
            control_panel(controlPanelEntity, player);
        }
    }

    private static void furnace_block(PlayerInteractEvent.RightClickBlock event, FurnaceEntity furnaceEntity, BlockEntity blockEntity, Player player, Level level, BlockPos pos){
        BlockEntity be = level.getBlockEntity(pos.relative(level.getBlockState(pos).getValue(FACING).getOpposite()));

        if (be instanceof ControllerEntity controller) {
            if (!player.getMainHandItem().isEmpty() && !player.isShiftKeyDown() &&
                    player.getItemInHand(InteractionHand.MAIN_HAND).getItem() != ForgeRegistries.ITEMS.getValue(Create.asResource("wrench"))) {

                if (!controller.checkStructure() || FurnaceBlock.getAnyCookingResult(level, player.getMainHandItem()).isEmpty()) return;

                ItemStack item = player.getMainHandItem().copy();

                for (int i = 0; i < 4; i++) {

                    ItemStack slot = controller.getItemhandler().getStackInSlot(i);

                    if (ItemStack.isSameItemSameTags(slot, item) && slot.getCount() < slot.getMaxStackSize()) {
                        int transferable = Math.min(item.getCount(), slot.getMaxStackSize() - slot.getCount());
                        slot.grow(transferable);
                        player.getMainHandItem().shrink(transferable);
                        break;
                    }

                    if (slot.isEmpty()) {
                        controller.getItemhandler().setStackInSlot(i, item);
                        player.getMainHandItem().shrink(item.getCount());
                        break;
                    }
                }

            } else if (player.isShiftKeyDown() &&
                    player.getItemInHand(InteractionHand.MAIN_HAND).getItem() != ForgeRegistries.ITEMS.getValue(Create.asResource("wrench"))) {
                for (int i = 0; i < 4; i++) {
                    int outSlot = 4 + i;
                    int inSlot = i;
                    ItemStack extracted = controller.getItemhandler().extractItem(outSlot, Integer.MAX_VALUE, false);

                    if (extracted.isEmpty()) {
                        extracted = controller.getItemhandler().extractItem(inSlot, Integer.MAX_VALUE, false);
                    }

                    if (!extracted.isEmpty() && player.getInventory().getFreeSlot() > -1) {
                        player.getInventory().add(extracted);
                    }
                }
            } else if (player.getItemInHand(InteractionHand.MAIN_HAND).getItem() == ForgeRegistries.ITEMS.getValue(Create.asResource("wrench"))) {

                if (furnaceEntity.debugMod == 1){
                    furnaceEntity.debugMod = 2;
                } else if (furnaceEntity.debugMod == 2) {
                    furnaceEntity.debugMod = 3;
                }else {
                    furnaceEntity.debugMod = 1;
                }

            }
        }
    }

    private static void fluid_port_block(PlayerInteractEvent.RightClickBlock event, BlockEntity blockEntity, Player player, Level level, BlockPos pos){
        if (player.getItemInHand(InteractionHand.MAIN_HAND).getItem() == ForgeRegistries.ITEMS.getValue(Create.asResource("wrench"))) {
            if (!player.getMainHandItem().isEmpty()) {
                FluidPortEntity fluidPortEntity = (FluidPortEntity) blockEntity;

                if (fluidPortEntity.block_Type.equals(FluidPortEntity.FUEL_TYPE)) {
                    fluidPortEntity.block_Type = FluidPortEntity.WATER_TYPE;
                    fluidPortEntity.autoriseFluid();
                } else if (fluidPortEntity.block_Type.equals(FluidPortEntity.WATER_TYPE)) {
                    fluidPortEntity.block_Type = FluidPortEntity.OUT_TYPE;
                    fluidPortEntity.autoriseFluid();
                } else if (fluidPortEntity.block_Type.equals(FluidPortEntity.OUT_TYPE)) {
                    fluidPortEntity.block_Type = FluidPortEntity.FUEL_TYPE;
                    fluidPortEntity.autoriseFluid();
                }
            }
        }
    }

    private static void chemical_cleaner(PlayerInteractEvent.RightClickBlock event, ChemicalCleanerEntity chemicalCleanerEntity, Player player, Level level, BlockPos pos){
        if (!player.getMainHandItem().isEmpty() &&
                player.getItemInHand(InteractionHand.MAIN_HAND).getItem().equals(ChemicalCleanerEntity.fuel)
                && !player.isShiftKeyDown()
        ) {

            ItemStack item = player.getMainHandItem().copy();

            ItemStack slot = chemicalCleanerEntity.getItemHandler().getStackInSlot(0);

            if (ItemStack.isSameItemSameTags(slot, item) && slot.getCount() < slot.getMaxStackSize()) {
                int transferable = Math.min(item.getCount(), slot.getMaxStackSize() - slot.getCount());
                slot.grow(transferable);
                player.getMainHandItem().shrink(transferable);
            }

            if (slot.isEmpty()) {
                chemicalCleanerEntity.getItemHandler().setStackInSlot(0, item);
                player.getMainHandItem().shrink(item.getCount());
            }

        }else if (player.getMainHandItem().isEmpty()
                && player.isShiftKeyDown()
                && player.getInventory().getFreeSlot() > -1
        ){
            ItemStack extracted = chemicalCleanerEntity.getItemHandler().extractItem(0, Integer.MAX_VALUE, false);

            if (!extracted.isEmpty()) {
                player.getInventory().add(extracted);
            }

        }
    }

    private static void control_panel(ControlPanelEntity controlPanelEntity, Player player){
        if (player.getItemInHand(InteractionHand.MAIN_HAND).getItem() == ForgeRegistries.ITEMS.getValue(Create.asResource("wrench"))) {
            controlPanelEntity.switchView();
        }
    }
}
