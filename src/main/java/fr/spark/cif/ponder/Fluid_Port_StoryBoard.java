package fr.spark.cif.ponder;

import com.simibubi.create.Create;
import com.simibubi.create.foundation.ponder.PonderPalette;
import com.simibubi.create.foundation.ponder.SceneBuilder;
import com.simibubi.create.foundation.ponder.SceneBuildingUtil;
import com.simibubi.create.foundation.ponder.element.InputWindowElement;
import com.simibubi.create.foundation.utility.Pointing;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;

public class Fluid_Port_StoryBoard {

    public static void changeType(SceneBuilder scene, SceneBuildingUtil util){
        
        scene.title("cif_fluid_port_changetype", "Change the Type of Fluid Port");
        scene.showBasePlate();
        scene.idle(10);

        scene.world.showSection(util.select.cuboid(util.grid.at(1, 1, 1), new Vec3i(8, 2, 8)), Direction.DOWN);

        scene.idle(20);

        scene.overlay.showText(20)
                .independent(0)
                .text("You can change the type of a fluid port with a right-click using a wrench");

        scene.overlay.showControls(new InputWindowElement(util.vector.of(7, 2, 1), Pointing.DOWN)
                        .rightClick()
                        .withItem(new ItemStack(ForgeRegistries.ITEMS.getValue(Create.asResource("wrench")))),
                20);

        scene.idle(20);

        scene.world.hideSection(util.select.cuboid(util.grid.at(1, 1, 1), new Vec3i(8, 2, 8)), Direction.UP);

        scene.idle(20);

        //out
        scene.world.showSection(util.select.cuboid(util.grid.at(1, 1, 1), new Vec3i(6, 1, 0)), Direction.DOWN);

        scene.idle(20);

        scene.overlay.showText(50)
                .independent(0)
                .text("You can drain fluid from an output type, but you can't fill it");

        scene.idle(10);

        scene.world.setKineticSpeed(util.select.position(3, 1, 1), 64);
        scene.world.setKineticSpeed(util.select.position(5, 1, 1), 64);
        scene.world.propagatePipeChange(util.grid.at(3, 1, 1));

        scene.idle(10);

        scene.overlay.showText(40)
                .independent(0)
                .colored(PonderPalette.RED)
                .pointAt(util.vector.blockSurface(util.grid.at(1, 1, 1), Direction.UP))
                .text("Output type");

        scene.overlay.showText(40)
                .independent(0)
                .colored(PonderPalette.RED)
                .pointAt(util.vector.blockSurface(util.grid.at(7, 1, 1), Direction.UP))
                .text("Output type");

        scene.idle(40);

        scene.world.setKineticSpeed(util.select.position(3, 1, 1), 0);
        scene.world.setKineticSpeed(util.select.position(5, 1, 1), 0);

        scene.idle(10);

        scene.world.hideSection(util.select.cuboid(util.grid.at(1, 1, 1), new Vec3i(6, 1, 0)), Direction.UP);

        scene.idle(20);

        //water
        scene.world.showSection(util.select.cuboid(util.grid.at(1, 1, 4), new Vec3i(6, 1, 0)), Direction.DOWN);

        scene.idle(20);

        scene.overlay.showText(50)
                .independent(0)
                .text("You can fill fluid into a water type, but you can't drain it");

        scene.idle(10);

        scene.world.setKineticSpeed(util.select.position(3, 1, 4), 64);
        scene.world.setKineticSpeed(util.select.position(5, 1, 4), 64);
        scene.world.propagatePipeChange(util.grid.at(5, 1, 4));

        scene.idle(10);

        scene.overlay.showText(40)
                .independent(0)
                .colored(PonderPalette.RED)
                .pointAt(util.vector.blockSurface(util.grid.at(1, 1, 4), Direction.UP))
                .text("Water type");

        scene.overlay.showText(40)
                .independent(0)
                .colored(PonderPalette.RED)
                .pointAt(util.vector.blockSurface(util.grid.at(7, 1, 4), Direction.UP))
                .text("Water type");

        scene.idle(40);

        scene.world.setKineticSpeed(util.select.position(3, 1, 4), 0);
        scene.world.setKineticSpeed(util.select.position(5, 1, 4), 0);

        scene.idle(10);

        scene.world.hideSection(util.select.cuboid(util.grid.at(1, 1, 4), new Vec3i(6, 1, 0)), Direction.UP);

        scene.idle(20);

        //fuel
        scene.world.showSection(util.select.cuboid(util.grid.at(1, 1, 7), new Vec3i(6, 1, 0)), Direction.DOWN);

        scene.idle(20);

        scene.overlay.showText(50)
                .independent(0)
                .text("You can fill fluid into a fuel type, but you can't drain it");

        scene.idle(10);

        scene.world.setKineticSpeed(util.select.position(3, 1, 7), 64);
        scene.world.setKineticSpeed(util.select.position(5, 1, 7), 64);
        scene.world.propagatePipeChange(util.grid.at(5, 1, 7));

        scene.idle(10);

        scene.overlay.showText(40)
                .independent(0)
                .colored(PonderPalette.RED)
                .pointAt(util.vector.blockSurface(util.grid.at(1, 1, 7), Direction.UP))
                .text("Fuel type");

        scene.overlay.showText(40)
                .independent(0)
                .colored(PonderPalette.RED)
                .pointAt(util.vector.blockSurface(util.grid.at(7, 1, 7), Direction.SOUTH))
                .text("Fuel type");

        scene.idle(40);

        scene.world.setKineticSpeed(util.select.position(3, 1, 7), 0);
        scene.world.setKineticSpeed(util.select.position(5, 1, 7), 0);

        scene.idle(10);

        scene.world.hideSection(util.select.cuboid(util.grid.at(1, 1, 7), new Vec3i(6, 1, 0)), Direction.UP);

        scene.idle(20);
    }
}
