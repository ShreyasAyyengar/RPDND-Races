package me.shreyasayyengar.rpdndraces.objects.races;

import me.shreyasayyengar.rpdndraces.objects.abst.AbstractRace;
import me.shreyasayyengar.rpdndraces.objects.interfaces.Appetitless;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityAirChangeEvent;
import org.bukkit.potion.PotionEffectType;

import java.util.List;
import java.util.UUID;

public class Warforged extends AbstractRace implements Appetitless {

    public static List<String> getItemLore() {

        List<String> lore = List.of("Humanoids of steel, wood, and stone. These automatons are sturdy and versatile machines, made to fill any positions in war.");
        List<String> active = List.of("Resistance");
        List<String> passive = List.of("Warforged Perks");

        return RaceUtils.formatLore(lore, active, passive);
    }

    public Warforged(UUID uuid) {
        super(uuid);
    }

    @Override
    public void onSwap() {
        RaceUtils.addPotionEffect(player, PotionEffectType.DAMAGE_RESISTANCE, 15, 2);
    }

    @Override
    public String getName() {
        return "Warforged";
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
    public void onEntityAirChange(EntityAirChangeEvent event) {
        if (event.getEntity() instanceof Player player) {
            if (isThisRace(player)) {
                event.setCancelled(true);
            }
        }
    }
}
