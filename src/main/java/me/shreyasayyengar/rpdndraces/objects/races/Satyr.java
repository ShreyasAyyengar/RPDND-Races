package me.shreyasayyengar.rpdndraces.objects.races;

import me.shreyasayyengar.rpdndraces.objects.abst.AbstractRace;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffectType;

import java.util.List;
import java.util.UUID;

public class Satyr extends AbstractRace {

    public static List<String> getItemLore() {

        List<String> lore = List.of("Full of good spirits, gregarious personalities, and love of revels. To see the world and to sample every pleasure they don't let moodiness of others ruin their fun.");
        List<String> active = List.of("Boundless Energy");
        List<String> passive = List.of();

        return RaceUtils.formatLore(lore, active, passive);
    }

    public Satyr(UUID uuid) {
        super(uuid);
    }

    @Override
    public void onSwap() {
        RaceUtils.addPotionEffect(player, PotionEffectType.JUMP, 15, 4);
        RaceUtils.addPotionEffect(player, PotionEffectType.SPEED, 15, 2);
    }

    @Override
    public String getName() {
        return "Satyr";
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
        return Sound.ENTITY_HORSE_GALLOP;
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
