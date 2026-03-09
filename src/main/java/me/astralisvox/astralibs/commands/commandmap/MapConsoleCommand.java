package me.astralisvox.astralibs.commands.commandmap;

import me.astralisvox.astralibs.Utilities;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public abstract class MapConsoleCommand extends Command {

    protected MapConsoleCommand(final String name) {
        super(name);
    }

    @Override
    public boolean execute(final CommandSender sender, final String commandLabel, final String[] args) {
        if (sender instanceof Player) {
            Utilities.message(sender, "&4Please use console to run this command!");
            return false;
        }

        onCommand((ConsoleCommandSender) sender, args);
        return false;
    }

    protected abstract void onCommand(ConsoleCommandSender console, String[] args);
}
