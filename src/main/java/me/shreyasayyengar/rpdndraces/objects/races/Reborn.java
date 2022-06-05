package me.shreyasayyengar.rpdndraces.objects.races;

import me.shreyasayyengar.rpdndraces.objects.abst.AbstractRace;
import me.shreyasayyengar.rpdndraces.objects.interfaces.Appetitless;
import org.bukkit.Sound;
import org.bukkit.potion.PotionEffectType;

import java.util.List;
import java.util.UUID;

public class Reborn extends AbstractRace implements Appetitless {

    private boolean nightVision = false;

    public static List<String> getItemLore() {

        List<String> lore = List.of("Ashen flesh and bloodless veins and usually showing the signs of the cause", "of their death upon their bodies. Reborn are a race that has died... yet live.", "Some are crafted by magic or science. ");
        List<String> active = List.of("Nightvision", "Regeneration 2");
        List<String> passive = List.of("Dead Man's Needs");

        return RaceUtils.formatLore(lore, active, passive);
    }

    public Reborn(UUID uuid) {
        super(uuid);
    }

    @Override
    public void onSwap() {
        if (!nightVision) {
            RaceUtils.addPotionEffect(player, PotionEffectType.NIGHT_VISION, 1000000, 255);
            nightVision = true;
        } else {
            player.removePotionEffect(PotionEffectType.NIGHT_VISION);
            nightVision = false;
        }

        RaceUtils.addPotionEffect(player, PotionEffectType.REGENERATION, 15, 2);
        // TODO check with the spreadsheet for regeneration
    }

    @Override
    public String getName() {
        return "Reborn";
    }

    @Override
    public void onDisable() {

    }

    @Override
    public int getRaceCooldown() {
        return 20; // TODO confer with the spreadsheet
    }

    @Override
    public Sound getSound() {
        return null;
    }
}
