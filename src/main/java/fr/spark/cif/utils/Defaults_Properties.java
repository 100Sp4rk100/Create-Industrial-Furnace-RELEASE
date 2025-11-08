package fr.spark.cif.utils;

import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.material.MapColor;

public class Defaults_Properties {
    public static final Properties DIAMOND_ALLOY_BLOCK = Properties.of()
            .strength(1.5f, 0f)
            .mapColor(MapColor.COLOR_BLUE)
            .sound(SoundType.METAL);

    public static final Properties ALLOYNIUM_BLOCK = Properties.of()
            .strength(3.0f, 2f)
            .mapColor(MapColor.COLOR_GREEN)
            .sound(SoundType.METAL);
}
