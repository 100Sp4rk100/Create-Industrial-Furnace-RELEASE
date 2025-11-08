package fr.spark.cif.init;

import com.simibubi.create.AllTags;
import com.tterrag.registrate.util.entry.BlockEntry;
import fr.spark.cif.blocks.Basic_CIF_Block;
import fr.spark.cif.blocks.chemical_cleaner.ChemicalCleanerBlock;
import fr.spark.cif.blocks.industrial_furnace.IndustrialIronBlockRefined;
import fr.spark.cif.blocks.industrial_furnace.control_panel.ControlPanelBlock;
import fr.spark.cif.blocks.industrial_furnace.controller.ControllerBlock;
import fr.spark.cif.blocks.industrial_furnace.diamound_alloy.DiamondAlloyBlock;
import fr.spark.cif.blocks.industrial_furnace.fluid_port.FluidPortBlock;
import fr.spark.cif.blocks.industrial_furnace.furnace.FurnaceBlock;
import fr.spark.cif.blocks.industrial_furnace.mechanical_port.MechanicalPortBlock;
import fr.spark.cif.blocks.portable_tank.advanced_portable_tank.AdvancedPortableTankBlock;
import fr.spark.cif.blocks.portable_tank.advanced_portable_tank.AdvancedPortableTankitem;
import fr.spark.cif.blocks.portable_tank.portable_tank.PortableTankBlock;
import fr.spark.cif.blocks.portable_tank.portable_tank.PortableTankItem;
import fr.spark.cif.utils.Defaults_Properties;

import static fr.spark.cif.Cif.CREATE_REGISTRATE;

public class CIF_blocks_Register {

	public static final BlockEntry<MechanicalPortBlock> MECHANICAL_PORT_BLOCK = CREATE_REGISTRATE
			.block("mechanical_port", MechanicalPortBlock::new)
			.properties(p -> Defaults_Properties.DIAMOND_ALLOY_BLOCK)
			.tag(AllTags.AllBlockTags.SAFE_NBT.tag)
			//.transform(Stres.of(MechanicalPortEntity.DEFAULT_STRESS))
			.item()
			.build()
			.register();

	public static final BlockEntry<IndustrialIronBlockRefined> INDUSTRIAL_IRON_BLOCK_REFINED = CREATE_REGISTRATE
			.block("industrial_iron_block_refined", IndustrialIronBlockRefined::new)
			.properties(p -> Defaults_Properties.DIAMOND_ALLOY_BLOCK)
			.item()
			.build()
			.register();

	public static final BlockEntry<DiamondAlloyBlock> DIAMOND_ALLOY_BLOCK = CREATE_REGISTRATE
			.block("diamond_alloy_block", DiamondAlloyBlock::new)
			.properties(p -> Defaults_Properties.DIAMOND_ALLOY_BLOCK)
			.item()
			.build()
			.register();

	public static final BlockEntry<Basic_CIF_Block> ALLOYNIUM_BLOCK = CREATE_REGISTRATE
			.block("alloynium_block", Basic_CIF_Block::new)
			.properties(p -> Defaults_Properties.ALLOYNIUM_BLOCK)
			.item()
			.build()
			.register();

	public static final BlockEntry<FluidPortBlock> FLUID_PORT_BLOCK = CREATE_REGISTRATE
			.block("fluid_port", FluidPortBlock::new)
			.properties(p -> Defaults_Properties.DIAMOND_ALLOY_BLOCK)
			.tag(AllTags.AllBlockTags.SAFE_NBT.tag)
			.item()
			.build()
			.register();

	public static final BlockEntry<FurnaceBlock> FURNACE_BLOCK = CREATE_REGISTRATE
			.block("furnace_block", FurnaceBlock::new)
			.properties(p -> Defaults_Properties.DIAMOND_ALLOY_BLOCK)
			.item()
			.build()
			.register();

	public static final BlockEntry<ControllerBlock> CONTROLLER_BLOCK = CREATE_REGISTRATE
			.block("controller_block", ControllerBlock::new)
			.properties(p -> Defaults_Properties.DIAMOND_ALLOY_BLOCK)
			.item()
			.build()
			.register();

	public static final BlockEntry<PortableTankBlock> PORTABLE_TANK = CREATE_REGISTRATE
			.block("portable_tank", PortableTankBlock::new)
			.properties(p -> Defaults_Properties.DIAMOND_ALLOY_BLOCK)
			.item(PortableTankItem::new)
			.build()
			.register();

	public static final BlockEntry<AdvancedPortableTankBlock> ADVANCED_PORTABLE_TANK = CREATE_REGISTRATE
			.block("advanced_portable_tank", AdvancedPortableTankBlock::new)
			.properties(p -> Defaults_Properties.ALLOYNIUM_BLOCK)
			.item(AdvancedPortableTankitem::new)
			.build()
			.register();

	public static final BlockEntry<ChemicalCleanerBlock> CHEMICAL_CLEANER_BLOCK = CREATE_REGISTRATE
			.block("chemical_cleaner", ChemicalCleanerBlock::new)
			.properties(p -> Defaults_Properties.DIAMOND_ALLOY_BLOCK)
			.tag(AllTags.AllBlockTags.SAFE_NBT.tag)
			.item()
			.build()
			.register();

	public static final BlockEntry<ControlPanelBlock> CONTROL_PANEL = CREATE_REGISTRATE
			.block("control_panel", ControlPanelBlock::new)
			.properties(p -> Defaults_Properties.ALLOYNIUM_BLOCK)
			.item()
			.build()
			.register();

	public static void register() {}
}
