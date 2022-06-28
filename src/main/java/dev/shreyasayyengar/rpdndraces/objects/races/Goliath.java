package dev.shreyasayyengar.rpdndraces.objects.races;

import dev.shreyasayyengar.rpdndraces.objects.abst.AbstractRace;
import org.bukkit.Sound;
import org.bukkit.potion.PotionEffectType;

import java.util.List;
import java.util.UUID;

public class Goliath extends AbstractRace {

    public static List<String> getItemLore() {

        List<String> lore = List.of("Goliaths are massive humanoid Giant-kin. With skin as tough as stone their durability is like no other.");
        List<String> active = List.of("Ground Stomp");
        List<String> passive = List.of();

        return RaceUtils.formatLore(lore, active, passive);
    }

    public Goliath(UUID uuid) {
        super(uuid);
    }

    @Override
    public void onSwap() {
        RaceUtils.addPotionEffect(player, PotionEffectType.DAMAGE_RESISTANCE, 15, 2);
        RaceUtils.stomp(player);
    }

    @Override
    public String getName() {
        return "Goliath";
    }

    @Override
    public void onDisable() {

    }

    @Override
    public int getRaceCooldown() {
        return 10;
    }

    @Override
    public Sound getSound() {
        return Sound.ENTITY_RAVAGER_ATTACK;
    }
}
