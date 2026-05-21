package com.github.buoyy.spigot_utils.command;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Represents the main command manager which acts as the sole @see TabExecutor 
 * for any plugin.
 */
public class CommandManager implements TabExecutor
{
    private final JavaPlugin plugin;
    private final Map<String, SimpleCommand> commands;

    public CommandManager(JavaPlugin plugin)
    {
        this.plugin = plugin;
        this.commands = new HashMap<>();
    }

    /**
     * Registers and instantiates a simple command. DO NOT USE FOR SUB-COMMANDS!
     * Use only for "Lone" command or @see BaseCommand
     * @param name - Name of the command to register in-game
     * @param command - the instance of that command
     */
    public void registerCommand(String name, SimpleCommand command)
    {
        commands.put(name, command);
        plugin.getCommand(name).setExecutor(this);
        plugin.getCommand(name).setTabCompleter(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) 
    {
        SimpleCommand cmd = commands.get(label.toLowerCase());
        if (cmd != null)
            cmd.execute(sender, args); 
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) 
    {
        List<String> completions = List.of();
        SimpleCommand cmd = commands.get(label.toLowerCase());
        if (cmd != null)
            completions = cmd.getTabs(args);
        return completions;
    }
}
