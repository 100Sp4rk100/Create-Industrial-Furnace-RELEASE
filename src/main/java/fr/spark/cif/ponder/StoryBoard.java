package fr.spark.cif.ponder;

import com.tterrag.registrate.util.entry.BlockEntry;
import com.tterrag.registrate.util.entry.FluidEntry;
import com.tterrag.registrate.util.entry.ItemEntry;
import com.tterrag.registrate.util.entry.RegistryEntry;
import fr.spark.cif.Cif;
import net.createmod.ponder.api.registration.PonderSceneRegistrationHelper;
import net.createmod.ponder.api.scene.PonderStoryBoard;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

public abstract class StoryBoard {
    private final ResourceLocation location;
    protected final List<RegistryEntry<?>> registry_entries = new ArrayList<>();
    protected final List<PonderStoryBoard> registry_storyboard = new ArrayList<>();

    public StoryBoard(String loc) {
        this.location = Cif.id(loc);
    }

    public void addBlock(BlockEntry block){
        registry_entries.add(block);
    }

    public void addItem(ItemEntry<?> item) {
        registry_entries.add(item);
    }

    public void addFluid(FluidEntry<?> fluid) {
        registry_entries.add(fluid);
    }

    public void addScene(PonderStoryBoard scene) {
        registry_storyboard.add(scene);
    }

    public void register(PonderSceneRegistrationHelper<ResourceLocation> helper){
        registry_entries.forEach(entry -> {
            registry_storyboard.forEach(scene -> {
                helper.addStoryBoard(entry.getId(), location, scene);
            });
        });
    }
}
