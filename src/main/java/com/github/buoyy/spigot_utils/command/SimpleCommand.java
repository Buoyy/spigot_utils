package com.github.buoyy.spigot_utils.command;

import java.util.List;

import org.bukkit.command.CommandSender;

/**
 * Represents a basic command. A wrapper over Bukkit's Command API.
 */
public interface SimpleCommand
{
    /**
     * Executed when this command is called.
     * @param sender - Who sent the command
     * @param args - Arguments provided to the command
     */
    void execute(CommandSender sender, String[] args);

    /**
     * The tab completions to be provided for this command
     * @param args - Arguments provided to the command
     */
    List<String> getTabs(String[] args);
}
