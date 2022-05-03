package me.shreyasayyengar.rpdndraces.objects.races;

import me.shreyasayyengar.rpdndraces.objects.abst.AbstractRace;
import org.bukkit.scheduler.BukkitTask;

import java.util.UUID;

public class Changeling extends AbstractRace {

    public Changeling(UUID uuid) {
        super(uuid);
    }

    @Override
    public void setupPlayer() {

    }

    @Override
    public void onSwap() {
        // TODO Disguises
    }

    @Override
    public void activatePassiveAbilities() {

    }

    @Override
    public BukkitTask getTask() {
        return null;
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
}
