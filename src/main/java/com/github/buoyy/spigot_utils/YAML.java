package com.github.buoyy.spigot_utils;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

/**
 * Represents a YAML file handled by Spigot, usually used for configs 
 * and also as data storage for small plugins.
 * Not to be confused with the default config from JavaPlugin, but be sure to 
 * generate that default config first to ensure that the data folder for the plugin
 * is created correctly.
 */
public class YAML {
    private File file;
    private FileConfiguration config;
    private final Messenger msg;

    /**
     * The constructor for this class.
     * @param pluginName - The plugin's name in whose data folder this
     *                   file will live.
     * @param fileName - The file's name, excluding the .yml extension.
     * @param msg - The messenger object to use for logging. @see Messenger
     */
    public YAML(String pluginName, String fileName, Messenger msg)
    {
        this.msg = msg;
        setup(pluginName, fileName);
    }

    /**
     * Returns the configuration related to this YAML.
     * Functions similarly to JavaPlugin#getConfig() .
     * @return - The configuration
     */
    public FileConfiguration getConfig()
    {
        return this.config;
    }

    /**
     * Saves the YAML file at its location.
     */
    public void save()
    {
        try
        {
            config.save(file);
        }
        catch (IOException e)
        {
            msg.logError(e.getMessage());
        }
    }

    /**
     * Reloads the YAML from disk. Useful when user
     * edits the file at server runtime.
     */
    public void reload()
    {
        config = YamlConfiguration.loadConfiguration(file);
    }

    private void setup(String pluginName, String fileName)
    {
        file = new File(Bukkit.getServer().getPluginManager().getPlugin(pluginName).getDataFolder(), fileName+".yml");
        if (!file.exists())
        {
            msg.logInfo(fileName+" file not found. Creating it...");
            try 
            {
                if (file.createNewFile())
                    msg.logGood(fileName+" file was successfully created.");
            }
            catch (IOException e)
            {
                msg.logError(e.getMessage());
            }
        }
        else 
        {
            msg.logGood(fileName+" file found! Loading...");
        }
        config = YamlConfiguration.loadConfiguration(file);
        msg.logGood("loaded file: "+fileName);
    }

}
