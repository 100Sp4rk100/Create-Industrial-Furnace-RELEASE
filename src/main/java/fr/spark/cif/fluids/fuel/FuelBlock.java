package fr.spark.cif.fluids.fuel;

import fr.spark.cif.init.CIF_damage_source_Register;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.fluids.ForgeFlowingFluid;

import java.util.function.Supplier;

public class FuelBlock extends LiquidBlock {
    public FuelBlock(Supplier<? extends ForgeFlowingFluid> fluid, Properties properties) {
        super(fluid, properties);
    }

    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if (!level.isClientSide && !(entity instanceof ItemEntity)) {
            entity.hurt(CIF_damage_source_Register.fuel(level), 4.0F);
            entity.setSecondsOnFire(2000);
        }
        super.entityInside(state, level, pos, entity);
    }
}
