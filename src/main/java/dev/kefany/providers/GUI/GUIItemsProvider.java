package dev.kefany.providers.GUI;

import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import dev.kefany.managers.config.ConfigManager;
import dev.kefany.models.GUI.GUIItem;

public final class GUIItemsProvider
{
    private static final FileConfiguration config;
    
    public static GUIItem getGUIItemByPath(final String path) {
        final ConfigurationSection itemSection = GUIItemsProvider.config.getConfigurationSection("GUI_VIEW.CONTENT." + path);
        return new GUIItem(itemSection.getString("MATERIAL"), itemSection.getString("DISPLAY_NAME"), itemSection.getStringList("LORE"), itemSection.getIntegerList("SLOTS"));
    }
    
    public static List<GUIItem> getGUIItems() {
        final List<GUIItem> GUIItems = new ArrayList<GUIItem>();
        for (final String key : GUIItemsProvider.config.getConfigurationSection("GUI_VIEW.CONTENT").getKeys(false)) {
            GUIItems.add(getGUIItemByPath(key));
        }
        return GUIItems;
    }
    
    private GUIItemsProvider() {
    }
    
    static {
        config = ConfigManager.getDefaultConfig();
    }
}
