package com.github.buoyy.spigot_utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;

/**
 * Represents a custom logger which can be used to log updates about your
 * plugin elegantly to the console.
 */
public class Messenger 
{
    private final ConsoleCommandSender console;
    private final String prefix;

    public Messenger(String prefix)
    {
        console = Bukkit.getConsoleSender();
        this.prefix = String.format("%s[%s]", ChatColor.LIGHT_PURPLE, prefix);
    }

    /**
     * Logs a message for any important information.
     * Colours the message aqua.
     * @param message - The message to log
     */
    public void logInfo(String message)
    {
        console.sendMessage(prefix+ChatColor.DARK_AQUA+"[INFO]: "+ChatColor.AQUA + message);
    }

     /**
     * Logs a message for an occurrence which should happen.
     * Colours the message green.
     * @param message - The message to log
     */
    public void logGood(String message) {
        console.sendMessage(prefix+ChatColor.DARK_GREEN+"[GOOD]: "+ChatColor.GREEN+message);
    }

    /**
     * Logs a message for an occurrence which may cause problems.
     * Colours the message gold.
     * @param message - The message to log
     */
    public void logWarn(String message)
    {
        console.sendMessage(prefix+ChatColor.GOLD+"[INFO]: "+ChatColor.YELLOW + message);
    }

    /**
     * Logs a message for an occurrence which shouldn't have happened.
     * Colours the message red.
     * @param message - The message to log
     */
    public void logError(String message)
    {
        console.sendMessage(prefix+ChatColor.DARK_RED+"[INFO]: "+ChatColor.RED + message);
    }
}
