package me.frawlah.antipork;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class AntiPork extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new AntiPorkListener(this), this);

        this.saveDefaultConfig();
        this.getConfig();

        FileConfiguration config = this.getConfig();

        config.addDefault("potionEffects.POISON.amplifier", 2);
        config.addDefault("potionEffects.POISON.duration", 300 );

        config.addDefault("potionEffects.SLOW.amplifier", 2);
        config.addDefault("potionEffects.SLOW.duration", 300);

        config.addDefault("potionEffects.SLOW_DIGGING.amplifier", 2);
        config.addDefault("potionEffects.SLOW_DIGGING.duration", 300);

        config.addDefault("potionEffects.WEAKNESS.amplifier", 2);
        config.addDefault("potionEffects.WEAKNESS.duration", 300);

        config.addDefault("potionEffects.WITHER.amplifier", 2);
        config.addDefault("potionEffects.WITHER.duration", 300);

        config.addDefault("eat-message", "&cYou have consumed pork! &lHaram!");

        config.options().copyDefaults(true);
        saveConfig();

        getLogger().info("AntiPork has been enabled!");
        getLogger().info("Stay Halal!");

        getCommand("antiporkreload").setExecutor(new Commands(this));
        getCommand("antiporkrel").setExecutor(new Commands(this));
    }

    @Override
    public void onDisable() {
        getLogger().info("AntiPork has been disabled!");
    }

}
