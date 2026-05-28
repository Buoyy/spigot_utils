package com.github.buoyy.spigot_utils.command;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.command.CommandSender;


/**
 * This type of command contains other subcommands, whose functionality is
 * handled by @see SimpleCommand
 * Call the super() method if you have an explicit constructor to a subclass.
 */
public abstract class BaseCommand implements SimpleCommand
{
    protected final Map<String, SimpleCommand> subs;

    public BaseCommand()
    {
        subs = new HashMap<>();
    }

    /**
     * Registers and instantiates a sub command.
     * @param name - Name of the command to register in-game
     * @param command - the instance of that command
     */
    public void registerSubCommand(String name, SimpleCommand command)
    {
        subs.put(name, command);
    }

    /**
     * The method to run when this base command is provided no arguments.
     * @param sender - The command sender for this command
     */
    public abstract void onNoArgs(CommandSender sender);

    @Override
    public void execute(CommandSender sender, String[] args) 
    {
        if (args.length != 0)
        {
            SimpleCommand sub = subs.get(args[0].toLowerCase());
            if (sub != null)
                sub.execute(sender, args);
        }
        else
            onNoArgs(sender);
    }

    @Override
    public List<String> getTabs(String[] args) {
        return subs.keySet().stream().toList();
    }
}
