package me.shreyasayyengar.rpdndraces.objects.abst;

import me.shreyasayyengar.rpdndraces.RacesPlugin;
import me.shreyasayyengar.rpdndraces.utils.RaceManager;
import me.shreyasayyengar.rpdndraces.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public abstract class AbstractRace implements Listener {

    private static final Map<UUID, AbstractRace> COOLDOWN_LIST = new HashMap<>();

    protected final UUID uuid;

    protected boolean eventRegistered = false;
    protected BukkitTask task;
    protected Player player;
    protected int cooldownTime;

    public AbstractRace(UUID uuid) {
        this.uuid = uuid;
        this.player = Bukkit.getPlayer(uuid);
        RaceManager.setRace(uuid, this);

        registerEvents();
        setupPlayer();
        runUnsafeActions(() -> player.sendMessage(Utils.colourise(RacesPlugin.PREFIX + " &cYou have chosen " + getName() + "!")));
        runUnsafeActions(this::activatePassiveAbilities);
    }

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

    private void registerEvents() {
        if (!eventRegistered) {
            RacesPlugin.getInstance().getServer().getPluginManager().registerEvents(this, RacesPlugin.getInstance());
            eventRegistered = true;
        }
    }

    public final void setCooldown() {
        COOLDOWN_LIST.put(player.getUniqueId(), this);

        this.cooldownTime = this.getRaceCooldown();
    }

    public final int getCooldownTime() {
        return cooldownTime;
    }

    public final boolean isOnCooldown() {
        return cooldownTime > 0;
    }

    public final boolean checkCooldown() {
        if (isOnCooldown()) {
            player.sendMessage(Utils.colourise(RacesPlugin.PREFIX + " &cYou are currently on cooldown! (" + cooldownTime + "s)"));
            return true;
        }
        return false;
    }

    public final void setPlayer(Player player) {
        this.player = player;
    }

    public final boolean isThisRace(Player player) {
        return RaceManager.getRace(player.getUniqueId()).getName().equalsIgnoreCase(this.getName());
    }

    // ---------------------------

    public abstract void setupPlayer();

    public abstract void onSwap();

    public abstract void activatePassiveAbilities();

    public abstract BukkitTask getTask();

    public abstract String getName();

    public abstract void deactivate();

    public abstract int getRaceCooldown();
}

// TODO figure out all the "COOLDOWN" tab completetion shit and fix all the mfing encapsulation!