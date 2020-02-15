package fr.unreal852.sunrealcore.messages;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class PluginMessenger
{
    private JavaPlugin m_plugin;

    private String m_baseMessage;

    public PluginMessenger(JavaPlugin javaPlugin, String baseMessage)
    {
        m_plugin = javaPlugin;
        m_baseMessage = baseMessage.replaceAll("%plugin%", m_plugin.getName());
    }

    /**
     * Returns plugin.
     *
     * @return Plugin
     */
    public JavaPlugin getPlugin()
    {
        return m_plugin;
    }

    /**
     * Returns the base message
     *
     * @return Base Message
     */
    public String getBaseMessage()
    {
        return m_baseMessage;
    }

    /**
     * Broadcast message
     *
     * @param message Message to broadcast
     */
    public void broadcastMessage(String message)
    {
        Bukkit.broadcastMessage(m_baseMessage + message);
    }

    /**
     * Send a message to the specified command sender
     *
     * @param sender  Message receiver
     * @param message Message
     */
    public void sendMessage(CommandSender sender, String message)
    {
        sender.sendMessage(m_baseMessage + message);
    }

    /**
     * Send a console message
     *
     * @param message Message
     */
    public void sendConsoleMessage(String message)
    {
        sendMessage(Bukkit.getConsoleSender(), message);
    }

    /**
     * Send a message to multiple receivers
     *
     * @param message Message
     * @param senders Message Receivers
     */
    public void sendMessage(String message, CommandSender... senders)
    {
        for (CommandSender sender : senders)
            sendMessage(sender, message);
    }
}
