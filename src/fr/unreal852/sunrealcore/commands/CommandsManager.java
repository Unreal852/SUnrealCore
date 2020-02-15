package fr.unreal852.sunrealcore.commands;

import fr.unreal852.sunrealcore.Main;
import fr.unreal852.sunrealcore.messages.PluginMessenger;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public class CommandsManager implements CommandExecutor
{
    private Map<String, BaseCommand> m_commands = new HashMap<>();

    private String m_baseCommand;

    private JavaPlugin m_plugin;

    private PluginCommand m_pluginCommand;

    private PluginMessenger m_pluginMessenger;

    public CommandsManager(JavaPlugin plugin, String baseCommand)
    {
        m_plugin = plugin;
        m_baseCommand = baseCommand;
        m_pluginCommand = plugin.getCommand(baseCommand);
        if (m_pluginCommand != null)
            m_pluginCommand.setExecutor(this);
    }

    public CommandsManager(JavaPlugin plugin, String m_baseCommand, PluginMessenger pluginMessenger)
    {
        this(plugin, m_baseCommand);
        m_pluginMessenger = pluginMessenger;
    }

    /**
     * Gets command base.
     *
     * @return Command Base
     */
    public String getCommandBase()
    {
        return m_baseCommand;
    }

    /**
     * Gets plugin command.
     *
     * @return Plugin Command
     */
    public PluginCommand getPluginCommand()
    {
        return m_pluginCommand;
    }

    /**
     * Gets plugin messager.
     *
     * @return Plugin Messager
     */
    public PluginMessenger getPluginMessager()
    {
        return m_pluginMessenger == null ? Main.getMessenger() : m_pluginMessenger;
    }

    /**
     * Gets plugin.
     *
     * @return Plugin
     */
    public JavaPlugin getPlugin()
    {
        return m_plugin;
    }

    /**
     * Register command
     *
     * @param command Command to register
     */
    public void registerCommand(BaseCommand command)
    {
        if (m_commands.containsKey(command.getCommandPrefix()))
            return;
        m_commands.put(command.getCommandPrefix(), command);
    }

    /**
     * Unregister command
     *
     * @param command Command to unregister
     */
    public void unregisterCommand(BaseCommand command)
    {
        if (!m_commands.containsKey(command.getCommandPrefix()))
            return;
        m_commands.remove(command.getCommandPrefix());
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args)
    {
        if (command.getName().equalsIgnoreCase(m_baseCommand) && args.length >= 1)
        {
            String cmd = args[0].toLowerCase();
            if (m_commands.containsKey(cmd))
            {
                BaseCommand baseCommand = m_commands.get(cmd);
                if (baseCommand.hasPermission(commandSender))
                {
                    if (baseCommand.execute(commandSender, command, label, args))
                        return true;
                    else
                    {
                        getPluginMessager().sendMessage(commandSender, ChatColor.YELLOW + baseCommand.getCommandUsage());
                        return false;
                    }
                }
                else
                    getPluginMessager().sendMessage(commandSender, ChatColor.RED + "Missing Permissions.");
            }
            else
                getPluginMessager().sendMessage(commandSender, ChatColor.RED + "Unknown Command.");
        }
        else
            getPluginMessager().sendMessage(commandSender, ChatColor.RED + "Missing Arguments.");
        return false;
    }
}
