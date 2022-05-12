package me.shreyasayyengar.rpdndraces.objects.races;

import me.shreyasayyengar.rpdndraces.objects.abst.AbstractRace;
import org.bukkit.Sound;

import java.util.UUID;

public class Changeling extends AbstractRace {

    public Changeling(UUID uuid) {
        super(uuid);
    }

    @Override
    public void onSwap() {
        // TODO Disguises
    }

    @Override
    public String getName() {
        return "Changeling";
    }

    @Override
    public void deactivate() {
    }

    @Override
    public int getRaceCooldown() {
        return 14400;
    }

    @Override
    public Sound getSound() {
        return null;
    }
}
