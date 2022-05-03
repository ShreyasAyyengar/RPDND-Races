package me.shreyasayyengar.rpdndraces.objects.races.elf;

import me.shreyasayyengar.rpdndraces.objects.abst.AbstractElf;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.scheduler.BukkitTask;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class ElfHigh extends AbstractElf {

    public ElfHigh(UUID uuid) {
        super(uuid);
    }

    @Override
    public void setupPlayer() {
    }

    @Override
    public void onSwap() {

        if (checkCooldown()) {

            AtomicInteger number = new AtomicInteger();
            player.getLocation().getNearbyPlayers(30).forEach(p -> {
                if (player.canSee(p)) {
                    number.getAndIncrement();
                }
            });

            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§o&cThere are §b" + number.get() + "§c players nearby!"));

            setCooldown();
        }

    }

    @Override
    public BukkitTask getTask() {
        return null;
    }

    @Override
    public String getName() {
        return "Elf-High";
    }

    @Override
    public void deactivate() {

    }

    @Override
    public int getRaceCooldown() {
        return 30;
    }
}
