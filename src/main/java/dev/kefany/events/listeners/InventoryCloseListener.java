package dev.kefany.events.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.Listener;

import dev.kefany.commands.InvseeCommand;

public final class InventoryCloseListener implements Listener
{
    @EventHandler
    public void onInventoryClose(final InventoryCloseEvent event) {
        if (!(event.getPlayer() instanceof Player)) {
            return;
        }
        final Player player = (Player)event.getPlayer();
        InvseeCommand.GUI_HOLDERS.remove(player);
    }
}
