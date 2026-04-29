package fr.spark.cif.init;

import com.simibubi.create.foundation.ponder.PonderRegistrationHelper;
import fr.spark.cif.Cif;
import fr.spark.cif.ponder.*;

public class CIF_ponder_Register {

    static final PonderRegistrationHelper HELPER = new PonderRegistrationHelper(Cif.MODID);

    public static void register(){
        HELPER.forComponents(CIF_blocks_Register.FLUID_PORT_BLOCK)
                .addStoryBoard("cif_fluid_port/fluid_port_ponder", Fluid_Port_StoryBoard::changeType)

                .addStoryBoard("cif_furnace_all/cif_furnace_ponder", Industrial_Furnace_StoryBoard::build)
                .addStoryBoard("cif_furnace_all/cif_furnace_ponder", Industrial_Furnace_StoryBoard::connection)
                .addStoryBoard("cif_furnace_all/cif_furnace_ponder", Industrial_Furnace_StoryBoard::use)
                .addStoryBoard("cif_furnace_all/cif_furnace_ponder", Industrial_Furnace_StoryBoard::automate)
                
                .addStoryBoard("cif_furnace_all/cif_furnace_ponder", Industrial_Furnace_StoryBoard::utils)

                .addStoryBoard("goggles/goggles_ponder", Goggles_StoryBoard::about)
        ;

        HELPER.forComponents(CIF_blocks_Register.FURNACE_BLOCK)
                .addStoryBoard("furnace/furnace", Furnace_StoryBoard::change_view)

                .addStoryBoard("cif_furnace_all/cif_furnace_ponder", Industrial_Furnace_StoryBoard::build)
                .addStoryBoard("cif_furnace_all/cif_furnace_ponder", Industrial_Furnace_StoryBoard::connection)
                .addStoryBoard("cif_furnace_all/cif_furnace_ponder", Industrial_Furnace_StoryBoard::use)
                .addStoryBoard("cif_furnace_all/cif_furnace_ponder", Industrial_Furnace_StoryBoard::automate)
                
                .addStoryBoard("cif_furnace_all/cif_furnace_ponder", Industrial_Furnace_StoryBoard::utils)

                .addStoryBoard("goggles/goggles_ponder", Goggles_StoryBoard::about)
        ;

        HELPER.forComponents(CIF_blocks_Register.CONTROL_PANEL)
                .addStoryBoard("control_panel/control_panel_ponder", Control_Panel_StoryBoard::usage)
                .addStoryBoard("control_panel/control_panel_ponder", Control_Panel_StoryBoard::changeView)

                .addStoryBoard("cif_furnace_all/cif_furnace_ponder", Industrial_Furnace_StoryBoard::build)
                .addStoryBoard("cif_furnace_all/cif_furnace_ponder", Industrial_Furnace_StoryBoard::connection)
                .addStoryBoard("cif_furnace_all/cif_furnace_ponder", Industrial_Furnace_StoryBoard::use)
                .addStoryBoard("cif_furnace_all/cif_furnace_ponder", Industrial_Furnace_StoryBoard::automate)
                
                .addStoryBoard("cif_furnace_all/cif_furnace_ponder", Industrial_Furnace_StoryBoard::utils)
        ;

        HELPER.forComponents(CIF_blocks_Register.MECHANICAL_PORT_BLOCK)
                .addStoryBoard("cif_furnace_all/cif_furnace_ponder", Industrial_Furnace_StoryBoard::build)
                .addStoryBoard("cif_furnace_all/cif_furnace_ponder", Industrial_Furnace_StoryBoard::connection)
                .addStoryBoard("cif_furnace_all/cif_furnace_ponder", Industrial_Furnace_StoryBoard::use)
                .addStoryBoard("cif_furnace_all/cif_furnace_ponder", Industrial_Furnace_StoryBoard::automate)
                
                .addStoryBoard("cif_furnace_all/cif_furnace_ponder", Industrial_Furnace_StoryBoard::utils)
        ;

        HELPER.forComponents(CIF_blocks_Register.DIAMOND_ALLOY_BLOCK)
                .addStoryBoard("cif_furnace_all/cif_furnace_ponder", Industrial_Furnace_StoryBoard::build)
                .addStoryBoard("cif_furnace_all/cif_furnace_ponder", Industrial_Furnace_StoryBoard::connection)
                .addStoryBoard("cif_furnace_all/cif_furnace_ponder", Industrial_Furnace_StoryBoard::use)
                .addStoryBoard("cif_furnace_all/cif_furnace_ponder", Industrial_Furnace_StoryBoard::automate)
                
                .addStoryBoard("cif_furnace_all/cif_furnace_ponder", Industrial_Furnace_StoryBoard::utils)
        ;

        HELPER.forComponents(CIF_blocks_Register.CONTROLLER_BLOCK)
                .addStoryBoard("cif_furnace_all/cif_furnace_ponder", Industrial_Furnace_StoryBoard::build)
                .addStoryBoard("cif_furnace_all/cif_furnace_ponder", Industrial_Furnace_StoryBoard::connection)
                .addStoryBoard("cif_furnace_all/cif_furnace_ponder", Industrial_Furnace_StoryBoard::use)
                .addStoryBoard("cif_furnace_all/cif_furnace_ponder", Industrial_Furnace_StoryBoard::automate)
                
                .addStoryBoard("cif_furnace_all/cif_furnace_ponder", Industrial_Furnace_StoryBoard::utils)
        ;

        HELPER.forComponents(CIF_blocks_Register.PORTABLE_TANK)
                .addStoryBoard("portable_tank/portable_tank", Portable_Tank_StoryBoard::use)

                .addStoryBoard("goggles/goggles_ponder", Goggles_StoryBoard::about)
        ;

        HELPER.forComponents(CIF_blocks_Register.ADVANCED_PORTABLE_TANK)
                .addStoryBoard("portable_tank/portable_tank", Portable_Tank_StoryBoard::use)

                .addStoryBoard("goggles/goggles_ponder", Goggles_StoryBoard::about)
        ;

        HELPER.forComponents(CIF_blocks_Register.CHEMICAL_CLEANER_BLOCK)
                .addStoryBoard("chemical_cleaner/chemical_cleaner_ponder", Chemical_Cleaner_StoryBoard::use)

                .addStoryBoard("goggles/goggles_ponder", Goggles_StoryBoard::about)
        ;

        HELPER.forComponents(CIF_items_Register.ACTIVATED_CARBON)
                .addStoryBoard("chemical_cleaner/chemical_cleaner_ponder", Chemical_Cleaner_StoryBoard::use)
        ;
    }
}
