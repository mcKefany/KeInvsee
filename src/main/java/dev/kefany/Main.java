package dev.kefany;

import org.bukkit.entity.HumanEntity;
import org.bukkit.plugin.java.JavaPlugin;

import dev.kefany.commands.InvseeCommand;
import dev.kefany.schedulers.Scheduler;
import dev.kefany.managers.listener.ListenerManager;
import dev.kefany.managers.command.CommandManager;
import dev.kefany.managers.config.ConfigManager;
import dev.kefany.utils.Log.LogUtils;

public final class Main extends JavaPlugin
{
    private static Main instance;
    
    public void onEnable() {
        LogUtils.enable("&fDeveloped: &eKefany");
        LogUtils.enable("&fVersion: &e1.0");
        ConfigManager.loadConfigs(Main.instance = this);
        CommandManager.loadCommands(this);
        ListenerManager.loadListeners(this);
        new Scheduler().startScheduler();
    }
    
    public void onDisable() {
        InvseeCommand.GUI_HOLDERS.keySet().forEach(HumanEntity::closeInventory);
        InvseeCommand.GUI_HOLDERS.clear();
    }
    
    public static Main getInstance() {
        return Main.instance;
    }
}
