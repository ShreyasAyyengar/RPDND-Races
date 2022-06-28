package dev.shreyasayyengar.rpdndraces.objects.races;

import dev.shreyasayyengar.rpdndraces.objects.abst.AbstractTiefling;
import dev.shreyasayyengar.rpdndraces.objects.interfaces.Glideable;
import org.bukkit.Sound;

import java.util.List;
import java.util.UUID;

public class TieflingWinged extends AbstractTiefling implements Glideable {

    public static List<String> getItemLore() {

        List<String> lore = List.of("Through birth or curse, tieflings are a mixture of human and devil features, with the wiles to match their more infernal parentage.");
        List<String> active = List.of("Take Flight", "Wind Ride");
        List<String> passive = List.of("Fire Reistance", "Nightvision");

        return RaceUtils.formatLore(lore, active, passive);
    }

    public TieflingWinged(UUID uuid) {
        super(uuid);
    }

    @Override
    public void onSwap() {
        RaceUtils.startGliding(player);
    }

    @Override
    public String getName() {
        return "Tiefling-Winged";
    }

    @Override
    public void onDisable() {
    }

    @Override
    public int getRaceCooldown() {
        return 20;
    }

    @Override
    public Sound getSound() {
        return Sound.ENTITY_PHANTOM_FLAP;
    }
}
