package fr.spark.cif.init;

import fr.spark.cif.Cif;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class CIF_creative_tab {

    private static final DeferredRegister<CreativeModeTab> TABS_REGISTER = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Cif.MODID);

    public static final RegistryObject<CreativeModeTab> CIF_TAB = TABS_REGISTER.register("cif_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("item_group." + Cif.MODID + ".ciftab"))
            .icon(() -> new ItemStack(CIF_blocks_Register.DIAMOND_ALLOY_BLOCK.get()))
            .displayItems(CIF_creative_tab::addItems)
            .build()
    );

    private static void addItems(CreativeModeTab.ItemDisplayParameters params, CreativeModeTab.Output output) {
        output.accept(CIF_blocks_Register.FLUID_PORT_BLOCK.get());
        output.accept(CIF_blocks_Register.MECHANICAL_PORT_BLOCK.get());
        output.accept(CIF_blocks_Register.FURNACE_BLOCK.get());
        output.accept(CIF_blocks_Register.CONTROLLER_BLOCK.get());
        output.accept(CIF_blocks_Register.CHEMICAL_CLEANER_BLOCK.get());
        output.accept(CIF_blocks_Register.CONTROL_PANEL.get());
        output.accept(CIF_blocks_Register.INDUSTRIAL_IRON_BLOCK_REFINED.get());
        output.accept(CIF_blocks_Register.DIAMOND_ALLOY_BLOCK.get());
        output.accept(CIF_blocks_Register.ALLOYNIUM_BLOCK.get());
        output.accept(CIF_blocks_Register.PORTABLE_TANK.get());
        output.accept(CIF_blocks_Register.ADVANCED_PORTABLE_TANK.get());
        output.accept(CIF_items_Register.INDUSTRIAL_IRON_SHEET.get());
        output.accept(CIF_items_Register.ACTIVATED_CARBON.get());
        output.accept(CIF_fluids_Register.HOT_WATER.getBucket().get());
        output.accept(CIF_fluids_Register.FUEL.getBucket().get());
        output.accept(CIF_items_Register.MUSIC_DISC_ONE.get());
        output.accept(CIF_items_Register.MUSIC_DISC_TWO.get());
        output.accept(CIF_items_Register.MUSIC_DISC_THREE.get());
    }

    public static void register(IEventBus eventBus) {
        TABS_REGISTER.register(eventBus);
    }
}
