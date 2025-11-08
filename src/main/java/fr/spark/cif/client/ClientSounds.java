package fr.spark.cif.client;

import fr.spark.cif.sounds.AlertExplosion;
import net.minecraft.client.Minecraft;

public class ClientSounds {
    public static AlertExplosion playAlertSound(int x, int y, int z) {
        AlertExplosion sound = new AlertExplosion(x, y, z);
        Minecraft.getInstance().getSoundManager().play(sound);
        return sound;
    }

    public static void stopAlertSound(AlertExplosion sound) {
        Minecraft.getInstance().getSoundManager().stop(sound);
    }
}
