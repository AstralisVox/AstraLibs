package me.astralisvox.astralibs;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;
import java.util.function.Consumer;

public class PluginUpdater {
    private final Plugin plugin;
    private final int resourceId;
    private String latestVersion = "";

    public PluginUpdater(final Plugin plugin, final int resourceId) {
        this.plugin = plugin;
        this.resourceId = resourceId;
    }

    public void getVersion(final Consumer<String> consumer) {
        Bukkit.getScheduler().runTaskAsynchronously(this.plugin, () -> {
            try (final InputStream is = new URL("https://api.spigotmc.org/legacy/update.php?resource=" + this.resourceId).openStream();
                 final Scanner scanner = new Scanner(is)) {
                if (scanner.hasNext()) {
                    consumer.accept(scanner.next());
                    latestVersion = consumer.toString();
                }
            } catch (final IOException ex) {
                Utilities.logInfo(true, "Unable to look for updates: " + ex.getMessage());
            }
        });
    }
}
