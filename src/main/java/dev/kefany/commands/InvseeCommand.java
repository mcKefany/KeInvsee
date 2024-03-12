package dev.kefany.commands;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import dev.kefany.models.GUI.GUIInventory;
import dev.kefany.storages.message.MessageStorage;

public final class InvseeCommand implements CommandExecutor {
    public static final Map<Player, Inventory> GUI_HOLDERS = new HashMap<>();
    public static final Map<Player, String> NICK_HOLDERS = new HashMap<>();

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!feasibilityCheck(sender, args)) {
            return true;
        }
        Player senderPlayer = (Player) sender;
        Inventory GUI = new GUIInventory(senderPlayer, Bukkit.getPlayer(args[0])).convertToBukkitInventory();
        InvseeCommand.GUI_HOLDERS.put(senderPlayer, GUI);
        InvseeCommand.NICK_HOLDERS.put(senderPlayer, args[0]);
        senderPlayer.openInventory(GUI);
        return true;
    }

    private boolean feasibilityCheck(CommandSender sender, String[] args) {
        if (sender instanceof ConsoleCommandSender) {
            return false;
        }
        Player senderPlayer = (Player) sender;
        if (!sender.hasPermission("keinvsee.usage")) {
            MessageStorage.NOT_PERMS.sendMessage(sender);
            return false;
        }
        if (args.length == 0) {
            MessageStorage.USAGE.sendMessage(sender);
            return false;
        }
        if (Bukkit.getPlayer(args[0]) == null && !InvseeCommand.GUI_HOLDERS.containsKey(senderPlayer)) {
            MessageStorage.PLAYER_NOT_ONLINE.sendMessage(sender);
            return false;
        }
        return true;
    }
}
