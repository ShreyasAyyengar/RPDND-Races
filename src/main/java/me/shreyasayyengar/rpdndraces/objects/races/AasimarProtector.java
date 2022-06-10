package me.shreyasayyengar.rpdndraces.objects.races;

import me.shreyasayyengar.rpdndraces.objects.abst.AbstractAasimar;
import me.shreyasayyengar.rpdndraces.objects.interfaces.InventoryRequirement;

import java.util.List;
import java.util.UUID;

public class AasimarProtector extends AbstractAasimar implements InventoryRequirement {

    public static List<String> getItemLore() {

        List<String> lore = List.of("The descendents of humans marked by Celestials. They often champion the cause of their Celestial parent.");
        List<String> active = List.of("Take Flight");
        List<String> passive = List.of("Healing Hands");

        return RaceUtils.formatLore(lore, active, passive);
    }

    public AasimarProtector(UUID uuid) {
        super(uuid);
    }

    @Override
    public String getName() {
        return "Aasimar-Protector";
    }

    @Override
    public void onDisable() {
        player.getInventory().setChestplate(null);
    }
}
