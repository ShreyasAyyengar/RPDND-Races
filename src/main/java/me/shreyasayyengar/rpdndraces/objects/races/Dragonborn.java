package me.shreyasayyengar.rpdndraces.objects.races;

import de.slikey.effectlib.EffectManager;
import de.slikey.effectlib.effect.DragonEffect;
import me.shreyasayyengar.rpdndraces.RacesPlugin;
import me.shreyasayyengar.rpdndraces.objects.abst.AbstractRace;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.List;
import java.util.UUID;

public class Dragonborn extends AbstractRace {

    public static List<String> getItemLore() {

        List<String> lore = List.of("A race of strong visual semblance to dragons, their strong builds and colorful scales are accompanied by the ability to exhale devastating elemental breaths.");
        List<String> active = List.of("Dragon's Breath");
        List<String> passive = List.of("Scale Defense");

        return RaceUtils.formatLore(lore, active, passive);
    }

    public Dragonborn(UUID uuid) {
        super(uuid);
    }

    @Override
    public void onSwap() {
        createParticles();
    }

    private void createParticles() {
        EffectManager effectManager = RacesPlugin.getEffectManager();
        DragonEffect effect = new DragonEffect(effectManager);
        Location location = player.getLocation().add(0, 1.5, 0);

        location.setDirection(player.getEyeLocation().getDirection());

        effect.setLocation(location);
        effect.duration = 1500;
        effect.start();
    }

    @Override
    public String getName() {
        return "Dragonborn";
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
        return Sound.ENTITY_ENDER_DRAGON_GROWL;
    }

    @EventHandler
    private void onEntityDamage(EntityDamageEvent event) {

        if (event.getEntityType() == EntityType.PLAYER) {
            Player player = (Player) event.getEntity();
            if (isThisRace(player)) {

                switch (event.getCause()) {
                    case CONTACT, FIRE, FIRE_TICK, LAVA -> event.setCancelled(true);
                }
            }
        }
    }
}
