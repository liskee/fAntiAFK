package eu.foxmc.fAntiAfk;

import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.UUID;

public class Main extends JavaPlugin {

    static HashMap<UUID, LocalDateTime> afk = new HashMap<>();

    private File customConfigFile;
    private FileConfiguration customConfig;

    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new AntiAfk_Move(), this);
        Bukkit.getPluginManager().registerEvents(new AntiAfk_DropItem(), this);
        Bukkit.getPluginManager().registerEvents(new AntiAfk_Sneak(), this);
        Bukkit.getPluginManager().registerEvents(new AntiAfk_Chat(), this);
        Bukkit.getPluginManager().registerEvents(new AntiAfk_Cmd(), this);
        Bukkit.getPluginManager().registerEvents(new AntiAfk_Join(), this);
        Bukkit.getPluginManager().registerEvents(new Quit(), this);
        createCustomConfig();

        long afkTime = this.getCustomConfig().getLong("afk-time");
        boolean kickForAfk = this.getCustomConfig().getBoolean("kick-for-afk");
        String kickMessage = this.getCustomConfig().getString("kick-message");
        long minimumPlayercountToKick = this.getCustomConfig().getLong("minimum-playercount-to-kick");
        String permissionKickBypass = this.getCustomConfig().getString("permission-kick-bypass");

        /* Checking if player is afk and kicking him. */
        BukkitScheduler scheduler = getServer().getScheduler();
        scheduler.scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                for(Player p : Bukkit.getOnlinePlayers()){

                    LocalDateTime wentafk = afk.get(p.getUniqueId());
                    LocalDateTime now = LocalDateTime.now();
                    long afksecs = ChronoUnit.SECONDS.between(wentafk, now);

                    if (afksecs > afkTime) {

                        if (kickForAfk) {

                            int onlineplayers = Bukkit.getOnlinePlayers().size();

                            if (onlineplayers >= minimumPlayercountToKick){

                                if (!(p.hasPermission(permissionKickBypass)) && !(p.isOp())) {

                                    p.kickPlayer(kickMessage);

                                }
                            }
                        }
                    }
                }
            }
        }, 0L, 20L);
    }

    public void onDisable() {



    }

    public FileConfiguration getCustomConfig() {
        return this.customConfig;
    }

    private void createCustomConfig() {
        customConfigFile = new File(getDataFolder(), "config.yml");
        if (!customConfigFile.exists()) {
            customConfigFile.getParentFile().mkdirs();
            saveResource("custom.yml", false);
        }

        customConfig= new YamlConfiguration();
        try {
            customConfig.load(customConfigFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }
}

