package me.shreyasayyengar.rpdndraces.objects.races;

import me.shreyasayyengar.rpdndraces.RacesPlugin;
import me.shreyasayyengar.rpdndraces.objects.abst.AbstractRace;
import me.shreyasayyengar.rpdndraces.objects.interfaces.TaskedRace;
import org.bukkit.Sound;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.UUID;

public class DummyRace extends AbstractRace implements TaskedRace {

    public DummyRace(UUID uuid) {
        super(uuid);
    }

    @Override
    public void onSwap() {
        player.sendMessage("swpapped");
    }

    @Override
    public String getName() {
        return "Dummy";
    }

    @Override
    public void deactivate() {

    }

    @Override
    public int getRaceCooldown() {
        return 0;
    }

    @Override
    public Sound getSound() {
        return null;
    }

    @Override
    public BukkitTask getRaceTask() {
        return new BukkitRunnable() {
            @Override
            public void run() {
                System.out.println("printing this out from the dummy");
            }
        }.runTaskTimer(RacesPlugin.getInstance(), 0, 20);
    }
}
