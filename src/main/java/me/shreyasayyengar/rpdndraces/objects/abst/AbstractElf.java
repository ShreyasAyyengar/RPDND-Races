package me.shreyasayyengar.rpdndraces.objects.abst;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.UUID;

public abstract class AbstractElf extends AbstractRace {

    public AbstractElf(UUID uuid) {
        super(uuid);
    }

    @Override
    public void activatePassiveAbilities() {
        player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 1000000, 255));
    }
}
