package fr.spark.cif.init;

import fr.spark.cif.Cif;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageType;

public class CIF_damage_type_Register {
    public static final ResourceKey<DamageType>
            HOT_WATER = key("hot_water"),
            FUEl = key("fuel");

    private static ResourceKey<DamageType> key(String name) {
        return ResourceKey.create(Registries.DAMAGE_TYPE, Cif.id(name));
    }

    public static void register() {}
}
