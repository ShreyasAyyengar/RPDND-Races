package me.shreyasayyengar.rpdndraces.objects.races;

import me.shreyasayyengar.rpdndraces.RacesPlugin;
import me.shreyasayyengar.rpdndraces.objects.abst.AbstractGenasi;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityToggleGlideEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class GenasiAir extends AbstractGenasi {

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
        player.setVelocity(new Vector(0, 3.85, 0));
        new BukkitRunnable() {

            @Override
            public void run() {
                player.setGliding(true);
            }
        }.runTaskLater(RacesPlugin.getInstance(), 10L);

        new BukkitRunnable() {
            @Override
            public void run() {
                player.setGliding(false);
            }
        }.runTaskLater(RacesPlugin.getInstance(), 1200L);
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

    @EventHandler
    public void onEntityToggleGlide(EntityToggleGlideEvent event) {
        if (event.getEntity() instanceof Player player) {
            if (isThisRace(player)) {
                if (!player.isOnGround()) {
                    event.setCancelled(true);
                }
            }
        }
    }
}
