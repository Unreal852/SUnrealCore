package fr.unreal852.sunrealcore.utils;

import org.bukkit.ChatColor;

public final class StringUtils
{
    /**
     * Returns the substring value without stripping chat colors.
     *
     * @param value The value to substring
     * @param index The substring index
     * @return Substring result
     */
    public static String substringWithColor(String value, int index)
    {
        if (index < 0 || index >= value.length())
            return "";
        String sub = value.substring(index);
        if (sub.isEmpty())
            return "";
        if (value.charAt(index - 1) == '§')
        {
            ChatColor color = ChatColor.getByChar(sub.charAt(0));
            if (color != null)
                return color + sub;
        }
        ChatColor color = ChatColor.WHITE;
        for (int i = 0; i < value.length() - index; i++)
        {
            char curChar = value.charAt(i);
            if (curChar != '§' || (i + 1) >= (value.length() - index))
                continue;
            ChatColor c = ChatColor.getByChar(value.charAt(i + 1));
            if (c != null)
                color = c;
        }
        return color + sub;
    }
}

