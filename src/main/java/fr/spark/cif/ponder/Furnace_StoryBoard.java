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

public class Furnace_StoryBoard {
    public static void change_view(SceneBuilder scene, SceneBuildingUtil util){
        
        scene.title("furnace_change_view", "Change the view mod");
        scene.showBasePlate();
        scene.idle(10);

        scene.world.showSection(util.select.cuboid(util.grid.at(0, 1, 0), new Vec3i(2, 0, 2)), Direction.DOWN);

        scene.idle(10);

        scene.overlay.showText(40)
                .independent(0)
                .text("You can change the view mode with a right-click using a wrench");

        scene.overlay.showControls(new InputWindowElement(util.vector.of(1, 2, 1), Pointing.DOWN)
                        .rightClick()
                        .withItem(new ItemStack(ForgeRegistries.ITEMS.getValue(Create.asResource("wrench")))),
                40);
    }
}
