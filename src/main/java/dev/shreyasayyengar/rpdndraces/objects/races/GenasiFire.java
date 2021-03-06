package dev.shreyasayyengar.rpdndraces.objects.races;

import dev.shreyasayyengar.rpdndraces.RacesPlugin;
import dev.shreyasayyengar.rpdndraces.objects.abst.AbstractGenasi;
import dev.shreyasayyengar.rpdndraces.objects.interfaces.TaskedRace;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import ru.beykerykt.minecraft.lightapi.common.LightAPI;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class GenasiFire extends AbstractGenasi implements TaskedRace {

    private final List<Location> lightedSources = new ArrayList<>();
    private boolean light = false;

    public static List<String> getItemLore() {

        List<String> lore = List.of("A rare humanoid touched by the elemental planes, Genasi are typically offsprings of genies and mortals.");
        List<String> active = List.of("Firey Light");
        List<String> passive = List.of("Fire Heritage");

        return RaceUtils.formatLore(lore, active, passive);
    }

    public GenasiFire(UUID uuid) {
        super(uuid);
    }

    private void setLight(Location location, int lightLevel) {
        LightAPI.get().setLightLevel(location.getWorld().getName(), location.getBlockX(), location.getBlockY(), location.getBlockZ(), lightLevel);
    }

    @Override
    public Collection<EntityDamageEvent.DamageCause> getImmuneDamageCauses() {
        return List.of(EntityDamageEvent.DamageCause.FIRE, EntityDamageEvent.DamageCause.FIRE_TICK, EntityDamageEvent.DamageCause.LAVA);
    }

    @Override
    public boolean canWaterBreath() {
        return false;
    }

    @Override
    public void onSwap() {
        light = !light;

        if (!light) {
            lightedSources.forEach(location -> setLight(location, 0));
            lightedSources.clear();
        }
    }

    @Override
    public String getName() {
        return "Genasi-Fire";
    }

    @Override
    public int getRaceCooldown() {
        return 0;
    }

    @Override
    public Sound getSound() {
        return Sound.BLOCK_FIRE_AMBIENT;
    }

    @Override
    public BukkitTask getRaceTask() {
        return new BukkitRunnable() {
            @Override
            public void run() {
                if (!lightedSources.isEmpty() && lightedSources.size() != 1) {
                    setLight(lightedSources.get(0), 0);
                    lightedSources.remove(0);
                }
            }
        }.runTaskTimer(RacesPlugin.getInstance(), 0, 5);
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        if (isThisRace(player)) {

            if (event.getFrom().getBlockX() != event.getTo().getBlockX() || event.getFrom().getBlockZ() != event.getTo().getBlockZ()) {
                if (light) {
                    setLight(player.getLocation(), 10);
                    lightedSources.add(player.getLocation());
                }
            }
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        if (isThisRace(player)) {
            lightedSources.forEach(location -> setLight(location, 0));
            lightedSources.clear();
        }
    }
}
