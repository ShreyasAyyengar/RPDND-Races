package me.shreyasayyengar.rpdndraces.objects.races;

import me.shreyasayyengar.rpdndraces.objects.abst.AbstractRace;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.List;
import java.util.UUID;

public class Dragonborn extends AbstractRace {

    public static List<String> getItemLore() {

        List<String> lore = List.of("A humanoid draconic race native to Abeir. Strongly built, the ", "blood of dragons grant them gifts and the use of their breath weapon.");
        List<String> active = List.of("Dragon's Breath");
        List<String> passive = List.of("Scale Defense");

        return RaceUtils.formatLore(lore, active, passive);
    }

    public Dragonborn(UUID uuid) {
        super(uuid);
    }

    @Override
    public void onSwap() {
        createParticles();
    }

    private void createParticles() {

        Location loc = player.getLocation();
        for (double numberofcircles = 0; numberofcircles <= 1; numberofcircles += 0.1) {
            for (double angle = 0; angle <= Math.PI * 2; angle += Math.PI / 8) {
                double x = Math.cos(angle) * numberofcircles;
                double y = numberofcircles;
                double z = Math.sin(angle) * numberofcircles;
                loc.add(x, y, z);
                player.spawnParticle(Particle.FLAME, loc, 1, 0, 0, 0, 0);
                loc.subtract(x, y, z);

                for (double radius = 1; radius >= 0; radius -= 0.1) {
                    double x2 = 1.5 * (Math.cos(angle) * radius);
                    double y2 = 1;
                    double z2 = 1.5 * (Math.sin(angle) * radius);
                    loc.add(x2, y2, z2);
                    player.spawnParticle(Particle.FLAME, loc, 1, 0, 0, 0, 0);
                    loc.subtract(x2, y2, z2);
                }
            }
        }
    }

    @Override
    public String getName() {
        return "Dragonborn";
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
    private void onEntityDamage(EntityDamageEvent event) {

        if (event.getEntityType() == EntityType.PLAYER) {
            Player player = (Player) event.getEntity();
            if (isThisRace(player)) {

                switch (event.getCause()) {
                    case CONTACT, FIRE, FIRE_TICK, LAVA -> event.setCancelled(true);
                }
            }
        }
    }
}
