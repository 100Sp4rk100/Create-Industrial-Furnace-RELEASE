package fr.spark.cif.ponder;

import com.simibubi.create.content.kinetics.mechanicalArm.ArmBlockEntity;
import com.simibubi.create.foundation.ponder.SceneBuilder;
import com.simibubi.create.foundation.ponder.SceneBuildingUtil;
import com.simibubi.create.foundation.ponder.element.InputWindowElement;
import com.simibubi.create.foundation.utility.Pointing;
import fr.spark.cif.init.CIF_items_Register;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.world.item.ItemStack;

public class Chemical_Cleaner_StoryBoard {

    public static void use(SceneBuilder scene, SceneBuildingUtil util) {

        scene.title("cif_chemical_cleaner_use", "Using the Chemical Cleaner");
        scene.showBasePlate();
        scene.idle(10);

        scene.world.showSection(util.select.cuboid(util.grid.at(0, 1, 0), new Vec3i(8, 3, 8)), Direction.DOWN);

        scene.idle(10);

        scene.overlay.showText(40)
                .independent(0)
                .text("The chemical cleaner transforms hot water into regular water");

        scene.idle(10);

        scene.world.setKineticSpeed(util.select.position(3, 1, 3), 64);
        scene.world.setKineticSpeed(util.select.position(3, 1, 4), 64);
        scene.world.setKineticSpeed(util.select.position(5, 1, 3), 64);
        scene.world.setKineticSpeed(util.select.position(5, 1, 4), 64);

        scene.idle(10);

        scene.world.propagatePipeChange(util.grid.at(3, 1, 3));
        scene.world.propagatePipeChange(util.grid.at(3, 1, 4));
        scene.world.propagatePipeChange(util.grid.at(5, 1, 3));
        scene.world.propagatePipeChange(util.grid.at(5, 1, 4));

        scene.idle(30);

        scene.overlay.showText(40)
                .independent(0)
                .text("It consumes activated carbon");

        scene.idle(10);

        scene.world.setKineticSpeed(util.select.position(4, 1, 0), 64);
        scene.world.setKineticSpeed(util.select.position(4, 1, 7), 64);

        scene.world.instructArm(
                util.grid.at(4, 1, 0),
                ArmBlockEntity.Phase.MOVE_TO_INPUT,
                ItemStack.EMPTY,
                0
        );

        scene.world.instructArm(
                util.grid.at(4, 1, 7),
                ArmBlockEntity.Phase.MOVE_TO_INPUT,
                ItemStack.EMPTY,
                0
        );

        scene.idle(20);

        scene.world.instructArm(
                util.grid.at(4, 1, 0),
                ArmBlockEntity.Phase.MOVE_TO_OUTPUT,
                CIF_items_Register.ACTIVATED_CARBON.get().getDefaultInstance(),
                0
        );

        scene.world.instructArm(
                util.grid.at(4, 1, 7),
                ArmBlockEntity.Phase.MOVE_TO_OUTPUT,
                CIF_items_Register.ACTIVATED_CARBON.get().getDefaultInstance(),
                0
        );

        scene.idle(20);

        scene.world.instructArm(
                util.grid.at(4, 1, 0),
                ArmBlockEntity.Phase.SEARCH_INPUTS,
                ItemStack.EMPTY,
                0
        );

        scene.world.instructArm(
                util.grid.at(4, 1, 7),
                ArmBlockEntity.Phase.SEARCH_INPUTS,
                ItemStack.EMPTY,
                0
        );

        scene.overlay.showText(40)
                .independent(0)
                .text("You lose 30% of the water");

        scene.idle(50);

        scene.overlay.showText(40)
                .independent(0)
                .text("You can insert activated carbon into the chemical cleaner with a right-click");

        scene.overlay.showControls(new InputWindowElement(util.vector.of(4, 2, 4), Pointing.DOWN)
                .rightClick()
                .withItem(CIF_items_Register.ACTIVATED_CARBON.asStack()),
        40);

        scene.idle(50);

        scene.overlay.showText(40)
                .independent(0)
                .text("You can remove activated carbon from the chemical cleaner with shift + right-click");

        scene.overlay.showControls(new InputWindowElement(util.vector.of(4, 2, 4), Pointing.DOWN)
                .whileSneaking()
                .rightClick(),
                40);

        scene.idle(50);

        scene.overlay.showText(40)
                .independent(0)
                .text("The output side has red pixels, and the input side has blue ones");

        scene.idle(50);

        scene.world.hideSection(util.select.cuboid(util.grid.at(0, 1, 0), new Vec3i(8, 3, 8)), Direction.UP);
    }
}
