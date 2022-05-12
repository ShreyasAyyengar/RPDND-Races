package me.shreyasayyengar.rpdndraces.objects.races;

import me.shreyasayyengar.rpdndraces.objects.abst.AbstractAasimar;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Animals;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;

import java.util.UUID;

public class AasimarScourge extends AbstractAasimar {

    public AasimarScourge(UUID uuid) {
        super(uuid);
    }

    private void spawnCircleParticle(LivingEntity livingEntity, Color color) {
        Location location = livingEntity.getLocation();
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
    public void onSwap() {
        player.getLocation().getNearbyLivingEntities(5).forEach(livingEntity -> {
            if (livingEntity instanceof Animals animal) {
                switch (animal.getType()) {
                    case COW, CHICKEN, PIG -> {
                        spawnCircleParticle(animal, Color.fromBGR(0, 0, 181));
                        animal.setHealth(0);
                    }
                }
            }

            if (livingEntity instanceof Monster monster) {
                spawnCircleParticle(monster, Color.fromBGR(0, 0, 181));
                monster.setHealth(0);
            }
        });
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

    @Override
    public Sound getSound() {
        return null;
    }
}