package me.shreyasayyengar.rpdndraces.objects.races;

import me.shreyasayyengar.rpdndraces.RacesPlugin;
import me.shreyasayyengar.rpdndraces.objects.abst.AbstractRace;
import me.shreyasayyengar.rpdndraces.objects.interfaces.BoostedDiet;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class Tabaxi extends AbstractRace implements BoostedDiet {

    public static List<String> getItemLore() {

        List<String> lore = List.of("These cat-like humanoids are as flexible and agile as they look, as", "well as fast and deadly predators with razor-like claws.");
        List<String> active = List.of("Mad Dash");
        List<String> passive = List.of("Raw Diet");

        return RaceUtils.formatLore(lore, active, passive);
    }

    public Tabaxi(UUID uuid) {
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
        }.runTaskLater(RacesPlugin.getInstance(), (3 * 20) + 2);
    }

    @Override
    public String getName() {
        return "Tabaxi";
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
    public void onPlayerItemConsume(PlayerItemConsumeEvent event) {
        Player player = event.getPlayer();

        if (isThisRace(player)) {
            Collection<Material> unaffectedFoods = List.of(
                    Material.COD,
                    Material.SALMON,
                    Material.PUFFERFISH,
                    Material.TROPICAL_FISH
            );

            if (unaffectedFoods.contains(event.getItem().getType())) {
                event.setCancelled(true);
                event.getItem().subtract();
                player.setFoodLevel(player.getFoodLevel() + 8);
            }
        }
    }

    @Override
    public Collection<Material> getBoostedFoods() {
        return null;
    }

    @Override
    public boolean justRaw() {
        return true;
    }

    @Override
    public int getFoodPoints() {
        return 0;
    }
}
