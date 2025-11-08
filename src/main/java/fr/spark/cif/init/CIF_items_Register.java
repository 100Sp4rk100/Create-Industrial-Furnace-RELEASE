package fr.spark.cif.init;

import com.tterrag.registrate.util.entry.ItemEntry;
import fr.spark.cif.Cif;
import fr.spark.cif.items.Basic_CIF_Item;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.RecordItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class CIF_items_Register {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Cif.MODID);

    public static final RegistryObject<Item> MUSIC_DISC_ONE = ITEMS.register("music_disc_one",
            () -> new RecordItem(6, CIF_sound_Event_Register.MUSIC_1, new Item.Properties().stacksTo(1), 2440));

    public static final RegistryObject<Item> MUSIC_DISC_TWO = ITEMS.register("music_disc_two",
            () -> new RecordItem(6, CIF_sound_Event_Register.MUSIC_2, new Item.Properties().stacksTo(1), 2920));

    public static final RegistryObject<Item> MUSIC_DISC_THREE = ITEMS.register("music_disc_three",
            () -> new RecordItem(6, CIF_sound_Event_Register.MUSIC_3, new Item.Properties().stacksTo(1), 2020));

    public static final ItemEntry<Basic_CIF_Item> INDUSTRIAL_IRON_SHEET = Cif.CREATE_REGISTRATE
            .item("industrial_iron_sheet", Basic_CIF_Item::new)
            .properties(p -> p.stacksTo(1))
            .register();

    public static final ItemEntry<Basic_CIF_Item> UNPROCESSED_INDUSTRIAL_IRON_SHEET = Cif.CREATE_REGISTRATE
            .item("unprocessed_industrial_iron_sheet", Basic_CIF_Item::new)
            .properties(p -> p.stacksTo(1))
            .register();

    public static final ItemEntry<Basic_CIF_Item> ACTIVATED_CARBON = Cif.CREATE_REGISTRATE
            .item("activated_carbon", Basic_CIF_Item::new)
            .properties(p -> p.stacksTo(64))
            .register();

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
