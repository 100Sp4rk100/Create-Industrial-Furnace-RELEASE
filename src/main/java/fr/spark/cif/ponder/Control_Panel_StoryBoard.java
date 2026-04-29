package fr.spark.cif.ponder;

import com.simibubi.create.Create;
import com.simibubi.create.foundation.ponder.SceneBuilder;
import com.simibubi.create.foundation.ponder.SceneBuildingUtil;
import com.simibubi.create.foundation.ponder.element.InputWindowElement;
import com.simibubi.create.foundation.utility.Pointing;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;

public class Control_Panel_StoryBoard {

    public static void usage(SceneBuilder scene, SceneBuildingUtil util){

        scene.title("control_panel_usage", "Usage of the Control Panel");
        scene.showBasePlate();
        scene.idle(10);

        scene.world.showSection(util.select.cuboid(util.grid.at(1, 1, 2), new Vec3i(4, 2, 3)), Direction.DOWN);

        scene.idle(20);

        scene.rotateCameraY(-90);

        scene.idle(20);

        scene.overlay.showText(40)
                .independent(0)
                .text("The control panel allows you to view the contents of the furnace slots");
    }

    public static void changeView(SceneBuilder scene, SceneBuildingUtil util){

        scene.title("control_panel_change_view", "Change the view of the control panel");
        scene.showBasePlate();
        scene.idle(10);

        scene.world.showSection(util.select.cuboid(util.grid.at(1, 1, 2), new Vec3i(4, 2, 3)), Direction.DOWN);

        scene.idle(20);

        scene.rotateCameraY(-90);

        scene.idle(20);

        scene.overlay.showText(40)
                .independent(0)
                .text("To change the view type (input slots, output slots, or empty), right-click the control panel with a wrench");

        scene.overlay.showControls(new InputWindowElement(util.vector.of(3, 3, 4), Pointing.DOWN)
                        .rightClick()
                        .withItem(new ItemStack(ForgeRegistries.ITEMS.getValue(Create.asResource("wrench")))),
                40);
    }
}
