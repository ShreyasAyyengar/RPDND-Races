package me.shreyasayyengar.rpdndraces.objects.races;

import me.shreyasayyengar.rpdndraces.RacesPlugin;
import me.shreyasayyengar.rpdndraces.objects.abst.AbstractElf;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;
import java.util.UUID;

public class ElfDrow extends AbstractElf {

    public static List<String> getItemLore() {

        List<String> lore = List.of("Often described as the definition of grace and poise, they are agile and deadly hunters.");
        List<String> active = List.of("Darkness");
        List<String> passive = List.of("Nightvision");

        return RaceUtils.formatLore(lore, active, passive);
    }

    public ElfDrow(UUID uuid) {
        super(uuid);
    }

    @Override
    public void onSwap() {
        new BukkitRunnable() {
            int count = 20;

            @Override
            public void run() {
                if (count == 0) cancel();

                runUnsafeActions(() -> spawnCircleParticle(Color.fromBGR(1, 1, 1), player.getLocation().clone().add(0, 2, 0)));

                count--;
            }
        }.runTaskTimer(RacesPlugin.getInstance(), 0, 10);
    }

    private void spawnCircleParticle(Color color, Location location) {
        for (int degree = 0; degree < 360; degree++) {
            double radians = Math.toRadians(degree);
            double x = (2.5 * Math.cos(radians));
            double z = (2.5 * Math.sin(radians));
            location.add(x, 0, z);
            location.getWorld().spawnParticle(Particle.REDSTONE.builder().color(Color.RED).count(50).particle(), location, 1, new Particle.DustOptions(color, 1));
            location.subtract(x, 0, z);
        }
    }

    @Override
    public String getName() {
        return "Elf-Drow";
    }

    @Override
    public void onDisable() {
    }

    @Override
    public int getRaceCooldown() {
        return 20;
    }

    @Override
    public Sound getSound() {
        return null;
    }
}