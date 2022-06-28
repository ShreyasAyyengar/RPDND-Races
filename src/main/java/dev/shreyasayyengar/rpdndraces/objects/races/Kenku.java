package dev.shreyasayyengar.rpdndraces.objects.races;

import dev.shreyasayyengar.rpdndraces.RacesPlugin;
import dev.shreyasayyengar.rpdndraces.objects.abst.AbstractRace;
import dev.shreyasayyengar.rpdndraces.utils.Utils;
import org.bukkit.Sound;

import java.util.List;
import java.util.UUID;

public class Kenku extends AbstractRace {

    public static List<String> getItemLore() {

        List<String> lore = List.of("Haunted by an ancient crime that robbed them of their wings, the flightless kenku wander the world as vagabonds who live at the edge of society.");
        List<String> active = List.of("Mimicry");
        List<String> passive = List.of();

        return RaceUtils.formatLore(lore, active, passive);
    }

    public Kenku(UUID uuid) {
        super(uuid);
    }

    @Override
    public void onSwap() {
        player.sendMessage(Utils.colourise(RacesPlugin.PREFIX + "&aUse /k <sound> to mimic a sound!"));
    }

    @Override
    public String getName() {
        return "Kenku";
    }

    @Override
    public void onDisable() {
    }

    @Override
    public int getRaceCooldown() {
        return 0;
    }

    @Override
    public Sound getSound() {
        return null;
    }
}