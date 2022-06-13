package me.shreyasayyengar.rpdndraces.objects.races;

import me.shreyasayyengar.rpdndraces.objects.abst.AbstractRace;
import org.bukkit.Sound;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffectType;

import java.util.List;
import java.util.UUID;

public class YuantiPureblood extends AbstractRace {

    private boolean enabled = false;

    public static List<String> getItemLore() {

        List<String> lore = List.of("Ages ago their dark gods taught them profane, cannibalistic rituals to mix their flesh with that of snakes, producing caste-based society of human snake hybrids.");
        List<String> active = List.of("Nightvision", "Animal Charm");
        List<String> passive = List.of("Poison Immunity");

        return RaceUtils.formatLore(lore, active, passive);
    }

    public YuantiPureblood(UUID uuid) {
        super(uuid);
    }

    @Override
    public void onSwap() {
        if (!enabled) {
            RaceUtils.addPotionEffect(player, PotionEffectType.NIGHT_VISION, 1000000, 255);
            enabled = true;
        } else {
            player.removePotionEffect(PotionEffectType.NIGHT_VISION);
            enabled = false;
        }
    }

    @Override
    public String getName() {
        return "Yuanti-Pureblood";
    }

    @Override
    public void onDisable() {

    }

    @Override
    public int getRaceCooldown() {
        return 0;
    }

    @Override
    public Sound getSound() {
        return null;
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player player) {
            if (isThisRace(player)) {

                if (event.getCause() == EntityDamageEvent.DamageCause.POISON) {
                    event.setCancelled(true);
                    player.removePotionEffect(PotionEffectType.POISON);
                }
            }
        }
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        if (isThisRace(player)) {
            player.getLocation().getNearbyLivingEntities(5, 5, 5, livingEntity -> !(livingEntity instanceof Monster)).forEach(passiveMob -> {
                if (passiveMob.isLeashed()) return;

                passiveMob.setLeashHolder(player);
            });
        }
    }
}
