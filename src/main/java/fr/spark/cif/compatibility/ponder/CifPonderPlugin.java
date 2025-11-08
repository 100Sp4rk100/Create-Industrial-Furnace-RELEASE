package fr.spark.cif.compatibility.ponder;

import fr.spark.cif.Cif;
import fr.spark.cif.init.CIF_ponder_Register;
import net.createmod.ponder.api.registration.PonderPlugin;
import net.createmod.ponder.api.registration.PonderSceneRegistrationHelper;
import net.minecraft.resources.ResourceLocation;

public class CifPonderPlugin implements PonderPlugin {
    @Override
    public String getModId() {
        return Cif.MODID;
    }

    @Override
    public void registerScenes(PonderSceneRegistrationHelper<ResourceLocation> helper) {
        CIF_ponder_Register.registerScenes(helper);
    }
}
