package me.shreyasayyengar.rpdndraces.objects.races;

import me.shreyasayyengar.rpdndraces.objects.abst.AbstractRace;
import org.bukkit.Sound;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.UUID;

public class Gith extends AbstractRace {

    public Gith(UUID uuid) {
        super(uuid);
    }

    @Override
    public void onSwap() {
        // Jump Boost 10 for 15 seconds & Swiftness 2 for 15 seconds
        RaceUtils.addPotionEffect(player, PotionEffectType.JUMP, 15, 10);
        RaceUtils.addPotionEffect(player, PotionEffectType.SPEED, 15, 2);
    }

    @Override
    public String getName() {
        return "Gith";
    }

    @Override
    public void deactivate() {

    }

    @Override
    public int getRaceCooldown() {
        return 10;
    }

    @Override
    public Sound getSound() {
        return null;
    }
}
