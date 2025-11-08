package fr.spark.cif.blocks.industrial_furnace.mechanical_port;

import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class MechanicalPortEntity extends KineticBlockEntity {
    public static final Float DEFAULT_STRESS = 32.0f;

    public MechanicalPortEntity(BlockEntityType<?> typeIn, BlockPos pos, BlockState state) {
        super(typeIn, pos, state);
    }

    @Override
    public float calculateStressApplied() {
        return DEFAULT_STRESS;
    }

    @Override
    public void tick(){
        super.tick();
    }
}
