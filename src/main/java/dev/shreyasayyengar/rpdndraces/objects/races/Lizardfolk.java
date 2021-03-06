package dev.shreyasayyengar.rpdndraces.objects.races;

import dev.shreyasayyengar.rpdndraces.objects.abst.AbstractRace;
import dev.shreyasayyengar.rpdndraces.objects.interfaces.BoostedDiet;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class Lizardfolk extends AbstractRace implements BoostedDiet {

    public static List<String> getItemLore() {

        List<String> lore = List.of("Their dismall swamp homes might lie hundreds of miles from the nearest human settlement, and but the gap between their way of thinking and that of the smooth-skins is far greater.");
        List<String> active = List.of("Hunter");
        List<String> passive = List.of("Scale Defense", "Raw Diet");

        return RaceUtils.formatLore(lore, active, passive);
    }

    public Lizardfolk(UUID uuid) {
        super(uuid);
    }

    @Override
    public void onSwap() {
        RaceUtils.addPotionEffect(player, PotionEffectType.SPEED, 15, 2);
        RaceUtils.addPotionEffect(player, PotionEffectType.INCREASE_DAMAGE, 15, 2);
        RaceUtils.addPotionEffect(player, PotionEffectType.NIGHT_VISION, 15, 2);
    }

    @Override
    public String getName() {
        return "Lizardfolk";
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
        return Sound.ENTITY_FOX_SNIFF;
    }

    @Override
    public Collection<Material> getBoostedFoods() {
        return Arrays.stream(Material.values()).filter(material -> material.name().toLowerCase().contains("raw")).collect(Collectors.toList());
    }

    @Override
    public boolean allowsRaw() {
        return true;
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player player) {
            if (isThisRace(player)) {

                if (event.getCause() == EntityDamageEvent.DamageCause.CONTACT) {
                    event.setCancelled(true);
                }
            }
        }
    }
}
