package dev.kefany.managers.config;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.configuration.file.FileConfiguration;

public final class ConfigManager
{
    private static FileConfiguration defaultConfig;
    
    public static void loadConfigs(final JavaPlugin plugin) {
        plugin.saveDefaultConfig();
        ConfigManager.defaultConfig = plugin.getConfig();
    }
    
    private ConfigManager() {
    }
    
    public static FileConfiguration getDefaultConfig() {
        return ConfigManager.defaultConfig;
    }
}
