package dev.shreyasayyengar.rpdndraces.events;

import dev.shreyasayyengar.rpdndraces.objects.abst.AbstractRace;
import dev.shreyasayyengar.rpdndraces.objects.interfaces.SpecialMovement;
import dev.shreyasayyengar.rpdndraces.utils.RaceManager;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMove implements Listener {

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {

        Player player = event.getPlayer();

        if (!RaceManager.hasRace(player.getUniqueId())) return;

        AbstractRace race = RaceManager.getRace(player.getUniqueId());

        if (race instanceof SpecialMovement) {
            if (player.getLocation().getBlockX() == event.getTo().getBlockX() && player.getLocation().getBlockZ() == event.getTo().getBlockZ()) {
                return;
            }

            if (!player.isOnGround()|| player.isSwimming() || player.isInWater()) return;

            player.playSound(player.getLocation(), Sound.ENTITY_HORSE_STEP, 0.3F, 1);
        }
    }
}
