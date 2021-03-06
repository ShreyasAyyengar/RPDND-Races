package dev.shreyasayyengar.rpdndraces.objects.races;

import dev.shreyasayyengar.rpdndraces.objects.abst.AbstractRace;
import dev.shreyasayyengar.rpdndraces.objects.interfaces.Glideable;
import dev.shreyasayyengar.rpdndraces.utils.RaceManager;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.List;
import java.util.UUID;

public class Aarakocra extends AbstractRace implements Glideable {

    public static List<String> getItemLore() {

        List<String> lore = List.of("Sequestered in high mountains atop tall trees, these bird-like folk evoke both fear and wonder.");
        List<String> active = List.of("Take Flight", "Wind Ride");
        List<String> passive = List.of("Natural Weapon");

        return RaceUtils.formatLore(lore, active, passive);
    }

    public Aarakocra(UUID uuid) {
        super(uuid);
    }

    @Override
    public void onSwap() {
        RaceUtils.startGliding(player);
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
        return Sound.ENTITY_ENDER_DRAGON_FLAP;
    }

    @Override
    public String getName() {
        return "Aarakocra";
    }

    @EventHandler
    private void onHit(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player player) {

            if (!RaceManager.hasRace(player.getUniqueId())) {
                return;
            }

            if (isThisRace(player)) {
                event.setDamage(6);
                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1, 1);
                player.getWorld().spawnParticle(org.bukkit.Particle.SWEEP_ATTACK, event.getEntity().getLocation().add(1, 0, 1), 1, 0, 0, 0, 0);
            }
        }
    }

    @EventHandler
    private void onSwing(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if (!RaceManager.hasRace(player.getUniqueId())) {
            return;
        }

        if (isThisRace(player)) {
            if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) {
                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1, 1);
                player.getWorld().spawnParticle(org.bukkit.Particle.SWEEP_ATTACK, player.getEyeLocation().toCenterLocation(), 1, 0, 0, 0, 0);
            }
        }
    }
}
