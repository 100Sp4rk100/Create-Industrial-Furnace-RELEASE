package fr.spark.cif.blocks.portable_tank.portable_tank;

import fr.spark.cif.blocks.portable_tank.AbstractPortableTankItem;
import net.minecraft.world.level.block.Block;

public class PortableTankItem extends AbstractPortableTankItem {

    public PortableTankItem(Block block, Properties properties) {
        super(block, properties);
    }

    @Override
    protected int getCapacity() {
        return PortableTankEntity.capacity;
    }
}
