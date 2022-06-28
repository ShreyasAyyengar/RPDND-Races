package dev.shreyasayyengar.rpdndraces.objects.races;

import dev.shreyasayyengar.rpdndraces.objects.abst.AbstractRace;
import org.bukkit.Sound;
import org.bukkit.potion.PotionEffectType;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class Halfling extends AbstractRace {

    public static List<String> getItemLore() {

        List<String> lore = List.of("The comforts of home are the goals of most halflings' lives, looking mostly for a place to settle in peace and quiet.");
        List<String> active = List.of("Roll The Stones");
        List<String> passive = List.of();

        return RaceUtils.formatLore(lore, active, passive);
    }

    public Halfling(UUID uuid) {
        super(uuid);
    }

    @Override
    public void onSwap() {
        Collection<PotionEffectType> potions = List.of(
                PotionEffectType.REGENERATION,
                PotionEffectType.INCREASE_DAMAGE,
                PotionEffectType.SPEED,
                PotionEffectType.JUMP,
                PotionEffectType.WATER_BREATHING
        );

        // chose ranomd
        PotionEffectType potion = potions.toArray(new PotionEffectType[0])[(int) (Math.random() * potions.size())];
        RaceUtils.addPotionEffect(player, potion, 15, 3);
    }

    @Override
    public String getName() {
        return "Halfling";
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
        return Sound.ENTITY_ZOMBIE_DESTROY_EGG;
    }
}
