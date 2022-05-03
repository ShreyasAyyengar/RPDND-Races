package me.shreyasayyengar.rpdndraces.objects.races;

import me.shreyasayyengar.rpdndraces.RacesPlugin;
import me.shreyasayyengar.rpdndraces.objects.abst.AbstractRace;
import me.shreyasayyengar.rpdndraces.utils.RaceUtils;
import me.shreyasayyengar.rpdndraces.utils.Utils;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitTask;

import java.util.UUID;

public class Bugbear extends AbstractRace {

    public Bugbear(UUID uuid) {
        super(uuid);
    }

    @Override
    public void setupPlayer() {
    }

    @Override
    public void onSwap() {
        if (isOnCooldown()) {
            player.sendMessage(Utils.colourise(RacesPlugin.PREFIX + " &cYou are currently on cooldown! (" + cooldownTime + "s)"));
            return;
        }

        RaceUtils.boostForward(player, 10);
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 15, 2, false, false, false));
        player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 15, 2, false, false, false));

        setCooldown();
    }

    @Override
    public void activatePassiveAbilities() {
    }

    @Override
    public BukkitTask getTask() {
        return null;
    }

    @Override
    public String getName() {
        return "Bugbear";
    }

    @Override
    public void deactivate() {

    }

    @Override
    public int getRaceCooldown() {
        return 20;
    }
}
