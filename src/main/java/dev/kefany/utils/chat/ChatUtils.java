package dev.kefany.utils.chat;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.Bukkit;

import net.md_5.bungee.api.ChatColor;

public final class ChatUtils
{
    private static final Pattern PATTERN;
    
    public static String format(String message) {
        if (Bukkit.getVersion().contains("1.16")) {
            for (Matcher matcher = ChatUtils.PATTERN.matcher(message); matcher.find(); matcher = ChatUtils.PATTERN.matcher(message)) {
                final String color = message.substring(matcher.start(), matcher.end());
                message = message.replace(color, ChatColor.of(color) + "");
            }
        }
        return message.replaceAll("&", "§");
    }

    public static String color(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    public static List<String> format(final List<String> message) {
        final List<String> formattedMessage = new ArrayList<String>();
        message.forEach(partMessage -> formattedMessage.add(format(partMessage)));
        return formattedMessage;
    }
    
    private ChatUtils() {
    }
    
    static {
        PATTERN = Pattern.compile("#[a-fA-F0-9]{6}");
    }
}
