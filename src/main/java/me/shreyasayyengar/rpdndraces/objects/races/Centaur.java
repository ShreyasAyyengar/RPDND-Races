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

public class Centaur extends AbstractRace {

    public static List<String> getItemLore() {

        List<String> lore = List.of("Roamers at heart, centaurs love open spaces and the freedom to travel. ", "They race the wind, hooves thundering and tails streaming behind them.");
        List<String> active = List.of("Mad Dash");
        List<String> passive = List.of();

        return RaceUtils.formatLore(lore, active, passive);
    }

    public Centaur(UUID uuid) {
        super(uuid);
    }

    @Override
    public void onSwap() {
        RaceUtils.pushForward(player, 3);
        RaceUtils.addPotionEffect(player, PotionEffectType.SPEED, 3, 10);

        new BukkitRunnable() {
            @Override
            public void run() {
                RaceUtils.addPotionEffect(player, PotionEffectType.SPEED, 15, 2);
            }
        }.runTaskLater(RacesPlugin.getInstance(), 3 * 20);
    }

    @Override
    public String getName() {
        return "Centaur";
    }

    @Override
    public void onDisable() {
    }

    @Override
    public int getRaceCooldown() {
        return 25;
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

            if (player.isSprinting()) {
                player.playSound(player.getLocation(), Sound.ENTITY_HORSE_GALLOP, 1, 1);
            } else {
                player.playSound(player.getLocation(), Sound.ENTITY_HORSE_STEP, 1, 1);
            }
        }
    }
}
