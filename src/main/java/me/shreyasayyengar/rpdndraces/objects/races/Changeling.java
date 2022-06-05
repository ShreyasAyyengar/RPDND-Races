package me.shreyasayyengar.rpdndraces.objects.races;

import me.shreyasayyengar.rpdndraces.objects.abst.AbstractRace;
import org.bukkit.Sound;

import java.util.List;
import java.util.UUID;

public class Changeling extends AbstractRace {

    public static List<String> getItemLore() {

        List<String> lore = List.of("While normally pale faced with eyes black as obsidian, they are masters of", "disguise as they can change their form at will.");
        List<String> active = List.of("Disguise");
        List<String> passive = List.of();

        return RaceUtils.formatLore(lore, active, passive);
    }

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
    public void onDisable() {
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
