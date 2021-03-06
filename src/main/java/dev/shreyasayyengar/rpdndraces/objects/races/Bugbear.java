package dev.shreyasayyengar.rpdndraces.objects.races;

import dev.shreyasayyengar.rpdndraces.objects.abst.AbstractRace;
import org.bukkit.Sound;
import org.bukkit.potion.PotionEffectType;

import java.util.List;
import java.util.UUID;

public class Bugbear extends AbstractRace {

    public static List<String> getItemLore() {

        List<String> lore = List.of("These long-limbed goblinoids are mostly nocturnal and often take up leadership roles within goblin clans.");
        List<String> active = List.of("Shadow Walk");
        List<String> passive = List.of();

        return RaceUtils.formatLore(lore, active, passive);
    }

    public Bugbear(UUID uuid) {
        super(uuid);
    }

    @Override
    public void onSwap() {
        RaceUtils.teleportForward(player, 10);
        RaceUtils.addPotionEffect(player,PotionEffectType.SPEED, 15, 2);
        RaceUtils.addPotionEffect(player,PotionEffectType.INCREASE_DAMAGE, 15, 2);
    }

    @Override
    public String getName() {
        return "Bugbear";
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
        return null;
    }
}
