package fr.spark.cif;

import com.simibubi.create.foundation.data.CreateRegistrate;
import fr.spark.cif.events.RightClickEvent;
import fr.spark.cif.init.*;
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
        event.enqueueWork(CIF_ponder_Register::register);

        ItemBlockRenderTypes.setRenderLayer(CIF_blocks_Register.CONTROL_PANEL.get(), RenderType.solid());
    }

    public static ResourceLocation id(String path) {
        return new ResourceLocation(Cif.MODID, path);
    }
}
