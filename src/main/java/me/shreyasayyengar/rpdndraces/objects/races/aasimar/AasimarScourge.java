package me.shreyasayyengar.rpdndraces.objects.races.aasimar;

import me.shreyasayyengar.rpdndraces.RacesPlugin;
import me.shreyasayyengar.rpdndraces.objects.abst.AbstractAasimar;
import me.shreyasayyengar.rpdndraces.utils.Utils;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Monster;

import java.util.UUID;

public class AasimarScourge extends AbstractAasimar {

    public AasimarScourge(UUID uuid) {
        super(uuid);
    }

    private void spawnCircleParticle(Monster monster, Color color) {
        Location location = monster.getLocation();
        for (int degree = 0; degree < 360; degree++) {
            double radians = Math.toRadians(degree);
            double x = Math.cos(radians);
            double z = Math.sin(radians);
            location.add(x, 0, z);
            location.getWorld().spawnParticle(Particle.REDSTONE.builder().color(Color.RED).count(1).particle(), location.clone().add(0, 1, 0), 2, new Particle.DustOptions(color, 1));
            location.subtract(x, 0, z);
        }
    }

    @Override
    public void setupPlayer() {
    }

    @Override
    public void onSwap() {
        if (isOnCooldown()) {
            player.sendMessage(Utils.colourise(RacesPlugin.PREFIX + " &cYou are currently on cooldown! (" + cooldownTime + "s)"));
            return;
        }

        player.getLocation().getNearbyLivingEntities(5).forEach(livingEntity -> {
            if (livingEntity instanceof Monster monster) {
                spawnCircleParticle(monster, Color.fromBGR(0, 0, 181));
                monster.setHealth(0);
            }
        });

        setCooldown();
    }

    @Override
    public void activatePassiveAbilities() {
    }

    @Override
    public String getName() {
        return "Aasimar-Scourge";
    }

    @Override
    public void deactivate() {
    }

    @Override
    public int getRaceCooldown() {
        return 180;
    }
}