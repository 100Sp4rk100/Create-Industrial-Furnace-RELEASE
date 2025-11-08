package fr.spark.cif.events;

import fr.spark.cif.Cif;
import fr.spark.cif.blocks.industrial_furnace.control_panel.ControlPanelRenderer;
import fr.spark.cif.init.CIF_entity_blocks_Register;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Cif.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventBusClientEvents {

    @SubscribeEvent
    public static void registerBlockEntityRenderer(EntityRenderersEvent.RegisterRenderers event){
        event.registerBlockEntityRenderer(CIF_entity_blocks_Register.CONTROL_PANEL_ENTITY.get(), ControlPanelRenderer::new);
    }

}
