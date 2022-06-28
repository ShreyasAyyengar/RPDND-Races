package dev.shreyasayyengar.rpdndraces.objects.races;

import dev.shreyasayyengar.rpdndraces.objects.abst.AbstractGenasi;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import static org.bukkit.Material.*;

public class GenasiEarth extends AbstractGenasi {

    private boolean tilled = true;

    public static List<String> getItemLore() {

        List<String> lore = List.of("A rare humanoid touched by the elemental planes, Genasi are typically offsprings of genies and mortals.");
        List<String> active = List.of("Tilled Earth");
        List<String> passive = List.of("Earth Heritage");

        return RaceUtils.formatLore(lore, active, passive);
    }

    public GenasiEarth(UUID uuid) {
        super(uuid);
    }

    @Override
    public Collection<EntityDamageEvent.DamageCause> getImmuneDamageCauses() {
        return new HashSet<>();
    }

    @Override
    public boolean canWaterBreath() {
        return false;
    }

    @Override
    public void onSwap() {
        tilled = !tilled;
    }

    @Override
    public String getName() {
        return "Genasi-Earth";
    }

    @Override
    public int getRaceCooldown() {
        return 0;
    }

    @Override
    public Sound getSound() {
        return Sound.ITEM_HOE_TILL;
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (isThisRace(player)) {

            Location downOne = player.getLocation().clone().subtract(0, 1, 0);
            Location location = player.getLocation();

            if (location.getBlock().getType() == SOUL_SAND) {
                player.setWalkSpeed(0.38F);
            } else if (location.getBlock().getType() == SWEET_BERRY_BUSH) {
                player.setWalkSpeed(0.58F);
            } else if (location.getBlock().getType() == HONEY_BLOCK) {
                player.setWalkSpeed(0.38F);
            } else if (location.getBlock().getType() == COBWEB) {
                player.setWalkSpeed(1);
            } else {
                switch (downOne.getBlock().getType()) {
                    case SOUL_SOIL -> player.setWalkSpeed(0.22F);
                    case AIR -> {
                    } // prevent FOV from changing
                    default -> player.setWalkSpeed(0.2F);
                }
            }
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (isThisRace(event.getPlayer())) {

            if (event.getClickedBlock() == null) return;
            if (event.getClickedBlock().getType() != Material.GRASS_BLOCK) return;

            Block clickedBlock = event.getClickedBlock();
            if (tilled) {
                clickedBlock.setType(FARMLAND);
            }
        }
    }
}