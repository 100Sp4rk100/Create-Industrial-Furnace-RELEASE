package fr.spark.cif.blocks.portable_tank.portable_tank;

import fr.spark.cif.blocks.portable_tank.AbstractPortableTankEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class PortableTankEntity extends AbstractPortableTankEntity {

    public static final int capacity = 8000;

    public PortableTankEntity(BlockEntityType<?> typeIn, BlockPos pos, BlockState state) {
        super(typeIn, pos, state);
    }

    @Override
    protected int getCapacity() {
        return capacity;
    }
}
