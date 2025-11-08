package fr.spark.cif.blocks.portable_tank.advanced_portable_tank;

import fr.spark.cif.blocks.portable_tank.AbstractPortableTankItem;
import net.minecraft.world.level.block.Block;

public class AdvancedPortableTankitem extends AbstractPortableTankItem {

    public AdvancedPortableTankitem(Block block, Properties properties) {
        super(block, properties);
    }

    @Override
    protected int getCapacity() {
        return AdvancedPortableTankEntity.capacity;
    }
}
