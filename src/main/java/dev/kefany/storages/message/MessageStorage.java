package dev.kefany.storages.message;

import java.util.List;
import java.util.function.Consumer;

import org.bukkit.command.CommandSender;

import dev.kefany.utils.chat.ChatUtils;
import dev.kefany.managers.config.ConfigManager;

public enum MessageStorage
{
    USAGE("USAGE"), 
    PLAYER_NOT_ONLINE("PLAYER_NOT_ONLINE"), 
    NOT_PERMS("NOT_PERMS");
    
    private final String path;
    
    public void sendMessage(final CommandSender receiver) {
        this.getMessage().forEach(receiver::sendMessage);
    }
    
    private List<String> getMessage() {
        return ChatUtils.format(ConfigManager.getDefaultConfig().getStringList("MESSAGES." + this.path));
    }
    
    private MessageStorage(final String path) {
        this.path = path;
    }
}
