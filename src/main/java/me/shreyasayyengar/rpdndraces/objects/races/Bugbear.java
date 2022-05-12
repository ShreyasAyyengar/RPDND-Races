package me.shreyasayyengar.rpdndraces.objects.races;

import me.shreyasayyengar.rpdndraces.objects.abst.AbstractRace;
import org.bukkit.Sound;
import org.bukkit.potion.PotionEffectType;

import java.util.UUID;

public class Bugbear extends AbstractRace {

    public Bugbear(UUID uuid) {
        super(uuid);
    }

    @Override
    public void onSwap() {
        RaceUtils.boostForward(player, 10);
        RaceUtils.addPotionEffect(player,PotionEffectType.SPEED, 15, 1);
        RaceUtils.addPotionEffect(player,PotionEffectType.INCREASE_DAMAGE, 15, 1);
    }

    @Override
    public String getName() {
        return "Bugbear";
    }

    @Override
    public void deactivate() {
    }

    @Override
    public int getRaceCooldown() {
        return 20;
    }

    @Override
    public Sound getSound() {
        return null;
    }
}
