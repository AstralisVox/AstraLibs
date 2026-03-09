package me.astralisvox.astralibs.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public abstract class GlobalCommand implements CommandExecutor {

    @Override
    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        execute(sender, args);
        return true;
    }

    protected abstract void execute(CommandSender sender, String[] args);
}
