package me.shreyasayyengar.rpdndraces.objects.races;

import me.shreyasayyengar.rpdndraces.objects.abst.AbstractTiefling;
import me.shreyasayyengar.rpdndraces.objects.interfaces.RequiredSetup;
import me.shreyasayyengar.rpdndraces.utils.Utils;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.List;
import java.util.UUID;

public class TieflingWinged extends AbstractTiefling implements RequiredSetup {

    public static List<String> getItemLore() {

        List<String> lore = List.of("Through birth or curse, tieflings are a mixture of human and devil", "features, with the wiles to match their more infernal parentage.");
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

    }

    @Override
    public int getRaceCooldown() {
        return 20;
    }

    @Override
    public Sound getSound() {
        return null;
    }

    @Override
    public void setupPlayer() {
        runUnsafeActions(() -> {
            Utils.setElytra(player);

            ItemStack[] contents = player.getInventory().getContents();

            for (int i = 0; i < contents.length; i++) {
                if (contents[i] == null) {

                    if (player.getInventory().getChestplate() != null) {

                        ItemStack chestplate = player.getInventory().getChestplate();

                        if (chestplate.getType() != Material.ELYTRA) {
                            player.getInventory().setItem(i, chestplate);
                            chestplate.setAmount(1);
                            player.getInventory().getChestplate().setType(Material.ELYTRA);
                        }
                    } else player.getInventory().setChestplate(new ItemStack(Material.ELYTRA));

                    break;
                }
            }
        });
    }
}
