package me.shreyasayyengar.rpdndraces.objects.races;

import me.shreyasayyengar.rpdndraces.objects.abst.AbstractRace;
import org.bukkit.Sound;
import org.bukkit.potion.PotionEffectType;

import java.util.List;
import java.util.UUID;

public class Gith extends AbstractRace {

    public static List<String> getItemLore() {

        List<String> lore = List.of("A psionically sensitive race once enslaved to mindflayers, now freed", "A sundered race split in two, Githzerai and Githyanki. Both follow their own opposite values.");
        List<String> active = List.of("Movement");
        List<String> passive = List.of();

        return RaceUtils.formatLore(lore, active, passive);
    }

    public Gith(UUID uuid) {
        super(uuid);
    }

    @Override
    public void onSwap() {
        RaceUtils.addPotionEffect(player, PotionEffectType.JUMP, 15, 10);
        RaceUtils.addPotionEffect(player, PotionEffectType.SPEED, 15, 2);
    }

    @Override
    public String getName() {
        return "Gith";
    }

    @Override
    public void onDisable() {

    }

    @Override
    public int getRaceCooldown() {
        return 10;
    }

    @Override
    public Sound getSound() {
        return Sound.ENTITY_TURTLE_SHAMBLE;
    }
}
