package dev.kefany.schedulers;

import java.util.List;
import java.util.Collection;
import java.util.ArrayList;

import org.bukkit.plugin.Plugin;
import org.bukkit.inventory.Inventory;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import dev.kefany.Main;
import dev.kefany.models.GUI.GUIInventory;
import dev.kefany.commands.InvseeCommand;

public final class Scheduler extends BukkitRunnable
{
    public void run() {
        final List<Player> GUIViewers = new ArrayList<Player>(InvseeCommand.GUI_HOLDERS.keySet());
        GUIViewers.removeIf(GUIViewer -> {
            if (!InvseeCommand.NICK_HOLDERS.containsKey(GUIViewer)) {
                return false;
            }
            else {
                final Player player = Bukkit.getPlayer(InvseeCommand.NICK_HOLDERS.get(GUIViewer));
                if (player != null) {
                    final Inventory GUI = new GUIInventory(GUIViewer, player).convertToBukkitInventory();
                    GUIViewer.openInventory(GUI);
                    InvseeCommand.GUI_HOLDERS.put(GUIViewer, GUI);
                    return true;
                }
                else {
                    return false;
                }
            }
        });
        GUIViewers.forEach(GUIViewer -> {
            if (!InvseeCommand.GUI_HOLDERS.containsKey(GUIViewer)) {
                InvseeCommand.GUI_HOLDERS.remove(GUIViewer);
            }
        });
    }
    
    public void startScheduler() {
        this.runTaskTimer(Main.getInstance(), 0L, 20L);
    }
}
