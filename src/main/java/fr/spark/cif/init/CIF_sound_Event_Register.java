package fr.spark.cif.init;

import fr.spark.cif.Cif;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class CIF_sound_Event_Register {

    private static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(Registries.SOUND_EVENT, Cif.MODID);

    public static final RegistryObject<SoundEvent> SMELT_END = add("smelt_end");
    public static final RegistryObject<SoundEvent> ALERT_EXPLOSION = add("alert_explosion");
    public static final RegistryObject<SoundEvent> MUSIC_1 = add("music_one");
    public static final RegistryObject<SoundEvent> MUSIC_2 = add("music_two");
    public static final RegistryObject<SoundEvent> MUSIC_3 = add("music_three");

    private static RegistryObject<SoundEvent> add(String name) {
        return SOUND_EVENTS.register(name, () ->
                SoundEvent.createVariableRangeEvent(Cif.id(name)));
    }

    public static void register(IEventBus bus) {
        SOUND_EVENTS.register(bus);
    }
}
