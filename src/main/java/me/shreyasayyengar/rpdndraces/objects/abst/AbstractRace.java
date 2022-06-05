package me.shreyasayyengar.rpdndraces.objects.abst;

import me.shreyasayyengar.rpdndraces.RacesPlugin;
import me.shreyasayyengar.rpdndraces.events.HandSwap;
import me.shreyasayyengar.rpdndraces.objects.interfaces.PassiveAbilities;
import me.shreyasayyengar.rpdndraces.objects.interfaces.RequiredSetup;
import me.shreyasayyengar.rpdndraces.objects.interfaces.TaskedRace;
import me.shreyasayyengar.rpdndraces.utils.RaceManager;
import me.shreyasayyengar.rpdndraces.utils.Utils;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * AbstractRace is the base class for all races. It serves as a central hub to most of the operations that
 * a race uses. All races are instantiated with the same constructor with the parameter of {@link UUID}.
 * This abstract class also implements {@link Listener} to allow any subclasses to create their own event handling.
 * <p></p>
 *
 * @author Shreyas Ayyengar
 * @since 1.0
 */
public abstract class AbstractRace implements Listener {

    private static final Map<UUID, AbstractRace> COOLDOWN_LIST = new HashMap<>();

    protected final UUID uuid;

    protected boolean eventRegistered = false;
    protected boolean handSwapEnabled = true;
    protected BukkitTask registeredTask;
    protected Player player;
    protected int cooldownTime;

    /**
     * startTask is a static method that is called by the Main class to start the countdown manager. All cooldowns
     * are handled by the startTask method.
     */
    public static void startTask() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Map.Entry<UUID, AbstractRace> entry : COOLDOWN_LIST.entrySet()) {

                    if (entry.getValue().cooldownTime > 0) {
                        entry.getValue().cooldownTime--;
                    }

                    if (entry.getValue().getCooldownTime() == 0) {
                        COOLDOWN_LIST.remove(entry.getKey());

                        entry.getValue().runUnsafeActions(() -> entry.getValue().player.sendMessage(Utils.colourise(RacesPlugin.PREFIX + " &cYour " + entry.getValue().getName() + " cooldown has ended!")));
                    }
                }
            }
        }.runTaskTimerAsynchronously(RacesPlugin.getInstance(), 0, 20);
    }

    public AbstractRace(UUID uuid) {
        this.uuid = uuid;
        this.player = Bukkit.getPlayer(uuid);
        RaceManager.setRace(uuid, this);

        if (this instanceof TaskedRace taskedRace) {
            setRegisteredTask(taskedRace.getRaceTask());// TODO possibly running twice?
        }

        if (this instanceof RequiredSetup setup) {
            setup.setupPlayer();
        }

        runUnsafeActions(() -> {
            if (this instanceof PassiveAbilities passiveAbilities) {
                passiveAbilities.activatePassiveAbilities();
            }
        });

        registerEvents();
        runUnsafeActions(() -> player.sendMessage(Utils.colourise(RacesPlugin.PREFIX + " &cYou have chosen " + getName() + "!")));
    }

    private void registerEvents() {
        if (!eventRegistered) {
            RacesPlugin.getInstance().getServer().getPluginManager().registerEvents(this, RacesPlugin.getInstance());
            eventRegistered = true;
        }
    }

    /**
     * The runUnsafeActions method is used to run actions that are not safe to run, due to the possibility of
     * <strong>the {@link Player} being null.</strong> This method can be used inside BukkitRunnables or methods that are part of the
     * {@link TaskedRace}, {@link PassiveAbilities}, and {@link RequiredSetup} classes. A void action is required to be passed in, which will
     * be run if the {@link Player} is not null. If the {@link Player} is null, nothing will be run. <p></p>
     * This method is useful for things like activating passive abilities, or sending messages to the player, etc,
     * without having to worry about null pointers. <i>(and tbh im lazy af :>)</i>
     *
     * @param action is the action to run.
     */
    protected final void runUnsafeActions(Runnable action) {
        if (player != null) {
            if (Bukkit.getPlayer(uuid) != null) {
                this.player = Bukkit.getPlayer(uuid);
                action.run();
            }
        } else {
            System.out.println("Could not perform actions since player was null " + uuid);
        }
    }

    public final boolean checkCooldown() {
        if (cooldownTime > 0) {
            player.sendMessage(Utils.colourise(RacesPlugin.PREFIX + " &cYou are currently on cooldown! (" + cooldownTime + "s)"));
            return false;
        }
        return true;
    }

    public final boolean isThisRace(Player player) {
        return RaceManager.getRace(player.getUniqueId()).getName().equalsIgnoreCase(this.getName());
    }

    public final boolean isHandSwapEnabled() {
        return handSwapEnabled;
    }

    public final void setRegisteredTask(BukkitTask task) {
        this.registeredTask = task;
    }

    public final void setPlayer(Player player) {
        this.player = player;
    }

    public final void setCooldown() {
        if (getRaceCooldown() == 0) {
            return;
        }

        COOLDOWN_LIST.put(player.getUniqueId(), this);
        this.cooldownTime = this.getRaceCooldown();
    }

    public final int getCooldownTime() {
        return cooldownTime;
    }

    public final BukkitTask getRegisteredTask() {
        return registeredTask;
    }

    // ----------------------------------------------------------------------------------------------------------------

    /**
     * The onSwap method is called by the {@link PlayerSwapHandItemsEvent} by the BukkitAPI. Each subclass of {@link AbstractRace}
     * should override this method to do perform specific actions when the player swaps their hand items. The {@link AbstractRace#player} variable can be used
     * unchecked in this method as they are not null when called by the event. <p></p>
     * Anything regarding cooldowns should <b>NOT be handled in this method</b>. Any data, countdowns or managers relating to cooldowns are managed directly
     * within the {@link HandSwap} class, allowing for this method for the sole purpose of performing actions that are not related to cooldowns and more specifically
     * for the actual race.
     */
    public abstract void onSwap();

    public abstract String getName();

    public abstract void onDisable();

    public abstract int getRaceCooldown();

    public abstract Sound getSound();

    protected static class RaceUtils {

        public static void teleportForward(Player player, int blocks) {
            player.getWorld().spawnParticle(Particle.SPELL_WITCH, player.getLocation().add(0, 0.5, 0), 5);
            Vector direction = player.getEyeLocation().getDirection().multiply(blocks).setY(0.5);
            Location directionLoc = player.getLocation().add(direction);
            player.teleport(directionLoc);
            player.playSound(player.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1, 1);
            player.getWorld().spawnParticle(Particle.SPELL_WITCH, player.getLocation().add(0, 0.5, 0), 5);
        }

        public static void pushForward(Player player, int blocks) {
            player.getWorld().spawnParticle(Particle.BLOCK_DUST, player.getLocation().add(0, 0.5, 0), 15);
            Vector direction = player.getEyeLocation().getDirection().multiply(blocks).setY(0.5);
            player.setVelocity(direction);
            player.playSound(player.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1, 1);
            player.getWorld().spawnParticle(Particle.BLOCK_DUST, player.getLocation().add(0, 0.5, 0), 15);
        }

        public static void addPotionEffect(Player player, PotionEffectType type, int seconds, int amplifier) {
            player.addPotionEffect(new PotionEffect(type, seconds * 20, amplifier - 1, false, false, false));
        }

        public static void getPerception(Player player) {
            AtomicInteger number = new AtomicInteger();
            player.getLocation().getNearbyPlayers(30).forEach(p -> {
                if (player.canSee(p)) {
                    number.getAndIncrement();
                }
            });

            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§o§cThere are §b" + (number.get() - 1) + "§c players nearby!"));
        }

        public static List<String> formatLore(List<String> lore, List<String> active, List<String> passive) {

            List<String> formattedLore = new ArrayList<>(lore);
            formattedLore.add("");
            formattedLore.add("&c[Active Abilities]");
            formattedLore.addAll(active.stream().map(activeLore -> "&6" + active).toList());
            formattedLore.add("");
            formattedLore.add("&6[Passive Abilities]");
            formattedLore.addAll(passive.stream().map(passiveLore -> "&e" + passive).toList());

            return formattedLore.stream().map(Utils::colourise).toList();
        }
    }
}

// TODO: configure how inventory requirements will work (Aarakocra, TieflingWinged mainly)
