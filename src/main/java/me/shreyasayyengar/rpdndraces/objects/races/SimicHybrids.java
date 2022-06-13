package me.shreyasayyengar.rpdndraces.objects.races;

import me.shreyasayyengar.rpdndraces.RacesPlugin;
import me.shreyasayyengar.rpdndraces.objects.abst.AbstractRace;
import me.shreyasayyengar.rpdndraces.objects.interfaces.Glideable;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.List;
import java.util.UUID;

public class SimicHybrids extends AbstractRace implements Glideable {

    public static List<String> getItemLore() {

        List<String> lore = List.of("Hybrids of various kinds, the Simic Combine has extended their research to cross humanoids with animal traits, with the goal to create soldiers perfectly adapted to a variety of combat situations.");
        List<String> active = List.of("Manta Glide");
        List<String> passive = List.of("Carapace");

        return RaceUtils.formatLore(lore, active, passive);
    }

    public SimicHybrids(UUID uuid) {
        super(uuid);
    }

    @Override
    public void onSwap() {
        if (player.isGliding()) {
            player.sendMessage(RacesPlugin.PREFIX + " &cYou cannot boost yourself!");
        } else {
            player.setVelocity(new Vector(0, 1.5, 0));

            new BukkitRunnable() {

                @Override
                public void run() {
                    player.setGliding(true);
                }
            }.runTaskLater(RacesPlugin.getInstance(), 10);
        }
    }

    @Override
    public String getName() {
        return "Simic-Hybrids";
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
        return null;
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player player) {
            if (isThisRace(player)) {

                if (event.getCause() == EntityDamageEvent.DamageCause.CONTACT) {
                    event.setCancelled(true);
                }
            }
        }
    }
}
