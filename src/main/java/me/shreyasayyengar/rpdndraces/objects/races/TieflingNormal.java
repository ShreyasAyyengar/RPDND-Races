package me.shreyasayyengar.rpdndraces.objects.races;

import me.shreyasayyengar.rpdndraces.objects.abst.AbstractTiefling;
import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Monster;
import org.bukkit.util.RayTraceResult;

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
        RayTraceResult rayTraceResult = player.getWorld().rayTraceEntities(player.getLocation().add(player.getEyeLocation().getDirection().multiply(3)), player.getEyeLocation().getDirection(), 7, 1);

        if (rayTraceResult == null) return;

        Entity hitEntity = rayTraceResult.getHitEntity();

        if (hitEntity == null) return;
        if (hitEntity.getType() == EntityType.PLAYER) return;

        if (!(hitEntity instanceof Monster mob)) return;

        for (int i = 0; i < 15; i++) {
            mob.getWorld().spawnParticle(Particle.FLAME, hitEntity.getLocation().add(0, 1, 0), 45, 1.5, 1.5, 1.5, 1.5);
            mob.getWorld().spawnParticle(Particle.REDSTONE.builder().color(Color.RED).count(1).particle(), mob.getLocation().clone().add(0, 1, 0), 15, new Particle.DustOptions(Color.RED, 1));
        }

        mob.damage(6, player);
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
}
