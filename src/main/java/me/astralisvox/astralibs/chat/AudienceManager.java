package me.astralisvox.astralibs.chat;

import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import org.bukkit.plugin.Plugin;

public final class AudienceManager {
    private static BukkitAudiences audiences;

    private AudienceManager() {}

    public static void init(Plugin plugin) {
        if(audiences == null) {
            audiences = BukkitAudiences.create(plugin);
        }
    }

    public static void shutdown() {
        if(audiences != null) {
            audiences.close();
            audiences = null;
        }
    }

    public static BukkitAudiences getAudiences() {
        if(audiences == null) {
            throw new IllegalStateException("AudienceManager is not initialised");
        }
        return audiences;
    }
}
