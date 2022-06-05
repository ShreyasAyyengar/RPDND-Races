package me.shreyasayyengar.rpdndraces.objects.races;

import me.shreyasayyengar.rpdndraces.objects.abst.AbstractElf;
import org.bukkit.Sound;

import java.util.List;
import java.util.UUID;

public class ElfHigh extends AbstractElf {

    public static List<String> getItemLore() {

        List<String> lore = List.of("Often described as the definition of grace and poise, they are agile and deadly hunters.");
        List<String> active = List.of("Perception");
        List<String> passive = List.of("Nightvision");

        return RaceUtils.formatLore(lore, active, passive);
    }

    public ElfHigh(UUID uuid) {
        super(uuid);
    }

    @Override
    public void onSwap() {
        RaceUtils.getPerception(player);
    }

    @Override
    public String getName() {
        return "Elf-High";
    }

    @Override
    public void onDisable() {

    }

    @Override
    public int getRaceCooldown() {
        return 30;
    }

    @Override
    public Sound getSound() {
        return null;
    }
}
