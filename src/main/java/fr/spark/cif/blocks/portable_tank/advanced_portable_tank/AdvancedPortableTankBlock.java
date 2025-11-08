package fr.spark.cif.blocks.portable_tank.advanced_portable_tank;

import com.tterrag.registrate.util.entry.BlockEntityEntry;
import fr.spark.cif.blocks.portable_tank.AbstractPortableTankBlock;
import fr.spark.cif.blocks.portable_tank.AbstractPortableTankEntity;
import fr.spark.cif.init.CIF_entity_blocks_Register;
import org.jetbrains.annotations.Nullable;

public class AdvancedPortableTankBlock extends AbstractPortableTankBlock {

    public AdvancedPortableTankBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected @Nullable BlockEntityEntry<? extends AbstractPortableTankEntity> getEntry() {
        return CIF_entity_blocks_Register.ADVANCED_PORTABLE_TANK_ENTITY;
    }
}
