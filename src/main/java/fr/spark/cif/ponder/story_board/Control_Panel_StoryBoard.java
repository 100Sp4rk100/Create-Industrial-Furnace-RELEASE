package fr.spark.cif.ponder.story_board;

import com.simibubi.create.Create;
import com.simibubi.create.foundation.ponder.CreateSceneBuilder;
import fr.spark.cif.ponder.StoryBoard;
import net.createmod.catnip.math.Pointing;
import net.createmod.ponder.api.scene.SceneBuilder;
import net.createmod.ponder.api.scene.SceneBuildingUtil;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;

public class Control_Panel_StoryBoard extends StoryBoard {
    public Control_Panel_StoryBoard() {
        super("control_panel/control_panel_ponder");

        addScene(Control_Panel_StoryBoard::usage);
        addScene(Control_Panel_StoryBoard::changeView);
    }

    private static void usage(SceneBuilder builder, SceneBuildingUtil util){

        CreateSceneBuilder scene = new CreateSceneBuilder(builder);

        scene.title("control_panel_usage", "Usage of the Control Panel");
        scene.showBasePlate();
        scene.idle(10);

        scene.world().showSection(util.select().cuboid(util.grid().at(1, 1, 2), new Vec3i(4, 2, 3)), Direction.DOWN);

        scene.idle(20);

        scene.rotateCameraY(-90);

        scene.idle(20);

        scene.overlay().showText(40)
                .independent(0)
                .text("The control panel allows you to view the contents of the furnace slots");
    }

    private static void changeView(SceneBuilder builder, SceneBuildingUtil util){

        CreateSceneBuilder scene = new CreateSceneBuilder(builder);

        scene.title("control_panel_change_view", "Change the view of the control panel");
        scene.showBasePlate();
        scene.idle(10);

        scene.world().showSection(util.select().cuboid(util.grid().at(1, 1, 2), new Vec3i(4, 2, 3)), Direction.DOWN);

        scene.idle(20);

        scene.rotateCameraY(-90);

        scene.idle(20);

        scene.overlay().showText(40)
                .independent(0)
                .text("To change the view type (input slots, output slots, or empty), right-click the control panel with a wrench");

        scene.overlay().showControls(util.vector().of(3, 3, 4), Pointing.DOWN, 40)
                .rightClick()
                .withItem(new ItemStack(ForgeRegistries.ITEMS.getValue(Create.asResource("wrench"))));
    }
}
