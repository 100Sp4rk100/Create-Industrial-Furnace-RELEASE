package fr.spark.cif.init;

import com.tterrag.registrate.util.entry.FluidEntry;
import fr.spark.cif.Cif;
import fr.spark.cif.fluids.fuel.FuelAttributes;
import fr.spark.cif.fluids.fuel.FuelBlock;
import fr.spark.cif.fluids.hot_water.HotWaterAttributes;
import fr.spark.cif.fluids.hot_water.HotWaterBlock;
import net.minecraftforge.fluids.ForgeFlowingFluid;

public class CIF_fluids_Register {
    public static final FluidEntry<ForgeFlowingFluid.Flowing> HOT_WATER = Cif.CREATE_REGISTRATE
            .standardFluid("hot_water", HotWaterAttributes::new)
            .properties(b -> b.viscosity(2000)
                    .density(400)
                    .canConvertToSource((false))
                    .lightLevel(8)
                    .canHydrate(true)
                    .temperature(1000)
                    .supportsBoating(true)
                    .canSwim(true)
            )
            .fluidProperties(p -> p.levelDecreasePerBlock(2)
                    .tickRate(5)
                    .slopeFindDistance(6)
                    .explosionResistance(100f)
            )
            .source(ForgeFlowingFluid.Source::new)
            .block((fluid, properties) -> new HotWaterBlock(fluid, properties))
            .build()
            .bucket()
            .build()
            .register();

    public static final FluidEntry<ForgeFlowingFluid.Flowing> FUEL = Cif.CREATE_REGISTRATE
            .standardFluid("fuel", FuelAttributes::new)
            .properties(b -> b.viscosity(2000)
                    .density(400)
                    .canConvertToSource((false))
                    .lightLevel(12)
                    .canHydrate(false)
                    .temperature(5000)
                    .supportsBoating(false)
                    .canSwim(false)
            )
            .fluidProperties(p -> p.levelDecreasePerBlock(2)
                    .tickRate(5)
                    .slopeFindDistance(6)
                    .explosionResistance(500f)
            )
            .source(ForgeFlowingFluid.Source::new)
            .block((fluid, properties) -> new FuelBlock(fluid, properties))
            .build()
            .bucket()
            .build()
            .register();



    public static void register() {}
}
