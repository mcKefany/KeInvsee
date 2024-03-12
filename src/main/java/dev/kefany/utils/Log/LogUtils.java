package dev.kefany.utils.Log;

import dev.kefany.utils.chat.ChatUtils;

public class LogUtils {
    public static void enable(String text) {
        System.out.println(ChatUtils.color("&e[KeInvsee] &f" + text));
    }
}
