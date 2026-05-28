package com.github.buoyy.spigot_utils;

import org.bukkit.ChatColor;

/**
 * A collection of utility functions.
*/
public class Utils 
{
    public static String stripColorCodes(String message) 
    {
        return ChatColor.stripColor(ChatColor.translateAlternateColorCodes('&', message));
    }
}
