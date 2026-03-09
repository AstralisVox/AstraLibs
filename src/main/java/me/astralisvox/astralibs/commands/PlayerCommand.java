package me.astralisvox.astralibs.commands;

import me.astralisvox.astralibs.Utilities;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public abstract class PlayerCommand implements CommandExecutor {

    @Override
    public boolean onCommand(final @NotNull CommandSender sender, final Command command, final String label, final String[] args) {
        if (!(sender instanceof Player)) {
            Utilities.message(sender, "&4You must be a player to use this command!");
            return false;
        }
        execute((Player) sender, args);
        return true;
    }

    protected abstract void execute(Player player, String[] args);
}
