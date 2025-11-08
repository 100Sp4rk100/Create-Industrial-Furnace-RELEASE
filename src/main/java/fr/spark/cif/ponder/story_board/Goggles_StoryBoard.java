package fr.spark.cif.ponder.story_board;

import com.simibubi.create.foundation.ponder.CreateSceneBuilder;
import fr.spark.cif.ponder.StoryBoard;
import net.createmod.ponder.api.scene.SceneBuilder;
import net.createmod.ponder.api.scene.SceneBuildingUtil;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;

public class Goggles_StoryBoard extends StoryBoard {
    public Goggles_StoryBoard() {
        super("goggles/goggles_ponder");

        addScene(Goggles_StoryBoard::about);
    }

    private static void about(SceneBuilder builder, SceneBuildingUtil util) {

        CreateSceneBuilder scene = new CreateSceneBuilder(builder);
        
        scene.title("cif_goggles_about", "About Goggles");
        scene.showBasePlate();
        scene.idle(10);

        scene.world().showSection(util.select().cuboid(util.grid().at(0, 1, 0), new Vec3i(8, 2, 8)), Direction.DOWN);

        scene.idle(10);

        scene.overlay().showText(40)
                .independent(0)
                .text("All block information from the mod can be viewed with goggles");

        scene.idle(50);

        scene.overlay().showText(40)
                .independent(0)
                .pointAt(util.vector().blockSurface(util.grid().at(2, 1, 2), Direction.UP))
                .text("Kinetic information");

        scene.idle(50);

        scene.overlay().showText(40)
                .independent(0)
                .pointAt(util.vector().blockSurface(util.grid().at(6, 1, 2), Direction.UP))
                .text("Current Fluid, Capacity, Current Type");

        scene.idle(50);

        scene.overlay().showText(40)
                .independent(0)
                .pointAt(util.vector().blockSurface(util.grid().at(6, 1, 6), Direction.UP))
                .text("Current Fluid, Capacity, Current Item");

        scene.idle(50);

        scene.overlay().showText(40)
                .independent(0)
                .pointAt(util.vector().blockSurface(util.grid().at(2, 1, 6), Direction.UP))
                .text("Current Type, information about the type");

        scene.idle(50);

        scene.overlay().showText(40)
                .independent(0)
                .pointAt(util.vector().blockSurface(util.grid().at(3, 1, 4), Direction.UP))
                .text("Current Fluid, Capacity");

        scene.idle(50);

        scene.overlay().showText(40)
                .independent(0)
                .pointAt(util.vector().blockSurface(util.grid().at(5, 1, 4), Direction.UP))
                .text("Current Fluid, Capacity");

        scene.idle(50);

        scene.world().hideSection(util.select().cuboid(util.grid().at(0, 1, 0), new Vec3i(8, 2, 8)), Direction.UP);
    }
}
