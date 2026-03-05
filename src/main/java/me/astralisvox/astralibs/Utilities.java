package me.astralisvox.astralibs;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.md_5.bungee.api.ChatMessageType;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;

public class Utilities {
    private static JavaPlugin instance;
    private static final HashMap<String, CommandExecutor> commands = new HashMap<>();
    private static final LegacyComponentSerializer componentSerializer = LegacyComponentSerializer.builder().character('&').hexCharacter('#').hexColors().build();
    private static int resourceId;
    private static String latestVersion = "";

    public static JavaPlugin getInstance() { return instance; }

    public static void setInstance(final JavaPlugin instance) { Utilities.instance = instance; }

    public static TextComponent componentDeserializer(String message) {
        return componentSerializer.deserialize(message);
    }

    public static List<TextComponent> componentDeserializer(List<String> messages) {
        final List<TextComponent> componentList = new ArrayList<>();
        for(String message : messages) {
            componentList.add(componentDeserializer(message));
        }

        return componentList;
    }

    public static String componentSerializerFromString(String value) {
        return componentSerializer.serialize(Component.text(value));
    }

    /**
     * Takes a TextComponent and serializes and colours it into a String
     *
     * @param component (The message to serialize into a string)
     * @return String (The string containing the coloured messages)
     */
    public static String componentSerializer(TextComponent component) {
        return componentSerializer.serialize(component);
    }

    public static String componentSerializer(Component component) {
        return componentSerializer.serialize(component);
    }

    public static List<String> componentSerializer(List<TextComponent> components) {
        final List<String> componentList = new ArrayList<>();
        for(TextComponent component : components) {
            componentList.add(componentSerializer(component));
        }

        return componentList;
    }

    /**
     * Takes a list of strings and turns each string into a component
     * then back into a coloured string and adds it to a new list.
     *
     * @param messages (The list of strings to colourise)
     * @return (The list of strings that have been colourised)
     */
    public static List<String> stringColouriseList(List<String> messages) {
        final List<String> finalMessagesList = new ArrayList<>();

        for(String message : messages) {
            finalMessagesList.add(componentSerializer(componentDeserializer(message)));
        }

        return  finalMessagesList;
    }

    /**
     * Send the target player a message with {@link #componentDeserializer(String)} support.
     * @param player (Needs to be an online player)
     * @param message (String)
     */
    public static void message(final CommandSender player, final TextComponent message) {
        player.sendMessage(componentSerializer.serialize(message));
    }

    /**
     * Send the target player a list of messages with {@link #componentDeserializer(String)} support.
     * @param player (Needs to be an online player)
     * @param messages (String List)
     */
    public static void message(final CommandSender player, final String... messages) {
        for (final String message : messages) {
            player.sendMessage(componentSerializerFromString(message));
        }
    }

    /**
     * Send the target player a list of messages with {@link #componentDeserializer(String)} support.
     * @param player (Needs to be an online player)
     * @param messages (String List)
     */
    public static void message(final CommandSender player, final List<String> messages) {
        for (final String message : messages) {
            message(player, componentDeserializer(message));
        }
    }

    /**
     * Send a message to the entire server with {@link #componentDeserializer(String)} support.
     * @param broadcastConsole (Boolean)
     * @param message (String)
     */
    public static void broadcast(final boolean broadcastConsole, final String message) {
        if(!broadcastConsole) {
            for(Player player : Bukkit.getOnlinePlayers()) {
                message(player, message);
            }
            return;
        }
        Bukkit.getServer().broadcastMessage(componentSerializer(componentDeserializer(message)));
    }

    /**
     * Send a list of messages to the entire server with {@link #componentDeserializer(String)} support.
     * @param messages (String List)
     */
    public static void broadcast(final boolean broadcastConsole, final String... messages) {
        for (final String message : messages) {
            broadcast(broadcastConsole, message);
        }
    }

    /**
     * Send a list of messages to the entire server with {@link #componentDeserializer(String)} support.
     * @param messages (String List)
     */
    public static void broadcast(final boolean broadcastConsole, final List<String> messages) {
        for(final String message: messages) {
            broadcast(broadcastConsole, message);
        }
    }

    /**
     * @param player     (Needs to be an online player)
     * @param permission (String)
     * @param checkOp    (Boolean)
     * @return (True or False)
     */
    public static boolean checkPermission(final CommandSender player, final boolean checkOp, final String permission) {
        if (checkOp) {
            return player.hasPermission(permission) && !player.isOp();
        } else {
            return player.hasPermission(permission);
        }
    }

    public static boolean checkPermissions(final CommandSender player, final boolean checkOp, final String... permissions) {
        for (final String permission : permissions) {
            return checkPermission(player, checkOp, permission);
        }
        return true;
    }

    /**
     * Log an 'info' level message to the server console. Supports colour codes.
     * @param message (String)
     */
    public static void logInfo(final boolean prefix, final String message) {
        final TextComponent messageComponent = Component
                .text("[" + Utilities.getInstance().getDescription().getName() + "]")
                .append(Component.text(" " + message));

        if (prefix) {
            Bukkit.getLogger().info(componentSerializer(messageComponent));
        } else {
            Bukkit.getLogger().info(componentSerializer(Component.text(message)));
        }
    }

    /**
     * Log a list of 'info' level messages to the server console. Supports colour codes.
     * @param messages (String List)
     */
    public static void logInfo(final boolean prefix, final String... messages) {
        for (final String message : messages) {
            logInfo(prefix, message);
        }
    }

    public static void logInfo(final boolean prefix, final List<String> messages) {
        for (final String message : messages) {
            logInfo(prefix, message);
        }
    }

    /**
     * Log a 'warning' level message to the server console. Supports colour codes.
     * @param message (String)
     */
    public static void logWarning(final boolean prefix, final String message) {
        final TextComponent messageComponent = Component
                .text("[" + Utilities.getInstance().getDescription().getName() + "]")
                .append(Component.text(" " + message));

        if (prefix) {
            Bukkit.getLogger().warning(componentSerializer(messageComponent));
        } else {
            Bukkit.getLogger().warning(componentSerializer(Component.text(message)));
        }
    }

    /**
     * Log a list of 'warning' level messages to the server console. Supports colour codes.
     * @param messages (String List)
     */
    public static void logWarning(final boolean prefix, final String... messages) {
        for (final String message : messages) {
            logWarning(prefix, message);
        }
    }

    /**
     * Log a 'severe' level message to the server console. Supports colour codes.
     * @param message (String)
     */
    public static void logSevere(final boolean prefix, final String message) {
        final TextComponent messageComponent = Component
                .text("[" + Utilities.getInstance().getDescription().getName() + "]")
                .append(Component.text(" " + message));

        if (prefix) {
            Bukkit.getLogger().severe(componentSerializer(messageComponent));
        } else {
            Bukkit.getLogger().severe(componentSerializer(Component.text(message)));
        }
    }

    /**
     * Log a list of 'severe' level messages to the server console. Supports colour codes.
     * @param messages (String List)
     */
    public static void logSevere(final boolean prefix, final String... messages) {
        for (final String message : messages) {
            logSevere(prefix, message);
        }
    }

    /**
     * Send the target player an ActionBar message with {@link #componentDeserializer(String)} support.
     * WILL NOT WORK ON VERSIONS PRE-1.9. WILL PRINT A MESSAGE IN CHAT INSTEAD.
     * @param player (Needs to be an online player)
     * @param message (String)
     */
    public static void sendActionBar(final Player player, final String message) {
        try {
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, net.md_5.bungee.api.chat.TextComponent.fromLegacyText(message));
        } catch (final Throwable throwable) {
            message(player, message);
        }
    }

    /**
     * Send the target player an ActionBar message with {@link #componentDeserializer(String)} support.
     * WILL NOT WORK ON VERSIONS PRE-1.9. WILL PRINT A MESSAGE IN CHAT IF SENDMESSAGE IS TRUE.
     * @param player      (Needs to be an online player)
     * @param message     (String)
     * @param sendMessage (boolean)
     */
    public static void sendActionBar(final Player player, final String message, final boolean sendMessage) {
        try {
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, net.md_5.bungee.api.chat.TextComponent.fromLegacyText(message));
        } catch (final Throwable throwable) {
            if (sendMessage) {
                message(player, message);
            }
        }
    }

    /**
     * Send the target player a title message with pre-set animation times. Has {@link #componentDeserializer(String)} support.
     * @param player (Needs to be an online player)
     * @param title (String)
     * @param subtitle (String)
     */
    public static void sendTitle(final Player player, final String title, final String subtitle) {
        player.sendTitle(componentSerializer(Component.text(title)), componentSerializer(Component.text(subtitle)), 20, 20 * 3, 10);
    }

    /**
     * Send the target player a title message with custom animation times. Has {@link #componentDeserializer(String)} support.
     * @param player (Needs to be an online player)
     * @param title (String)
     * @param subtitle (String)
     * @param fadein (Int - Seconds)
     * @param stay (Int - Seconds)
     * @param fadeout (Int - Seconds)
     */
    public static void sendTitle(final Player player, final String title, final String subtitle, final int fadein, final int stay, final int fadeout) {
        player.sendTitle(componentSerializer(Component.text(title)), componentSerializer(Component.text(subtitle)), 20 * fadein, 20 * stay, 20 * fadeout);
    }

    /**
     * Add a potion effect to the target player. Does not support advanced options.
     * @param player (Needs to be an online player)
     * @param effect (PotionEffectType)
     * @param durationInSeconds (Int)
     * @param amplifier (Int - Level)
     */
    public static void addPotionEffect(final Player player, final PotionEffectType effect, final int durationInSeconds, final int amplifier) {
        player.addPotionEffect(new PotionEffect(effect, durationInSeconds * 20, amplifier));
    }

    /**
     * Add a potion effect to the target player. Supports advanced options.
     * @param player (Needs to be an online player)
     * @param effect (PotionEffectType)
     * @param durationInSeconds (Int)
     * @param amplifier (Int - Level)
     * @param ambient (Boolean)
     * @param particles (Boolean)
     * @param icon (Boolean)
     */
    public static void addPotionEffect(final Player player, final PotionEffectType effect, final int durationInSeconds, final int amplifier, final boolean ambient, final boolean particles, final boolean icon) {
        player.addPotionEffect(new PotionEffect(effect, durationInSeconds * 20, amplifier, ambient, particles, icon));
    }

    /**
     * Remove a potion effect from the target player.
     * @param player (Needs to be an online player)
     * @param effect (PotionEffectType)
     */
    public static void removePotionEffect(final Player player, final PotionEffectType effect) {
        player.removePotionEffect(effect);
    }

    public static boolean isPluginPresent(final String plugin) {
        return Bukkit.getServer().getPluginManager().getPlugin(plugin) != null;
    }

    public static void registerCommand(final String command, final CommandExecutor commandExecutor) {
        getInstance().getCommand(command).setExecutor(commandExecutor);
    }

    /**
     * Allows the registering of commands that bypass the plugin.yml.
     * @param command (Create a new instance of the command class)
     */
    public static void registerMapCommand(final Command command) {
        try {
            final Field commandMapField = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            commandMapField.setAccessible(true);

            final CommandMap commandMap = (CommandMap) commandMapField.get(Bukkit.getServer());
            commandMap.register(command.getLabel(), command);

        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Allows the registering of event listeners that are used within plugins.
     * @param listener (Create a new instance of the listener class)
     */
    public static void registerEvent(final Listener listener) {
        Bukkit.getServer().getPluginManager().registerEvents(listener, getInstance());
    }

    public static void registerCommands() {
        for (final String s : commands.keySet()) {
            getInstance().getCommand(s).setExecutor(commands.get(s));
        }
    }

    /**
     * Allows for registering multiple commands at once. Uses {@link #registerMapCommand(Command)}.
     * @param commands (List of new instances of all command classes)
     */
    public static void registerMapCommands(final Command... commands) {
        for (final Command command : commands) {
            registerMapCommand(command);
        }
    }

    /**
     * Allows for registering multiple event listeners at once. Uses {@link #registerEvent(Listener)}.
     * @param listeners (List of new instances of all event listener classes)
     */
    public static void registerEvents(final Listener... listeners) {
        for (final Listener listener : listeners) {
            registerEvent(listener);
        }
    }

    public static HashMap<String, CommandExecutor> setCommand() {
        return commands;
    }

    /**
     * Allows for commands to be executed directly from console
     * @param command The command to be executed from the console
     */
    public static void executeConsoleCommand(final String command) {
        Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), command);
    }

    public static void getVersion(final Consumer<String> consumer) {
        Bukkit.getScheduler().runTaskAsynchronously(instance, () -> {
            try (final InputStream is = new URL("https://api.spigotmc.org/legacy/update.php?resource=" + resourceId).openStream();
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
