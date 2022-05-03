package me.shreyasayyengar.rpdndraces.objects;

import org.bukkit.Material;
import org.bukkit.inventory.EquipmentSlot;

public interface InventoryRequirement {

    boolean canApply(Material material, EquipmentSlot slot);
}
