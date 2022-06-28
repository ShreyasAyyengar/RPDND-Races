package dev.shreyasayyengar.rpdndraces.objects.races;

import dev.shreyasayyengar.rpdndraces.objects.abst.AbstractRace;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffectType;

import java.util.List;
import java.util.UUID;

public class Human extends AbstractRace {

    public static List<String> getItemLore() {

        List<String> lore = List.of("Diverse and ambitious, and although they lacked specialization, they could excel in many areas.");
        List<String> active = List.of("Adrenaline");
        List<String> passive = List.of("Friendship");

        return RaceUtils.formatLore(lore, active, passive);
    }

    public Human(UUID uuid) {
        super(uuid);
    }

    @Override
    public void onSwap() {
        RaceUtils.addPotionEffect(player, PotionEffectType.INCREASE_DAMAGE, 15, 2);
        RaceUtils.addPotionEffect(player, PotionEffectType.REGENERATION, 15, 2);
    }

    @Override
    public String getName() {
        return "Human";
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
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        if (isThisRace(player)) {

            if (player.getLocation().getNearbyPlayers(5).size() > 1) {
                RaceUtils.addPotionEffect(player, PotionEffectType.HEALTH_BOOST, 10, 5);
            } else {
                player.removePotionEffect(PotionEffectType.HEALTH_BOOST);
            }
        }
    }
}
