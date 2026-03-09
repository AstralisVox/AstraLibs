package me.astralisvox.astralibs.commands.commandmap;

import me.astralisvox.astralibs.Utilities;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class MapPlayerCommand extends Command {

    protected MapPlayerCommand(final String name) {
        super(name);
    }

    @Override
    public boolean execute(final CommandSender sender, final String commandLabel, final String[] args) {
        if (!(sender instanceof Player)) {
            Utilities.message(sender, "&4You must be a player to use this command!");
            return false;
        }
        onCommand((Player) sender, args);
        return true;
    }

    protected abstract void onCommand(Player player, String[] args);
}
