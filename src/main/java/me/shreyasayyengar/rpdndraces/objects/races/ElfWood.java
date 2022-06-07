package me.shreyasayyengar.rpdndraces.objects.races;

import me.shreyasayyengar.rpdndraces.objects.abst.AbstractElf;
import org.bukkit.Sound;
import org.bukkit.potion.PotionEffectType;

import java.util.List;
import java.util.UUID;

public class ElfWood extends AbstractElf {

    public static List<String> getItemLore() {

        List<String> lore = List.of("Often described as the definition of grace and poise, they are agile and deadly hunters.");
        List<String> active = List.of("Forest Stealth");
        List<String> passive = List.of("Nightvision");

        return RaceUtils.formatLore(lore, active, passive);
    }

    public ElfWood(UUID uuid) {
        super(uuid);
    }

    @Override
    public void onSwap() {
        RaceUtils.addPotionEffect(player, PotionEffectType.INVISIBILITY, 15, 10);
    }

    @Override
    public String getName() {
        return "Elf-Wood";
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
        return Sound.ENTITY_TURTLE_SHAMBLE;
    }
}
