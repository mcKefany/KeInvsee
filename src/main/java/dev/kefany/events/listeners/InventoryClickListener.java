package dev.kefany.events.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.inventory.Inventory;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.Listener;

import dev.kefany.commands.InvseeCommand;

public final class InventoryClickListener implements Listener
{
    @EventHandler
    public void onInventoryClick(final InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player)) {
            return;
        }
        final Player player = (Player)event.getWhoClicked();
        final Inventory clickedInventory = event.getClickedInventory();
        if (!InvseeCommand.GUI_HOLDERS.containsKey(player)) {
            return;
        }
        event.setCancelled(true);
    }
}
