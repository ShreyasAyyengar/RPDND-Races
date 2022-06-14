package me.shreyasayyengar.rpdndraces.objects.races;

import me.shreyasayyengar.rpdndraces.objects.abst.AbstractElf;
import org.bukkit.Sound;
import org.bukkit.potion.PotionEffectType;

import java.util.List;
import java.util.UUID;

public class ElfSea extends AbstractElf {

    public static List<String> getItemLore() {

        List<String> lore = List.of("Often described as the definition of grace and poise, they are agile and deadly hunters.");
        List<String> active = List.of("Aquatic Travel");
        List<String> passive = List.of("Nightvision");

        return RaceUtils.formatLore(lore, active, passive);
    }

    private boolean potion = false;

    public ElfSea(UUID uuid) {
        super(uuid);
    }

    @Override
    public void onSwap() {
        if (!potion) {
            RaceUtils.addPotionEffect(player, PotionEffectType.WATER_BREATHING, 1000000, 5);
            RaceUtils.addPotionEffect(player, PotionEffectType.DOLPHINS_GRACE, 1000000, 5);
            potion = true;
        } else {
            player.removePotionEffect(PotionEffectType.WATER_BREATHING);
            player.removePotionEffect(PotionEffectType.DOLPHINS_GRACE);
            potion = false;
        }
    }

    @Override
    public String getName() {
        return "Elf-Sea";
    }

    @Override
    public void onDisable() {

    }

    @Override
    public int getRaceCooldown() {
        return 0;
    }

    @Override
    public Sound getSound() {
        return Sound.ENTITY_ZOMBIE_CONVERTED_TO_DROWNED;
    }
}