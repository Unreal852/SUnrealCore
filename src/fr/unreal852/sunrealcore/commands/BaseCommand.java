package fr.unreal852.sunrealcore.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public abstract class BaseCommand
{
    private String m_commandPrefix;

    protected BaseCommand(String commandPrefix)
    {
        m_commandPrefix = commandPrefix;
    }

    /**
     * Gets Command Prefix
     *
     * @return Command Prefix
     */
    public String getCommandPrefix()
    {
        return m_commandPrefix;
    }

    /**
     * Execute this commands.
     * Note: HasPermission is checked before this gets called, this mean that if this is called, the sender has the permission
     *
     * @param sender  Command Sender
     * @param command Command
     * @param label   Command label
     * @param args    Command Arguments
     * @return true if command has been successfully executed, false otherwise.
     */
    public abstract boolean execute(CommandSender sender, Command command, String label, String[] args);

    /**
     * Checks if the command sender has the permission.
     *
     * @param sender Command Sender
     * @return true if the sender has permission, false otherwise.
     */
    public abstract boolean hasPermission(CommandSender sender);

    /**
     * Gets command usage.
     * @return Command Usage
     */
    public abstract String getCommandUsage();
}
