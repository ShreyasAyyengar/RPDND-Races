package dev.shreyasayyengar.rpdndraces.objects.races;

import dev.shreyasayyengar.rpdndraces.objects.abst.AbstractGenasi;
import org.bukkit.Sound;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.potion.PotionEffectType;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class GenasiWater extends AbstractGenasi {

    private boolean potion = false;

    public static List<String> getItemLore() {

        List<String> lore = List.of("A rare humanoid touched by the elemental planes, Genasi are typically offsprings of genies and mortals.");
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
    public int getRaceCooldown() {
        return 0;
    }

    @Override
    public void onSwap() {
        if (!potion) {
            RaceUtils.addPotionEffect(player, PotionEffectType.DOLPHINS_GRACE, 6, 1);
            RaceUtils.addPotionEffect(player, PotionEffectType.NIGHT_VISION, 3, 3);
            potion = true;
        } else {
            player.removePotionEffect(PotionEffectType.DOLPHINS_GRACE);
            player.removePotionEffect(PotionEffectType.NIGHT_VISION);
            potion = false;
        }
    }

    @Override
    public String getName() {
        return "Genasi-Water";
    }

    @Override
    public Sound getSound() {
        return Sound.ENTITY_ZOMBIE_CONVERTED_TO_DROWNED;
    }
}
