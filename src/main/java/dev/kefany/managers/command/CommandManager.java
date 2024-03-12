package dev.kefany.managers.command;

import java.util.Map;
import java.util.HashMap;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.command.CommandExecutor;

import dev.kefany.commands.InvseeCommand;

public final class CommandManager
{
    private static final Map<String, CommandExecutor> COMMANDS;
    
    public static void loadCommands(final JavaPlugin plugin) {
        CommandManager.COMMANDS.forEach((commandName, commandExecutor) -> plugin.getCommand(commandName).setExecutor(commandExecutor));
    }
    
    private CommandManager() {
    }
    
    static {
        (COMMANDS = new HashMap<String, CommandExecutor>()).put("invsee", new InvseeCommand());
    }
}
