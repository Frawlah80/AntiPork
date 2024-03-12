package me.frawlah.antipork;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class AntiPorkListener implements Listener {

    private final JavaPlugin plugin;

    public AntiPorkListener(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerConsume(PlayerItemConsumeEvent event) {
        if (event.getItem() != null && event.getItem().getType() == Material.PORKCHOP || event.getItem().getType() == Material.COOKED_PORKCHOP) {
            Player player = event.getPlayer();

            // Check if the consumed item is porkchop

            // Prevent hunger satisfaction
            event.setCancelled(true);

            // Apply potion effects from config.yml
            applyPotionEffects(player);

            // Send customizable message from config.yml
            sendEatMessage(player);

        }
    }

    private void applyPotionEffects(Player player) {
        FileConfiguration config = plugin.getConfig();
        ConfigurationSection effectsSection = config.getConfigurationSection("potionEffects");

        if (effectsSection != null) {
            for (String effectName : effectsSection.getKeys(false)) {
                PotionEffectType effectType = PotionEffectType.getByName(effectName);

                if (effectType != null) {
                    int amplifier = effectsSection.getInt(effectName + ".amplifier");
                    int duration = effectsSection.getInt(effectName + ".duration");

                    PotionEffect effect = new PotionEffect(effectType, duration, amplifier);
                    player.addPotionEffect(effect);
                }
            }
        }
    }

    private void sendEatMessage(Player player) {
        String message = plugin.getConfig().getString("eat-message");

        if (message != null && !message.isEmpty()) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
        }
    }
}
