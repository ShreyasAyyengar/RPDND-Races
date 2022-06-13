package me.shreyasayyengar.rpdndraces.objects.races;

import me.shreyasayyengar.rpdndraces.RacesPlugin;
import me.shreyasayyengar.rpdndraces.objects.abst.AbstractRace;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;
import java.util.UUID;

public class Minotaur extends AbstractRace {

    public static List<String> getItemLore() {

        List<String> lore = List.of("Humanoids with a beastial head of a bovine, horns included. Don't stand in the way of a charging Minotaur!");
        List<String> active = List.of("Mad Dash");
        List<String> passive = List.of();

        return RaceUtils.formatLore(lore, active, passive);
    }

    public Minotaur(UUID uuid) {
        super(uuid);
    }

    @Override
    public void onSwap() {
        RaceUtils.pushForward(player);
        RaceUtils.addPotionEffect(player, PotionEffectType.SPEED, 3, 10);

        new BukkitRunnable() {
            @Override
            public void run() {
                RaceUtils.addPotionEffect(player, PotionEffectType.SPEED, 15, 2);
            }
        }.runTaskLater(RacesPlugin.getInstance(), (3 * 20) + 1);
    }

    @Override
    public String getName() {
        return "Minotaur";
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

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        if (isThisRace(player)) {
            if (player.getLocation().getBlockX() == event.getTo().getBlockX() && player.getLocation().getBlockZ() == event.getTo().getBlockZ()) {
                return;
            }

            if (!player.isOnGround()|| player.isSwimming() || player.isInWater()) return;

            player.playSound(player.getLocation(), Sound.ENTITY_HORSE_STEP, 0.3F, 1);

        }
    }
}
