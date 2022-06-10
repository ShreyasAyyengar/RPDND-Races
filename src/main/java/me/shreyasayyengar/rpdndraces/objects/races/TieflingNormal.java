package me.shreyasayyengar.rpdndraces.objects.races;

import me.shreyasayyengar.rpdndraces.objects.abst.AbstractTiefling;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

import java.util.List;
import java.util.UUID;

public class TieflingNormal extends AbstractTiefling {

    public static List<String> getItemLore() {

        List<String> lore = List.of("Through birth or curse, tieflings are a mixture of human and devil features, with the wiles to match their more infernal parentage.");
        List<String> active = List.of("Burning Hands");
        List<String> passive = List.of("Fire Reistance", "Nightvision");

        return RaceUtils.formatLore(lore, active, passive);
    }

    public TieflingNormal(UUID uuid) {
        super(uuid);
    }

    @Override
    public void onSwap() {

        Entity hitEntity = player.getWorld().rayTraceEntities(player.getLocation().add(player.getEyeLocation().getDirection().multiply(2)), player.getEyeLocation().getDirection(), 10, 1).getHitEntity();

        if (hitEntity == null) return;
        if (hitEntity.getType() == EntityType.PLAYER) return;

        if (!(hitEntity instanceof LivingEntity entity)) return;

        for (int i = 0; i < 15; i++) {
            entity.getWorld().spawnParticle(Particle.FLAME, hitEntity.getLocation().add(0, 1, 0), 1, 1.5, 1.5, 1.5, 1.5);
            entity.getWorld().spawnParticle(Particle.REDSTONE, hitEntity.getLocation().add(0, 1, 0), 1, 1.5, 1.5, 1.5, 1.5);
        }

        entity.setHealth(entity.getHealth() - 4);
    }

    @Override
    public String getName() {
        return "Tiefling-Normal";
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

//    public void spawnParticles(Location loc) {
//        for (double numberofcircles = 0; numberofcircles <= 1; numberofcircles += 0.1) {
//            for (double angle = 0; angle <= Math.PI * 2; angle += Math.PI / 8) {
//                double x = (Math.cos(angle) * numberofcircles); // multiply the x by 1 then 1 gets smaller each time we increment numberofcircles by 0.1
//                double y = numberofcircles; // each circle has a distance of 0.1
//                double z = (Math.sin(angle) * numberofcircles); // multiply the z by 1 then 1 gets smaller each time we increment numberofcircles by 0.1
//                loc.add(x, y, z);
//                player.spawnParticle(Particle.FLAME, loc, 1, 0, 0, 0, 0);
//                loc.subtract(x, y, z);
//                // final circle
//                for (double radius = 1; radius >= 0; radius -= 0.1) {
//                    double x2 = Math.cos(angle) * radius;
//                    double y2 = 1; // Max y of the cone
//                    double z2 = Math.sin(angle) * radius;
//                    loc.add(x2, y2, z2);
//                    player.spawnParticle(Particle.FLAME, loc, 1, 0, 0, 0, 0);
//                    loc.subtract(x2, y2, z2);
//                }
//            }
//        }
//    }
}
