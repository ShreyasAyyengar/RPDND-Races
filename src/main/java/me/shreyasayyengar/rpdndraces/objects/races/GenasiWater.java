package me.shreyasayyengar.rpdndraces.objects.races;

import me.shreyasayyengar.rpdndraces.objects.abst.AbstractGenasi;
import org.bukkit.Sound;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.potion.PotionEffectType;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class GenasiWater extends AbstractGenasi {

    private boolean nightVision = false;

    public static List<String> getItemLore() {

        List<String> lore = List.of("The offspring of a Genie and a human, they carry the", "power of the elemental planes in their blood.");
        List<String> active = List.of("Aquatics");
        List<String> passive = List.of("Water Heritage");

        return RaceUtils.formatLore(lore, active, passive);
    }

    public GenasiWater(UUID uuid) {
        super(uuid);
    }

    @Override
    public Collection<EntityDamageEvent.DamageCause> getImmuneDamageCauses() {
        return List.of();
    }

    @Override
    public boolean canWaterBreath() {
        return true;
    }

    @Override
    public void onSwap() {
        RaceUtils.addPotionEffect(player, PotionEffectType.DOLPHINS_GRACE, 6, 1);
        if (!nightVision) {
            RaceUtils.addPotionEffect(player, PotionEffectType.NIGHT_VISION, 3, 3);
            nightVision = true;
        } else {
            player.removePotionEffect(PotionEffectType.NIGHT_VISION);
            nightVision = false;
        }
    }

    @Override
    public String getName() {
        return "Genasi-Water";
    }

    @Override
    public Sound getSound() {
        return null;
    }
}
