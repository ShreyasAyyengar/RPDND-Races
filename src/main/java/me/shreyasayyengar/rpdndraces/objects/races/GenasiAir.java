package me.shreyasayyengar.rpdndraces.objects.races;

import me.shreyasayyengar.rpdndraces.objects.abst.AbstractGenasi;
import org.bukkit.Sound;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class GenasiAir extends AbstractGenasi {

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
        // Elytra effects
    }

    @Override
    public String getName() {
        return "Genasi-Air";
    }

    @Override
    public Sound getSound() {
        return null;
    }
}
