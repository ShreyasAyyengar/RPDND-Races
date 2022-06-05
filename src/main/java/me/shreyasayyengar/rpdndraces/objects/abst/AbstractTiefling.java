package me.shreyasayyengar.rpdndraces.objects.abst;

import me.shreyasayyengar.rpdndraces.objects.interfaces.PassiveAbilities;
import org.bukkit.potion.PotionEffectType;

import java.util.UUID;

public abstract class AbstractTiefling extends AbstractRace implements PassiveAbilities {

    public AbstractTiefling(UUID uuid) {
        super(uuid);
    }

    @Override
    public void activatePassiveAbilities() {
        RaceUtils.addPotionEffect(player, PotionEffectType.FIRE_RESISTANCE, 1000000, 255);
        RaceUtils.addPotionEffect(player, PotionEffectType.NIGHT_VISION, 1000000, 255);
    }
}
