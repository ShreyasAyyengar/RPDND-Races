package me.shreyasayyengar.rpdndraces.objects.races;

import me.shreyasayyengar.rpdndraces.objects.abst.AbstractRace;
import org.bukkit.Sound;

import java.util.List;
import java.util.UUID;

public class Kalashtar extends AbstractRace {

    private boolean glow = false;

    public static List<String> getItemLore() {

        List<String> lore = List.of("An union of human and spirit, Kalashtar are wise beyond their age and often appear reserved or even tranquil. They perceive the world in a dreamlike state, sometimes not far off from insanity.");
        List<String> active = List.of("Emotional Spirit");
        List<String> passive = List.of();

        return RaceUtils.formatLore(lore, active, passive);
    }

    public Kalashtar(UUID uuid) {
        super(uuid);
    }

    @Override
    public void onSwap() {
        if (!glow) {
            player.setGlowing(true);
            glow = true;
        } else {
            player.setGlowing(false);
            glow = false;
        }
    }

    @Override
    public String getName() {
        return "Kalashtar";
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
        return Sound.BLOCK_BEACON_POWER_SELECT;
    }
}
