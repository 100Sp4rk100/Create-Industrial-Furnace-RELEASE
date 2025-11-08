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

public class Furnace_StoryBoard extends StoryBoard {
    public Furnace_StoryBoard() {
        super("furnace/furnace");

        addScene(Furnace_StoryBoard::change_view);
    }

    private static void change_view(SceneBuilder builder, SceneBuildingUtil util){

        CreateSceneBuilder scene = new CreateSceneBuilder(builder);
        
        scene.title("furnace_change_view", "Change the view mod");
        scene.showBasePlate();
        scene.idle(10);

        scene.world().showSection(util.select().cuboid(util.grid().at(0, 1, 0), new Vec3i(2, 0, 2)), Direction.DOWN);

        scene.idle(10);

        scene.overlay().showText(40)
                .independent(0)
                .text("You can change the view mode with a right-click using a wrench");

        scene.overlay().showControls(util.vector().of(1, 2, 1), Pointing.DOWN, 40)
                .rightClick()
                .withItem(new ItemStack(ForgeRegistries.ITEMS.getValue(Create.asResource("wrench"))));
    }
}
