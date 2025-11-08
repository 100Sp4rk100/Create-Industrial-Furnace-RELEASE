package fr.spark.cif;

import com.simibubi.create.foundation.data.CreateRegistrate;
import fr.spark.cif.events.RightClickEvent;
import fr.spark.cif.init.*;
import fr.spark.cif.compatibility.ponder.CifPonderPlugin;
import fr.spark.cif.ponder.story_board.*;
import net.createmod.ponder.foundation.PonderIndex;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Cif.MODID)
public class Cif {
    public static final String MODID = "cif";
    public static final CreateRegistrate CREATE_REGISTRATE = CreateRegistrate.create(Cif.MODID);
    public static final Industrial_Furnace_StoryBoard CIF_STORY_BOARD = new Industrial_Furnace_StoryBoard();
    public static final Fluid_Port_StoryBoard FLUID_PORT_STORY_BOARD = new Fluid_Port_StoryBoard();
    public static final Portable_Tank_StoryBoard PORTABLE_TANK_STORY_BOARD = new Portable_Tank_StoryBoard();
    public static final Furnace_StoryBoard FURNACE_STORY_BOARD = new Furnace_StoryBoard();
    public static final Goggles_StoryBoard GOGGLES_STORY_BOARD = new Goggles_StoryBoard();
    public static final Chemical_Cleaner_StoryBoard CHEMICAL_CLEANER_STORY_BOARD = new Chemical_Cleaner_StoryBoard();
    public static final Control_Panel_StoryBoard CONTROL_PANEL_STORY_BOARD = new Control_Panel_StoryBoard();

    public Cif() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        CREATE_REGISTRATE.registerEventListeners(eventBus);
        CIF_damage_type_Register.register();
        CIF_damage_source_Register.register();
        CIF_creative_tab.register(eventBus);
        CIF_recipes_Register.register(eventBus);
        CIF_entity_blocks_Register.register();
        CIF_blocks_Register.register();
        CIF_fluids_Register.register();
        CIF_sound_Event_Register.register(eventBus);
        CIF_items_Register.register(eventBus);

        eventBus.addListener(this::doClientStuff);

        MinecraftForge.EVENT_BUS.register(RightClickEvent.class);
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        PonderIndex.addPlugin(new CifPonderPlugin());

        ItemBlockRenderTypes.setRenderLayer(CIF_blocks_Register.CONTROL_PANEL.get(), RenderType.solid());
    }

    public static ResourceLocation id(String path) {
        return new ResourceLocation(Cif.MODID, path);
    }
}
