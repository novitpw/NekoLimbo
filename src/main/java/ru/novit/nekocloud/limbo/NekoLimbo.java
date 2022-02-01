package ru.novit.nekocloud.limbo;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;
import ru.novit.nekocloud.limbo.listeners.LoginListener;
import ru.novit.nekocloud.limbo.listeners.LobbyGuardListener;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NekoLimbo extends JavaPlugin {

    private Thread restart;

    @Getter
    private Location spawnLocation;

    public void onEnable() {
        saveDefaultConfig();

        spawnLocation = stringToLocation(getConfig().getString("spawn"));

        new LobbyGuardListener(this);

        if (!getConfig().getBoolean("login", false))
            new LoginListener(this);

        runStopServer();

    }

    private Location stringToLocation(String loc) {
        String[] locSplit = loc.split(";");
        Location location = new Location(Bukkit.getWorld(locSplit[0]),
                Double.parseDouble(locSplit[1]),
                Double.parseDouble(locSplit[2]),
                Double.parseDouble(locSplit[3]));
        if (locSplit.length == 6) {
            location.setPitch(Float.parseFloat(locSplit[4]));
            location.setYaw(Float.parseFloat(locSplit[5]));
        }
        return location;
    }

    //TODO: переписать этот пиздец
    private void runStopServer() {
        restart = new Thread() {
            private final String time = getConfig().getString("timeRestart");

            @Override
            public void run() {
                try {
                    while (true) {
                        if ((time + ":00").contains(getCurrentTimeStamp()))
                            System.exit(0);

                        Thread.sleep(1000L);
                    }
                }
                catch (InterruptedException ignored) {}
            }
        };
        restart.start();
    }

    @Override
    public void onDisable() {
        if (restart == null)
            return;

        restart.interrupt();
    }

    private String getCurrentTimeStamp() {
        return new SimpleDateFormat("HH:mm:ss").format(new Date());
    }
}

