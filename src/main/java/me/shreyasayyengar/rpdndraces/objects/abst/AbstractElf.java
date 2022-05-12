package me.shreyasayyengar.rpdndraces.objects.abst;

import me.shreyasayyengar.rpdndraces.objects.interfaces.PassiveAbilities;
import org.bukkit.Sound;
import org.bukkit.potion.PotionEffectType;

import java.util.UUID;

public abstract class AbstractElf extends AbstractRace implements PassiveAbilities {

    public AbstractElf(UUID uuid) {
        super(uuid);
    }

    @Override
    public void activatePassiveAbilities() {
        RaceUtils.addPotionEffect(player, PotionEffectType.NIGHT_VISION, 1000000, 255);
    }

    @Override
    public Sound getSound() {
        return null;
    }
}
