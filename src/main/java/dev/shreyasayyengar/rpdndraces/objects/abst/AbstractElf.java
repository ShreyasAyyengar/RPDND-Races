package dev.shreyasayyengar.rpdndraces.objects.abst;

import dev.shreyasayyengar.rpdndraces.objects.interfaces.PassiveAbilities;
import org.bukkit.Sound;
import org.bukkit.potion.PotionEffectType;

import java.util.List;
import java.util.UUID;

public abstract class AbstractElf extends AbstractRace implements PassiveAbilities {

    public static List<String> getItemLore() {

        List<String> lore = List.of("Often described as the definition of grace and poise, they are agile and deadly hunters. &e&lClick &e&lto &e&lview &e&lmore!");
        List<String> active = List.of();
        List<String> passive = List.of();

        return RaceUtils.formatLore(lore, active, passive);
    }

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
