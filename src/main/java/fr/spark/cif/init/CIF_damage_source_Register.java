package fr.spark.cif.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.level.Level;

public class CIF_damage_source_Register {

    public static DamageSource hot_water(Level level) {
        return create(CIF_damage_type_Register.HOT_WATER, level);
    }

    public static DamageSource fuel(Level level) {
        return create(CIF_damage_type_Register.FUEl, level);
    }

    public static DamageSource create(ResourceKey<DamageType> damageType, Level level) {
        return new DamageSource(level.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(damageType));
    }

    public static void register() {}
}
