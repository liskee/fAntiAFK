package eu.foxmc.fAntiAfk;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new AntiAfk(), this);
        loadConfig();
    }

    public void onDisable() {

    }

    public void loadConfig() {
        // Time to kick for afk in minutes
        getConfig().addDefault("afk-time", 1);
        getConfig().options().copyDefaults(true);
        saveConfig();
    }
 }
