package me.shreyasayyengar.rpdndraces.objects.races;

import me.shreyasayyengar.rpdndraces.objects.abst.AbstractRace;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.List;
import java.util.UUID;

public class Leonin extends AbstractRace {

    public static List<String> getItemLore() {

        List<String> lore = List.of("The noble and fierce presence of a Leonin is almost as imposing as their roar. They take pleasure in confrontation and contest, proving themselves to be prideful and self-reliant nomads");
        List<String> active = List.of("Daunting Roar");
        List<String> passive = List.of("Natural Weapon");

        return RaceUtils.formatLore(lore, active, passive);
    }

    public Leonin(UUID uuid) {
        super(uuid);
    }

    public void forcePush(Player player, Entity entity) {
        Vector vector = player.getLocation().toVector().subtract(entity.getLocation().toVector());
        double force = Math.abs((10 - player.getLocation().distance(entity.getLocation())) * 0.125);
        // changed 10 from 30
        vector.normalize();
        vector.multiply(-force); //if you want to push the entities away just set to "-force"
        entity.setVelocity(vector);
    }

    @Override
    public void onSwap() {
        player.getLocation().getNearbyLivingEntities(7, 7, 7, livingEntity -> !(livingEntity instanceof Player)).forEach(livingEntity -> {
            player.getWorld().playSound(player.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 1, 1);
            player.getWorld().playSound(player.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_IMPACT, 1, 1);

            forcePush(player, livingEntity);
            livingEntity.damage(0.1, player);
        });
    }


    @Override
    public String getName() {
        return "Leonin";
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
        return Sound.ENTITY_RAVAGER_ROAR;
    }
}
