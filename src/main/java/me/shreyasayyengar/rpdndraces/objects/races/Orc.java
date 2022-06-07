package me.shreyasayyengar.rpdndraces.objects.races;

import me.shreyasayyengar.rpdndraces.RacesPlugin;
import me.shreyasayyengar.rpdndraces.objects.abst.AbstractRace;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.data.BlockData;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.List;
import java.util.UUID;

public class Orc extends AbstractRace {

    public static List<String> getItemLore() {

        List<String> lore = List.of("While they aren't the brightest lot, these green skinned brutes make", "up for it with raw strength and durability.");
        List<String> active = List.of("Ground Stomp");
        List<String> passive = List.of();

        return RaceUtils.formatLore(lore, active, passive);
    }

    public Orc(UUID uuid) {
        super(uuid);
    }

    @Override
    public void onSwap() {
        RaceUtils.addPotionEffect(player, PotionEffectType.REGENERATION, 10, 2);
        RaceUtils.addPotionEffect(player, PotionEffectType.DAMAGE_RESISTANCE, 10, 2);
        RaceUtils.addPotionEffect(player, PotionEffectType.INCREASE_DAMAGE, 10, 2);
        RaceUtils.addPotionEffect(player, PotionEffectType.SPEED, 10, 2);

        // todo fix duplicate code
        player.setVelocity(player.getVelocity().add(new Vector(0, 1.2, 0)));

        new BukkitRunnable() {
            @Override
            public void run() {
                player.setVelocity(player.getVelocity().subtract(new Vector(0, 2, 0)));

                new BukkitRunnable() {
                    @Override
                    public void run() {
                        if (player.isOnGround()) {

                            BlockData blockData = player.getLocation().getBlock().getBlockData();
                            player.getWorld().spawnParticle(Particle.EXPLOSION_NORMAL, player.getLocation(), 50, 1, 1, 1, 0.1);
                            player.getWorld().spawnParticle(Particle.BLOCK_DUST, player.getLocation(), 100, 1, 1, 1, blockData);
                            player.getWorld().playSound(player.getLocation(), Sound.ENTITY_GUARDIAN_HURT, 1, 1);
                            cancel();
                        }
                    }
                }.runTaskTimer(RacesPlugin.getInstance(), 0, 1);
            }
        }.runTaskLater(RacesPlugin.getInstance(), 10);
    }

    @Override
    public String getName() {
        return "Orc";
    }

    @Override
    public void onDisable() {

    }

    @Override
    public int getRaceCooldown() {
        return 15;
    }

    @Override
    public Sound getSound() {
        return Sound.ENTITY_RAVAGER_ATTACK;
    }
}
