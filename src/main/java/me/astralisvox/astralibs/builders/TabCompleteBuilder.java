package me.astralisvox.astralibs.builders;

import me.astralisvox.astralibs.Utilities;
import org.bukkit.command.CommandSender;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TabCompleteBuilder {

    private final ArrayList<String> commandList = new ArrayList<>();
    private final CommandSender commandSender;

    public TabCompleteBuilder(final CommandSender commandSender) {
        this.commandSender = commandSender;
    }

    public TabCompleteBuilder checkCommand(final String command, final boolean checkOp, final String... permission ) {
        if (!Utilities.checkPermissions(commandSender, checkOp, permission)) {
            return this;
        }
        commandList.add(command);
        return this;
    }

    public TabCompleteBuilder checkCommand(final List<String> commands, final boolean checkOp, final String... permission ) {
        if (!Utilities.checkPermissions(commandSender, checkOp, permission)) {
            return this;
        }
        commandList.addAll(commands);
        return this;
    }

    public TabCompleteBuilder checkCommand(final String command, final boolean checkOp, final String permission ) {
        if (!Utilities.checkPermission(commandSender, checkOp, permission)) {
            return this;
        }
        commandList.add(command);
        return this;
    }

    public TabCompleteBuilder checkCommand(final List<String> commands, final boolean checkOp, final String permission ) {
        if (!Utilities.checkPermission(commandSender, checkOp, permission)) {
            return this;
        }
        commandList.addAll(commands);
        return this;
    }

    public TabCompleteBuilder addCommand(final String command) {
        commandList.add(command);
        return this;
    }

    public TabCompleteBuilder addCommand(final List<String> commands) {
        commandList.addAll(commands);
        return this;
    }

    public List<String> build(final String args) {
        return StringUtil.copyPartialMatches(args, commandList, new ArrayList<>()).stream().sorted().collect(Collectors.toList());
    }
}
