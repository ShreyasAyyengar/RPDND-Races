package me.shreyasayyengar.rpdndraces.objects.races;

import me.libraryaddict.disguise.DisguiseAPI;
import me.libraryaddict.disguise.disguisetypes.PlayerDisguise;
import me.shreyasayyengar.rpdndraces.RacesPlugin;
import me.shreyasayyengar.rpdndraces.menu.MenuItem;
import me.shreyasayyengar.rpdndraces.menu.interfaces.MenuDisplay;
import me.shreyasayyengar.rpdndraces.objects.abst.AbstractRace;
import me.shreyasayyengar.rpdndraces.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class Changeling extends AbstractRace {

    public static final Collection<UUID> TO_REMOVE = new ArrayList<>();

    private static final Map<UUID, Integer> DISGUISES_MAP = new HashMap<>();
    private boolean isLooping = false;

    public static List<String> getItemLore() {

        List<String> lore = List.of("While normally pale faced with eyes black as obsidian, they are masters of", "disguise as they can change their form at will.");
        List<String> active = List.of("Disguise");
        List<String> passive = List.of();

        return RaceUtils.formatLore(lore, active, passive);
    }

    public Changeling(UUID uuid) {
        super(uuid);

        if (!isLooping) {
            checkDisguises();
            isLooping = true;
        }
    }

    private void checkDisguises() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (UUID uuid : DISGUISES_MAP.keySet()) {
                    if (DISGUISES_MAP.get(uuid) == 0) {
                        DISGUISES_MAP.remove(uuid);

                        if (player == null) {
                            TO_REMOVE.add(uuid);
                            return;
                        }

                        DisguiseAPI.undisguiseToAll(player);
                    } else {
                        DISGUISES_MAP.put(uuid, DISGUISES_MAP.get(uuid) - 1);
                    }
                }
            }
        }.runTaskTimer(RacesPlugin.getInstance(), 0, 20);
    }

    @Override
    public void onSwap() {

        MenuDisplay.DisplayBuilder disguisesGUI = MenuDisplay.create(Utils.colourise(RacesPlugin.PREFIX + "&6Choose your disguise!"));

        List<? extends Player> players = Bukkit.getOnlinePlayers().stream().toList();
        for (int i = 0; i < players.size(); i++) {
            ItemStack stack = new ItemStack(Material.PLAYER_HEAD);
            SkullMeta itemMeta = (SkullMeta) stack.getItemMeta();
            itemMeta.setOwningPlayer(players.get(i));
            itemMeta.setDisplayName(Utils.colourise("&6" + players.get(i).getName()));
            itemMeta.setLore(List.of("&7Click to disguise as", "&7" + players.get(i).getName()));
            itemMeta.setLocalizedName("changling." + players.get(i).getName());
            stack.setItemMeta(itemMeta);

            disguisesGUI.set(i, new MenuItem(stack, (whoClicked, menuItem, clickType) -> {

                PlayerDisguise disguise = new PlayerDisguise(stack.getItemMeta().getLocalizedName().split("\\.")[1]);
                disguise.setEntity(player);
                disguise.startDisguise();
                whoClicked.sendMessage(Utils.colourise(RacesPlugin.PREFIX + "&6You are now disguised as &7" + disguise.getName()));
                whoClicked.sendMessage("&cIf you log out, you will be &c7lundisguised!");

                DISGUISES_MAP.put(player.getUniqueId(), 7200);
                whoClicked.closeInventory();
            }));
        }
    }

    @Override
    public String getName() {
        return "Changeling";
    }

    @Override
    public void onDisable() {
        if (DisguiseAPI.isDisguised(player)) {
            DisguiseAPI.undisguiseToAll(player);
        }
    }

    @Override
    public int getRaceCooldown() {
        return 14400;
    }

    @Override
    public Sound getSound() {
        return Sound.ENTITY_PHANTOM_FLAP;
    }
}
