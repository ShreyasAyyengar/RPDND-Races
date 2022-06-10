package me.shreyasayyengar.rpdndraces.objects.races;

import me.shreyasayyengar.rpdndraces.objects.abst.AbstractTiefling;
import me.shreyasayyengar.rpdndraces.objects.interfaces.InventoryRequirement;
import org.bukkit.Sound;
import org.bukkit.util.Vector;

import java.util.List;
import java.util.UUID;

public class TieflingWinged extends AbstractTiefling implements InventoryRequirement {

    public static List<String> getItemLore() {

        List<String> lore = List.of("Through birth or curse, tieflings are a mixture of human and devil features, with the wiles to match their more infernal parentage.");
        List<String> active = List.of("Take Flight", "Wind Ride");
        List<String> passive = List.of("Fire Reistance", "Nightvision");

        return RaceUtils.formatLore(lore, active, passive);
    }

    public TieflingWinged(UUID uuid) {
        super(uuid);
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
    public String getName() {
        return "Tiefling-Winged";
    }

    @Override
    public void onDisable() {
        player.getInventory().setChestplate(null);
    }

    @Override
    public int getRaceCooldown() {
        return 20;
    }

    @Override
    public Sound getSound() {
        return Sound.ENTITY_PHANTOM_FLAP;
    }
}
