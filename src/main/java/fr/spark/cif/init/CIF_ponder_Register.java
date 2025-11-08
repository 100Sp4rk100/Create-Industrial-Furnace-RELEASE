package fr.spark.cif.init;

import fr.spark.cif.Cif;
import net.createmod.ponder.api.registration.PonderSceneRegistrationHelper;
import net.minecraft.resources.ResourceLocation;

public class CIF_ponder_Register {

    public static void registerScenes(PonderSceneRegistrationHelper<ResourceLocation> helper){
        Cif.FLUID_PORT_STORY_BOARD.addBlock(CIF_blocks_Register.FLUID_PORT_BLOCK);
        Cif.FLUID_PORT_STORY_BOARD.register(helper);

        Cif.FURNACE_STORY_BOARD.addBlock(CIF_blocks_Register.FURNACE_BLOCK);
        Cif.FURNACE_STORY_BOARD.register(helper);

        Cif.CONTROL_PANEL_STORY_BOARD.addBlock(CIF_blocks_Register.CONTROL_PANEL);
        Cif.CONTROL_PANEL_STORY_BOARD.register(helper);

        Cif.CIF_STORY_BOARD.addBlock(CIF_blocks_Register.MECHANICAL_PORT_BLOCK);
        Cif.CIF_STORY_BOARD.addBlock(CIF_blocks_Register.DIAMOND_ALLOY_BLOCK);
        Cif.CIF_STORY_BOARD.addBlock(CIF_blocks_Register.CONTROLLER_BLOCK);
        Cif.CIF_STORY_BOARD.addBlock(CIF_blocks_Register.FURNACE_BLOCK);
        Cif.CIF_STORY_BOARD.addBlock(CIF_blocks_Register.FLUID_PORT_BLOCK);
        Cif.CIF_STORY_BOARD.addBlock(CIF_blocks_Register.CONTROL_PANEL);
        Cif.CIF_STORY_BOARD.register(helper);

        Cif.PORTABLE_TANK_STORY_BOARD.addBlock(CIF_blocks_Register.PORTABLE_TANK);
        Cif.PORTABLE_TANK_STORY_BOARD.addBlock(CIF_blocks_Register.ADVANCED_PORTABLE_TANK);
        Cif.PORTABLE_TANK_STORY_BOARD.register(helper);

        Cif.CHEMICAL_CLEANER_STORY_BOARD.addBlock(CIF_blocks_Register.CHEMICAL_CLEANER_BLOCK);
        Cif.CHEMICAL_CLEANER_STORY_BOARD.addItem(CIF_items_Register.ACTIVATED_CARBON);
        Cif.CHEMICAL_CLEANER_STORY_BOARD.register(helper);

        Cif.GOGGLES_STORY_BOARD.addBlock(CIF_blocks_Register.FURNACE_BLOCK);
        Cif.GOGGLES_STORY_BOARD.addBlock(CIF_blocks_Register.FLUID_PORT_BLOCK);
        Cif.GOGGLES_STORY_BOARD.addBlock(CIF_blocks_Register.CHEMICAL_CLEANER_BLOCK);
        Cif.GOGGLES_STORY_BOARD.addBlock(CIF_blocks_Register.PORTABLE_TANK);
        Cif.GOGGLES_STORY_BOARD.addBlock(CIF_blocks_Register.ADVANCED_PORTABLE_TANK);
        Cif.GOGGLES_STORY_BOARD.register(helper);
    }
}
