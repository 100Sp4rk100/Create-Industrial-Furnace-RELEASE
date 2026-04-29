package fr.spark.cif.ponder;

import com.simibubi.create.AllItems;
import com.simibubi.create.content.kinetics.mechanicalArm.ArmBlockEntity;
import com.simibubi.create.foundation.ponder.*;
import com.simibubi.create.foundation.ponder.element.InputWindowElement;
import com.simibubi.create.foundation.utility.Pointing;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class Industrial_Furnace_StoryBoard {
    public static void build(SceneBuilder scene, SceneBuildingUtil util) {
        
        scene.title("cif_furnace_ponder_build", "Build the Industrial Furnace");
        scene.showBasePlate();
        scene.idle(10);
        scene.world.showSection(util.select.cuboid(util.grid.at(3, 1, 3), new Vec3i(2, 2, 2)), Direction.DOWN);
        scene.idle(10);
        scene.overlay.showText(20)
                .independent(0)
                .text("The industrial furnace is a 3x3x3 block structure");
        scene.idle(20);
        scene.world.hideSection(util.select.cuboid(util.grid.at(3, 1, 3), new Vec3i(2, 2, 2)), Direction.UP);

        //1
        scene.idle(20);
        scene.world.showSection(util.select.cuboid(util.grid.at(3, 1, 3), new Vec3i(2, 0, 2)), Direction.DOWN);
        scene.idle(10);
        scene.overlay.showText(80)
                .independent(0)
                .text("At the bottom, 6 fluid ports");

        scene.overlay.showText(20)
                .independent(0)
                .colored(PonderPalette.RED)
                .pointAt(util.vector.blockSurface(util.grid.at(4, 2, 2), Direction.UP))
                .text("2 fuel types");
        scene.idle(30);
        scene.overlay.showText(20)
                .independent(100)
                .colored(PonderPalette.RED)
                .pointAt(util.vector.blockSurface(util.grid.at(2, 2, 3), Direction.UP))
                .text("2 output types");
        scene.idle(30);
        scene.overlay.showText(20)
                .independent(200)
                .colored(PonderPalette.RED)
                .pointAt(util.vector.blockSurface(util.grid.at(2, 2, 2), Direction.UP))
                .text("2 water types");
        scene.idle(30);
        scene.overlay.showText(20)
                .independent(0)
                .text("And 3 diamond alloy blocks");

        scene.idle(20);

        scene.rotateCameraY(360);

        //2
        scene.idle(60);
        scene.world.showSection(util.select.cuboid(util.grid.at(3, 2, 3), new Vec3i(2, 0, 2)), Direction.DOWN);
        scene.idle(20);
        scene.overlay.showText(50)
                .independent(0)
                .text("In the middle, 1 furnace on each side");

        scene.overlay.showText(20)
                .independent(0)
                .colored(PonderPalette.RED)
                .pointAt(util.vector.blockSurface(util.grid.at(2, 3, 3), Direction.WEST))
                .text("4 furnaces");
        scene.idle(30);
        scene.overlay.showText(20)
                .independent(0)
                .colored(PonderPalette.RED)
                .pointAt(util.vector.blockSurface(util.grid.at(3, 3, 3), Direction.UP))
                .text("1 controller");
        scene.idle(30);
        scene.overlay.showText(20)
                .independent(0)
                .colored(PonderPalette.RED)
                .text("And 4 diamond alloy blocks");

        scene.idle(20);

        scene.rotateCameraY(360);

        //3
        scene.idle(60);
        scene.world.showSection(util.select.cuboid(util.grid.at(3, 3, 3), new Vec3i(2, 0, 2)), Direction.DOWN);
        scene.idle(20);
        scene.overlay.showText(30)
                .independent(0)
                .text("On top, 8 diamond alloy blocks");

        scene.idle(30);
        scene.overlay.showText(20)
                .independent(0)
                .colored(PonderPalette.RED)
                .pointAt(util.vector.blockSurface(util.grid.at(3, 4, 3), Direction.UP))
                .text("And 1 mechanical port");
    }

    public static void connection(SceneBuilder scene, SceneBuildingUtil util) {

        scene.title("cif_furnace_ponder_connection", "Connect the Industrial Furnace");
        scene.showBasePlate();
        scene.idle(10);
        scene.world.showSection(util.select.cuboid(util.grid.at(3, 1, 3), new Vec3i(2, 2, 2)), Direction.DOWN);
        scene.idle(20);

        //water
        scene.world.showSection(util.select.cuboid(util.grid.at(2, 1, 0), new Vec3i(1, 1, 2)), Direction.SOUTH);
        scene.world.showSection(util.select.cuboid(util.grid.at(5, 1, 6), new Vec3i(1, 1, 2)), Direction.NORTH);

        scene.idle(20);

        scene.overlay.showText(20)
                .independent(0)
                .colored(PonderPalette.RED)
                .pointAt(util.vector.blockSurface(util.grid.at(3, 1, 1), Direction.UP))
                .text("Fill water into the water type ports");

        scene.idle(10);

        scene.world.setKineticSpeed(util.select.position(3, 1, 1), 64);
        scene.world.setKineticSpeed(util.select.position(5, 1, 7), 64);

        scene.idle(10);

        scene.world.propagatePipeChange(util.grid.at(3, 1, 1));
        scene.world.propagatePipeChange(util.grid.at(5, 1, 7));

        scene.idle(20);
        scene.rotateCameraY(180);
        scene.idle(40);
        scene.rotateCameraY(180);
        scene.idle(30);

        scene.world.hideSection(util.select.cuboid(util.grid.at(2, 1, 0), new Vec3i(1, 1, 2)), Direction.NORTH);
        scene.world.hideSection(util.select.cuboid(util.grid.at(5, 1, 6), new Vec3i(1, 1, 2)), Direction.SOUTH);

        scene.idle(20);

        //fuel
        scene.world.showSection(util.select.cuboid(util.grid.at(5, 1, 0), new Vec3i(1, 1, 2)), Direction.SOUTH);
        scene.world.showSection(util.select.cuboid(util.grid.at(2, 1, 6), new Vec3i(1, 1, 2)), Direction.NORTH);

        scene.idle(20);

        scene.overlay.showText(20)
                .independent(0)
                .colored(PonderPalette.RED)
                .pointAt(util.vector.blockSurface(util.grid.at(5, 1, 1), Direction.UP))
                .text("Fill fuel into the fuel type ports");

        scene.idle(10);

        scene.world.setKineticSpeed(util.select.position(5, 1, 1), 64);
        scene.world.setKineticSpeed(util.select.position(3, 1, 7), 64);

        scene.idle(10);

        scene.world.propagatePipeChange(util.grid.at(5, 1, 1));
        scene.world.propagatePipeChange(util.grid.at(3, 1, 7));

        scene.idle(20);
        scene.rotateCameraY(180);
        scene.idle(40);
        scene.rotateCameraY(180);
        scene.idle(30);

        scene.world.hideSection(util.select.cuboid(util.grid.at(5, 1, 0), new Vec3i(1, 1, 2)), Direction.NORTH);
        scene.world.hideSection(util.select.cuboid(util.grid.at(2, 1, 6), new Vec3i(1, 1, 2)), Direction.SOUTH);

        scene.idle(20);

        //out
        scene.world.showSection(util.select.cuboid(util.grid.at(0, 1, 4), new Vec3i(2, 1, 1)), Direction.EAST);
        scene.world.showSection(util.select.cuboid(util.grid.at(6, 1, 4), new Vec3i(2, 1, 1)), Direction.WEST);

        scene.idle(20);

        scene.overlay.showText(20)
                .independent(0)
                .colored(PonderPalette.RED)
                .pointAt(util.vector.blockSurface(util.grid.at(1, 1, 4), Direction.UP))
                .text("Drain hot water from the output type ports");

        scene.idle(10);

        scene.world.setKineticSpeed(util.select.position(1, 1, 4), 64);
        scene.world.setKineticSpeed(util.select.position(7, 1, 4), 64);

        scene.idle(10);

        scene.world.propagatePipeChange(util.grid.at(1, 1, 4));
        scene.world.propagatePipeChange(util.grid.at(7, 1, 4));

        scene.idle(20);
        scene.rotateCameraY(180);
        scene.idle(40);
        scene.rotateCameraY(180);
        scene.idle(30);

        scene.world.hideSection(util.select.cuboid(util.grid.at(0, 1, 4), new Vec3i(2, 1, 1)), Direction.WEST);
        scene.world.hideSection(util.select.cuboid(util.grid.at(6, 1, 4), new Vec3i(2, 1, 1)), Direction.EAST);

        scene.idle(20);

        //kinetic
        scene.world.showSection(util.select.cuboid(util.grid.at(4, 1, 8), new Vec3i(0, 5, 0)), Direction.NORTH);
        scene.world.showSection(util.select.cuboid(util.grid.at(4, 4, 4), new Vec3i(0, 1, 4)), Direction.NORTH);

        scene.idle(20);

        scene.overlay.showText(20)
                .independent(0)
                .colored(PonderPalette.RED)
                .pointAt(util.vector.blockSurface(util.grid.at(4, 3, 4), Direction.UP))
                .text("Connect the mechanical port to a rotational source");

        scene.idle(10);

        scene.world.setKineticSpeed(util.select.cuboid(util.grid.at(4, 0, 8), new Vec3i(0, 6, 0)), 100);
        scene.world.setKineticSpeed(util.select.cuboid(util.grid.at(4, 4, 4), new Vec3i(0, 1, 4)), 100);

        scene.idle(40);

        scene.rotateCameraY(360);

        scene.idle(40);

        scene.overlay.showText(30)
                .independent(0)
                .text("It must be fast enough");
    }

    public static void use(SceneBuilder scene, SceneBuildingUtil util) {

        scene.title("cif_furnace_ponder_use", "Use the Industrial Furnace");
        scene.showBasePlate();
        scene.idle(10);

        scene.world.showSection(util.select.cuboid(util.grid.at(3, 1, 3), new Vec3i(2, 2, 2)), Direction.DOWN);
        scene.world.showSection(util.select.cuboid(util.grid.at(2, 1, 0), new Vec3i(1, 1, 2)), Direction.SOUTH);
        scene.world.showSection(util.select.cuboid(util.grid.at(5, 1, 6), new Vec3i(1, 1, 2)), Direction.NORTH);
        scene.world.showSection(util.select.cuboid(util.grid.at(5, 1, 0), new Vec3i(1, 1, 2)), Direction.SOUTH);
        scene.world.showSection(util.select.cuboid(util.grid.at(2, 1, 6), new Vec3i(1, 1, 2)), Direction.NORTH);
        scene.world.showSection(util.select.cuboid(util.grid.at(0, 1, 4), new Vec3i(2, 1, 1)), Direction.EAST);
        scene.world.showSection(util.select.cuboid(util.grid.at(6, 1, 4), new Vec3i(2, 1, 1)), Direction.WEST);
        scene.world.showSection(util.select.cuboid(util.grid.at(4, 1, 8), new Vec3i(0, 5, 0)), Direction.NORTH);
        scene.world.showSection(util.select.cuboid(util.grid.at(4, 4, 4), new Vec3i(0, 1, 4)), Direction.NORTH);

        scene.idle(10);

        scene.world.setKineticSpeed(util.select.position(3, 1, 1), 64);
        scene.world.setKineticSpeed(util.select.position(5, 1, 7), 64);
        scene.world.setKineticSpeed(util.select.position(5, 1, 1), 64);
        scene.world.setKineticSpeed(util.select.position(3, 1, 7), 64);
        scene.world.setKineticSpeed(util.select.position(1, 1, 4), 64);
        scene.world.setKineticSpeed(util.select.position(7, 1, 4), 64);
        scene.world.setKineticSpeed(util.select.cuboid(util.grid.at(4, 0, 8), new Vec3i(0, 6, 0)), 100);
        scene.world.setKineticSpeed(util.select.cuboid(util.grid.at(4, 4, 4), new Vec3i(0, 1, 4)), 100);

        scene.idle(10);

        scene.world.propagatePipeChange(util.grid.at(3, 1, 1));
        scene.world.propagatePipeChange(util.grid.at(5, 1, 7));
        scene.world.propagatePipeChange(util.grid.at(5, 1, 1));
        scene.world.propagatePipeChange(util.grid.at(3, 1, 7));
        scene.world.propagatePipeChange(util.grid.at(1, 1, 4));
        scene.world.propagatePipeChange(util.grid.at(7, 1, 4));

        scene.idle(10);

        scene.overlay.showText(80)
                .independent(0)
                .text("Right-click on a furnace while holding an item that can be smelted");

        scene.overlay.showControls(new InputWindowElement(util.vector.of(5, 2, 4), Pointing.LEFT)
                        .rightClick()
                        .withItem(new ItemStack(Items.IRON_INGOT)),
                40);

        scene.idle(90);

        scene.overlay.showText(80)
                .independent(0)
                .text("The item is smelted 4 times faster");

        scene.idle(90);

        scene.overlay.showText(80)
                .independent(0)
                .text("Shift right-click again to get the result");

        scene.overlay.showControls(new InputWindowElement(util.vector.of(5, 2, 4), Pointing.LEFT)
                        .whileSneaking()
                        .rightClick(),
                40);

        scene.idle(90);

        scene.overlay.showText(80)
                .independent(0)
                .text("This consumes fuel and water, and returns hot water");

        scene.idle(90);

        scene.overlay.showText(80)
                .independent(0)
                .text("You must preheat the furnace before using it");

        scene.idle(90);
    }

    public static void automate(SceneBuilder scene, SceneBuildingUtil util){

        scene.title("cif_furnace_ponder_automate", "Automate the Industrial Furnace");
        scene.showBasePlate();
        scene.idle(10);

        scene.world.showSection(util.select.cuboid(util.grid.at(0, 1, 0), new Vec3i(8, 5, 8))
                .substract(util.select.cuboid(util.grid.at(2, 2, 3), new Vec3i(0, 1, 0))), Direction.DOWN);

        scene.idle(10);

        scene.overlay.showText(20)
                .independent(0)
                .text("You can automate the Industrial Furnace");

        scene.idle(20);

        scene.world.setKineticSpeed(util.select.position(3, 1, 1), 64);
        scene.world.setKineticSpeed(util.select.position(5, 1, 7), 64);
        scene.world.setKineticSpeed(util.select.position(5, 1, 1), 64);
        scene.world.setKineticSpeed(util.select.position(3, 1, 7), 64);
        scene.world.setKineticSpeed(util.select.position(1, 1, 4), 64);
        scene.world.setKineticSpeed(util.select.position(7, 1, 4), 64);
        scene.world.setKineticSpeed(util.select.cuboid(util.grid.at(4, 0, 8), new Vec3i(0, 6, 0)), 100);
        scene.world.setKineticSpeed(util.select.cuboid(util.grid.at(4, 4, 4), new Vec3i(0, 1, 4)), 100);

        scene.idle(10);

        scene.world.propagatePipeChange(util.grid.at(3, 1, 1));
        scene.world.propagatePipeChange(util.grid.at(5, 1, 7));
        scene.world.propagatePipeChange(util.grid.at(5, 1, 1));
        scene.world.propagatePipeChange(util.grid.at(3, 1, 7));
        scene.world.propagatePipeChange(util.grid.at(1, 1, 4));
        scene.world.propagatePipeChange(util.grid.at(7, 1, 4));

        scene.idle(10);

        scene.world.setKineticSpeed(util.select.cuboid(util.grid.at(4, 1, 0), new Vec3i(0, 0, 1)), 64);
        scene.world.setKineticSpeed(util.select.cuboid(util.grid.at(0, 1, 6), new Vec3i(1, 0, 0)), 64);

        scene.overlay.showText(20)
                .independent(0)
                .colored(PonderPalette.BLUE)
                .pointAt(util.vector.blockSurface(util.grid.at(4, 2, 2), Direction.UP))
                .text("You can interact with the funnel or with a hopper");

        scene.idle(20);

        arm(scene, util);
        scene.idle(20);
        arm(scene, util);
        scene.idle(20);
        arm(scene, util);
        scene.idle(20);
        arm(scene, util);
        scene.idle(20);

        scene.rotateCameraY(180);

        arm(scene, util);
        scene.idle(20);
        arm(scene, util);
        scene.idle(20);
        arm(scene, util);
        scene.idle(20);
        arm(scene, util);
        scene.idle(20);

        scene.world.instructArm(
                util.grid.at(4, 1, 0),
                ArmBlockEntity.Phase.SEARCH_INPUTS,
                ItemStack.EMPTY,
                0
        );

        scene.world.instructArm(
                util.grid.at(1, 1, 6),
                ArmBlockEntity.Phase.SEARCH_INPUTS,
                ItemStack.EMPTY,
                0
        );

        scene.idle(20);

        scene.rotateCameraY(180);

    }

    private static void arm(SceneBuilder scene, SceneBuildingUtil util) {

        scene.world.instructArm(
                util.grid.at(4, 1, 0),
                ArmBlockEntity.Phase.MOVE_TO_INPUT,
                ItemStack.EMPTY,
                0
        );

        scene.world.instructArm(
                util.grid.at(1, 1, 6),
                ArmBlockEntity.Phase.MOVE_TO_INPUT,
                ItemStack.EMPTY,
                0
        );

        scene.world.instructArm(
                util.grid.at(4, 1, 0),
                ArmBlockEntity.Phase.MOVE_TO_OUTPUT,
                AllItems.CRUSHED_GOLD.asStack(1),
                0
        );

        scene.world.instructArm(
                util.grid.at(1, 1, 6),
                ArmBlockEntity.Phase.MOVE_TO_OUTPUT,
                new ItemStack(Items.GOLD_INGOT, 1),
                0
        );

        scene.idle(20);

        scene.world.instructArm(
                util.grid.at(4, 1, 0),
                ArmBlockEntity.Phase.MOVE_TO_INPUT,
                ItemStack.EMPTY,
                1
        );

        scene.world.instructArm(
                util.grid.at(1, 1, 6),
                ArmBlockEntity.Phase.MOVE_TO_INPUT,
                ItemStack.EMPTY,
                0
        );

        scene.world.instructArm(
                util.grid.at(4, 1, 0),
                ArmBlockEntity.Phase.MOVE_TO_OUTPUT,
                AllItems.CRUSHED_ZINC.asStack(1),
                0
        );

        scene.world.instructArm(
                util.grid.at(1, 1, 6),
                ArmBlockEntity.Phase.MOVE_TO_OUTPUT,
                AllItems.ZINC_INGOT.asStack(1),
                0
        );
    }

    public static void utils(SceneBuilder scene, SceneBuildingUtil util) {

        scene.title("cif_furnace_ponder_utils", "Utils about the Industrial Furnace");
        scene.showBasePlate();
        scene.idle(10);

        scene.world.showSection(util.select.cuboid(util.grid.at(0, 1, 0), new Vec3i(8, 5, 8)), Direction.DOWN);

        scene.idle(10);

        scene.overlay.showText(40)
                .independent(0)
                .pointAt(util.vector.blockSurface(util.grid.at(2, 3, 3), Direction.UP))
                .text("Use a bell to hear a sound signal when an item is smelted or when the industrial furnace is at risk of exploding");

        scene.idle(50);

        scene.overlay.showText(40)
                .independent(0)
                .pointAt(util.vector.blockSurface(util.grid.at(2, 2, 3), Direction.UP))
                .text("Use redstone power to start the industrial furnace (preheating)");

        scene.idle(50);

        scene.overlay.showText(40)
                .independent(0)
                .text("If you don't drain the hot water or don't fill it with water, the industrial furnace will explode");
    }
}