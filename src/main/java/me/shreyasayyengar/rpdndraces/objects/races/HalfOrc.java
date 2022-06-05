package me.shreyasayyengar.rpdndraces.objects.races;

import me.shreyasayyengar.rpdndraces.objects.abst.AbstractRace;
import org.bukkit.Sound;
import org.bukkit.potion.PotionEffectType;

import java.util.List;
import java.util.UUID;

public class HalfOrc extends AbstractRace {

    public static List<String> getItemLore() {

        List<String> lore = List.of("The offsping of humans and orcs they have the strength and tenacity of both parents.");
        List<String> active = List.of("Rage");
        List<String> passive = List.of();

        return RaceUtils.formatLore(lore, active, passive);
    }

    public HalfOrc(UUID uuid) {
        super(uuid);
    }

    @Override
    public void onSwap() {
        RaceUtils.addPotionEffect(player, PotionEffectType.REGENERATION, 15, 2);
        RaceUtils.addPotionEffect(player, PotionEffectType.DAMAGE_RESISTANCE, 15, 2);
        RaceUtils.addPotionEffect(player, PotionEffectType.INCREASE_DAMAGE, 15, 2);
    }

    @Override
    public String getName() {
        return "Half-Orc";
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
