package me.shreyasayyengar.rpdndraces.objects.races;

import me.shreyasayyengar.rpdndraces.objects.abst.AbstractGenasi;
import me.shreyasayyengar.rpdndraces.objects.interfaces.Glideable;
import org.bukkit.Sound;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class GenasiAir extends AbstractGenasi implements Glideable {

    public static List<String> getItemLore() {

        List<String> lore = List.of("A rare humanoid touched by the elemental planes, Genasi are typically offsprings of genies and mortals.");
        List<String> active = List.of("Wind Burst", "Wind Ride");
        List<String> passive = List.of("Air Heritage");

        return RaceUtils.formatLore(lore, active, passive);
    }

    public GenasiAir(UUID uuid) {
        super(uuid);
    }

    @Override
    public Collection<EntityDamageEvent.DamageCause> getImmuneDamageCauses() {
        return List.of(EntityDamageEvent.DamageCause.FALL);
    }

    @Override
    public boolean canWaterBreath() {
        return true;
    }

    @Override
    public void onSwap() {
        RaceUtils.startGliding(player);
    }

    @Override
    public String getName() {
        return "Genasi-Air";
    }

    @Override
    public Sound getSound() {
        return Sound.ENTITY_ENDER_DRAGON_FLAP;
    }

    @Override
    public int getRaceCooldown() {
        return 120;
    }
}
