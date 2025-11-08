package fr.spark.cif.sounds;

import fr.spark.cif.init.CIF_sound_Event_Register;
import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;

public class AlertExplosion extends AbstractTickableSoundInstance {

    public AlertExplosion(int x, int y, int z){
        super(CIF_sound_Event_Register.ALERT_EXPLOSION.get(),
                SoundSource.BLOCKS,
                RandomSource.create()
        );
        this.looping = true;
        this.volume = 1.0f;
        this.x = x;
        this.y = y;
        this.z = z;
        this.attenuation = Attenuation.LINEAR;
    }

    @Override
    public void tick() {}

    @Override
    public boolean canStartSilent() {
        return false;
    }

    @Override
    public boolean isLooping() {
        return true;
    }
}
