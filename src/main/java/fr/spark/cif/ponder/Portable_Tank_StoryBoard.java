package fr.spark.cif.ponder;

import com.simibubi.create.content.fluids.spout.SpoutBlockEntity;
import com.simibubi.create.foundation.ponder.SceneBuilder;
import com.simibubi.create.foundation.ponder.SceneBuildingUtil;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;

public class Portable_Tank_StoryBoard {

    public static void use(SceneBuilder scene, SceneBuildingUtil util){
        
        scene.title("portable_tank_use", "Use a Portable Tank");
        scene.showBasePlate();
        scene.idle(10);

        scene.world.showSection(util.select.cuboid(util.grid.at(0, 1, 0), new Vec3i(8, 3, 8)), Direction.DOWN);

        scene.idle(10);

        scene.overlay.showText(20)
                .independent(0)
                .text("You can put all types of fluid in a portable tank");

        scene.idle(30);

        scene.overlay.showText(20)
                .independent(0)
                .text("The fluid is kept when broken");

        scene.idle(30);

        scene.world.hideSection(util.select.cuboid(util.grid.at(0, 1, 0), new Vec3i(8, 3, 8)), Direction.UP);

        scene.idle(30);

        //spout
        scene.world.showSection(util.select.cuboid(util.grid.at(4, 1, 1), new Vec3i(3, 3, 0)), Direction.DOWN);

        scene.idle(10);

        scene.overlay.showText(20)
                .independent(0)
                .pointAt(util.vector.blockSurface(util.grid.at(4, 3, 1), Direction.UP))
                .text("A portable tank can be filled by a spout");

        scene.idle(10);

        scene.world.setKineticSpeed(util.select.position(6, 1, 1), 64);
        scene.idle(10);
        scene.world.propagatePipeChange(util.grid.at(6, 1, 1));
        scene.idle(60);
        scene.world.modifyBlockEntityNBT(util.select.position(4, 3, 1), SpoutBlockEntity.class, nbt -> nbt.putInt("ProcessingTicks", 20));

        scene.idle(30);

        scene.world.hideSection(util.select.cuboid(util.grid.at(4, 1, 1), new Vec3i(3, 3, 0)), Direction.UP);

        scene.idle(20);

        //item drain
        scene.world.showSection(util.select.cuboid(util.grid.at(1, 1, 4), new Vec3i(5, 2, 0)), Direction.DOWN);

        scene.idle(10);

        scene.overlay.showText(20)
                .independent(0)
                .pointAt(util.vector.blockSurface(util.grid.at(4, 1, 4), Direction.UP))
                .text("A portable tank can be drained by the item drain");

        scene.idle(10);

        scene.world.setKineticSpeed(util.select.position(5, 1, 4), 64);
        scene.world.setKineticSpeed(util.select.position(6, 1, 4), 64);
        scene.world.setKineticSpeed(util.select.position(3, 1, 4), 64);
        scene.idle(10);
        scene.world.propagatePipeChange(util.grid.at(3, 1, 4));

        scene.idle(40);

        scene.world.hideSection(util.select.cuboid(util.grid.at(1, 1, 4), new Vec3i(5, 2, 0)), Direction.UP);

        scene.idle(20);

        //block
        scene.world.showSection(util.select.cuboid(util.grid.at(1, 1, 7), new Vec3i(6, 2, 0)), Direction.DOWN);

        scene.idle(10);

        scene.overlay.showText(20)
                .independent(0)
                .pointAt(util.vector.blockSurface(util.grid.at(5, 1, 7), Direction.UP))
                .text("You can fill a portable tank with a mechanical pump");

        scene.idle(10);

        scene.world.setKineticSpeed(util.select.position(5, 1, 7), 64);
        scene.idle(10);
        scene.world.propagatePipeChange(util.grid.at(5, 1, 7));

        scene.idle(40);

        scene.overlay.showText(20)
                .independent(0)
                .pointAt(util.vector.blockSurface(util.grid.at(3, 1, 7), Direction.UP))
                .text("You can drain a portable tank with a mechanical pump");

        scene.idle(10);

        scene.world.setKineticSpeed(util.select.position(3, 1, 7), 64);
        scene.idle(10);
        scene.world.propagatePipeChange(util.grid.at(3, 1, 7));

        scene.idle(40);

        scene.world.setKineticSpeed(util.select.position(5, 1, 7), 0);
        scene.world.setKineticSpeed(util.select.position(3, 1, 7), 0);

        scene.idle(10);

        scene.world.hideSection(util.select.cuboid(util.grid.at(1, 1, 7), new Vec3i(6, 2, 0)), Direction.UP);
    }
}
