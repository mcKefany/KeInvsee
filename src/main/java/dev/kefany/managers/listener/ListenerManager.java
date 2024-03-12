package dev.kefany.managers.listener;

import java.util.Arrays;
import java.util.List;

import org.bukkit.plugin.Plugin;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.Listener;

import dev.kefany.events.listeners.InventoryClickListener;
import dev.kefany.events.listeners.InventoryCloseListener;

public final class ListenerManager
{
    public static final List<Listener> LISTENERS;
    
    public static void loadListeners(final JavaPlugin plugin) {
        ListenerManager.LISTENERS.forEach(listener -> Bukkit.getPluginManager().registerEvents(listener, plugin));
    }
    
    static {
        LISTENERS = Arrays.asList((Listener)new InventoryCloseListener(), (Listener)new InventoryClickListener());
    }
}
