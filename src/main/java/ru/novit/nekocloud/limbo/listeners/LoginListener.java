package ru.novit.nekocloud.limbo.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import ru.novit.nekocloud.limbo.NekoLimbo;

public class LoginListener implements Listener { //переписать

    private final NekoLimbo nekoLimbo;

    public LoginListener(NekoLimbo nekoLimbo) {
        this.nekoLimbo = nekoLimbo;
        Bukkit.getPluginManager().registerEvents(this, nekoLimbo);
    }

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event) {
        String[] message = event.getMessage().replace("/", "").split(" ");

        if (!nekoLimbo.getConfig().getStringList("allowedCommands").contains(message[0])) {
            event.setCancelled(true);
        }
    }
}