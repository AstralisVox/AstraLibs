package me.astralisvox.astralibs.commands.commandmap;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public abstract class MapGlobalCommand extends Command {

    protected MapGlobalCommand(final String name) {
        super(name);
    }

    @Override
    public boolean execute(final CommandSender sender, final String commandLabel, final String[] args) {
        onCommand(sender, args);
        return true;
    }

    protected abstract void onCommand(CommandSender commandSender, String[] args);
}
