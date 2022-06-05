package me.shreyasayyengar.rpdndraces.objects.interfaces;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;

/**
 * This interface is used to note that a Race has a boosted diet. A boosted diet contains foods that
 * normally would have adverse effects on a Minecraft Player. This collection can be used to return
 * a Collection of {@link ItemStack}s that can be eaten without the possible adverse effects of the consumed item.
 *
 * @since 1.0
 * @author Shreyas Ayyengar
 */
public interface BoostedDiet {
    Collection<Material> getBoostedFoods();

    boolean justRaw();

    int getFoodPoints();
}
