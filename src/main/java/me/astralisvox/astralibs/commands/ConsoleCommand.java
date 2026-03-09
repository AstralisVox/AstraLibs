package me.astralisvox.astralibs.commands;

import me.astralisvox.astralibs.Utilities;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public abstract class ConsoleCommand implements CommandExecutor {

    @Override
    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        if (sender instanceof Player) {
            Utilities.message(sender, "&4Please use console to run this command!");
            return false;
        }
        execute((ConsoleCommandSender) sender, args);
        return true;
    }

    protected abstract void execute(ConsoleCommandSender sender, String[] args);
}
