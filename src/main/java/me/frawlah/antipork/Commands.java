package me.frawlah.antipork;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class Commands implements CommandExecutor {

    private final JavaPlugin plugin;

    public Commands(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("antiporkreload") || label.equalsIgnoreCase("antiporkrel")) {
            if (sender.hasPermission("antipork.reload")) {
                Bukkit.getServer().getPluginManager().disablePlugin(plugin);
                Bukkit.getServer().getPluginManager().enablePlugin(plugin);
                sender.sendMessage(ChatColor.GREEN + "AntiPork configuration reloaded!");
                return true;
            } else {
                sender.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
                return true;
            }
        }
        return false;
    }
}

