package me.shreyasayyengar.rpdndraces.objects.races;

import me.shreyasayyengar.rpdndraces.objects.abst.AbstractRace;
import me.shreyasayyengar.rpdndraces.objects.interfaces.InventoryRequirement;
import me.shreyasayyengar.rpdndraces.objects.interfaces.RequiredSetup;
import me.shreyasayyengar.rpdndraces.utils.RaceManager;
import me.shreyasayyengar.rpdndraces.utils.Utils;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.List;
import java.util.UUID;

public class Aarakocra extends AbstractRace implements RequiredSetup, InventoryRequirement {

    public static List<String> getItemLore() {

        List<String> lore = List.of("Sequestered in high mountains atop tall trees, these", "these bird-like folk evoke both fear and wonder.");
        List<String> active = List.of("Take Flight", "Wind Ride");
        List<String> passive = List.of("Natural Weapon");

        return RaceUtils.formatLore(lore, active, passive);
    }

    public Aarakocra(UUID uuid) {
        super(uuid);
    }

    @Override
    public void setupPlayer() {
        runUnsafeActions(() -> {
            Utils.setElytra(player);

            ItemStack[] contents = player.getInventory().getContents();

            for (int i = 0; i < contents.length; i++) {
                if (contents[i] == null) {

                    if (player.getInventory().getChestplate() != null) {

                        ItemStack chestplate = player.getInventory().getChestplate();

                        if (chestplate.getType() != Material.ELYTRA) {
                            player.getInventory().setItem(i, chestplate);
                            chestplate.setAmount(1);
                            player.getInventory().getChestplate().setType(Material.ELYTRA);
                        }
                    } else player.getInventory().setChestplate(new ItemStack(Material.ELYTRA));

                    break;
                }
            }
        });
    }

    @Override
    public void onSwap() {
        if (player.isGliding()) {
            player.setVelocity(player.getEyeLocation().getDirection().normalize().multiply(3.5));
        } else {
            player.setVelocity(player.getVelocity().add(new Vector(0, 3, 0)));
        }
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
                player.playSound(player.getLocation(), org.bukkit.Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1, 1);
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
                player.playSound(player.getLocation(), org.bukkit.Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1, 1);
                player.getWorld().spawnParticle(org.bukkit.Particle.SWEEP_ATTACK, player.getEyeLocation().toCenterLocation(), 1, 0, 0, 0, 0);
            }
        }
    }
}
