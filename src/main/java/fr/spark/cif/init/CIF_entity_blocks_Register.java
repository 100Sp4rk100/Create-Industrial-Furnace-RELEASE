package fr.spark.cif.init;

import com.tterrag.registrate.util.entry.BlockEntityEntry;
import fr.spark.cif.Cif;
import fr.spark.cif.blocks.chemical_cleaner.ChemicalCleanerEntity;
import fr.spark.cif.blocks.industrial_furnace.control_panel.ControlPanelEntity;
import fr.spark.cif.blocks.industrial_furnace.controller.ControllerEntity;
import fr.spark.cif.blocks.industrial_furnace.fluid_port.FluidPortEntity;
import fr.spark.cif.blocks.industrial_furnace.furnace.FurnaceEntity;
import fr.spark.cif.blocks.industrial_furnace.mechanical_port.MechanicalPortEntity;
import fr.spark.cif.blocks.portable_tank.advanced_portable_tank.AdvancedPortableTankEntity;
import fr.spark.cif.blocks.portable_tank.portable_tank.PortableTankEntity;

public class CIF_entity_blocks_Register {

    public static final BlockEntityEntry<MechanicalPortEntity> MECHANICAL_PORT_ENTITY = Cif.CREATE_REGISTRATE
            .blockEntity("mechanical_port", MechanicalPortEntity::new)
            .validBlocks(CIF_blocks_Register.MECHANICAL_PORT_BLOCK)
            .register();

    public static final BlockEntityEntry<FluidPortEntity> FLUID_PORT_ENTITY = Cif.CREATE_REGISTRATE
            .blockEntity("fluid_port", FluidPortEntity::new)
            .validBlocks(CIF_blocks_Register.FLUID_PORT_BLOCK)
            .register();

    public static final BlockEntityEntry<FurnaceEntity> FURNACE_ENTITY_BLOCK = Cif.CREATE_REGISTRATE
            .blockEntity("furnace_block", FurnaceEntity::new)
            .validBlocks(CIF_blocks_Register.FURNACE_BLOCK)
            .register();

    public static final BlockEntityEntry<ControllerEntity> CONTROLLER_ENTITY_BLOCK = Cif.CREATE_REGISTRATE
            .blockEntity("controller_block", ControllerEntity::new)
            .validBlocks(CIF_blocks_Register.CONTROLLER_BLOCK)
            .register();

    public static final BlockEntityEntry<PortableTankEntity> PORTABLE_TANK_ENTITY = Cif.CREATE_REGISTRATE
            .blockEntity("portable_tank", PortableTankEntity::new)
            .validBlocks(CIF_blocks_Register.PORTABLE_TANK)
            .register();

    public static final BlockEntityEntry<AdvancedPortableTankEntity> ADVANCED_PORTABLE_TANK_ENTITY = Cif.CREATE_REGISTRATE
            .blockEntity("advanced_portable_tank", AdvancedPortableTankEntity::new)
            .validBlocks(CIF_blocks_Register.ADVANCED_PORTABLE_TANK)
            .register();

    public static final BlockEntityEntry<ChemicalCleanerEntity> CHEMICAL_CLEANER_ENTITY = Cif.CREATE_REGISTRATE
            .blockEntity("chemical_cleaner", ChemicalCleanerEntity::new)
            .validBlocks(CIF_blocks_Register.CHEMICAL_CLEANER_BLOCK)
            .register();

    public static final BlockEntityEntry<ControlPanelEntity> CONTROL_PANEL_ENTITY = Cif.CREATE_REGISTRATE
            .blockEntity("control_panel", ControlPanelEntity::new)
            .validBlocks(CIF_blocks_Register.CONTROL_PANEL)
            .register();

    public static void register() {}
}
