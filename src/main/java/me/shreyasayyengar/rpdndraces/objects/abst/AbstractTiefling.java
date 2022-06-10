package me.shreyasayyengar.rpdndraces.objects.abst;

import me.shreyasayyengar.rpdndraces.objects.interfaces.PassiveAbilities;
import org.bukkit.potion.PotionEffectType;

import java.util.List;
import java.util.UUID;

public abstract class AbstractTiefling extends AbstractRace implements PassiveAbilities {

    public static List<String> getItemLore() {

        List<String> lore = List.of("Through birth or curse, tieflings are a mixture of human and devil features, with the wiles to match their more infernal parentage. &e&lClick &e&lto &e&lview &e&lmore!");
        List<String> active = List.of();
        List<String> passive = List.of();

        return RaceUtils.formatLore(lore, active, passive);
    }

    public AbstractTiefling(UUID uuid) {
        super(uuid);
    }

    @Override
    public void activatePassiveAbilities() {
        RaceUtils.addPotionEffect(player, PotionEffectType.FIRE_RESISTANCE, 1000000, 255);
        RaceUtils.addPotionEffect(player, PotionEffectType.NIGHT_VISION, 1000000, 255);
    }
}
