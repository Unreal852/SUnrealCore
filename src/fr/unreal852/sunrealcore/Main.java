package fr.unreal852.sunrealcore;

import fr.unreal852.sunrealcore.messages.PluginMessenger;
import fr.unreal852.sunrealcore.specialitems.listeners.SpecialItemInteractListener;
import fr.unreal852.sunrealcore.specialitems.listeners.SpecialItemInventoryClickListener;
import fr.unreal852.sunrealcore.specialitems.listeners.SpecialItemPlayerItemDropListener;
import fr.unreal852.sunrealcore.specialitems.listeners.SpecialItemPlayerItemHeldListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin
{
    private static PluginMessenger s_messager;
    private static Main            s_instance;

    public static Main getInstance()
    {
        return s_instance;
    }

    public static PluginMessenger getMessenger()
    {
        return s_messager;
    }

    /**
     * Called when this plugin is being enabled
     */
    public void onEnable()
    {
        s_instance = this;
        s_messager = new PluginMessenger(this, ChatColor.BLUE + "UnrealCore" + ChatColor.WHITE + " >> ");
        /*
        RegisterCommands();
        RegisterEvents(); */
    }

    /**
     * Called when this plugin is being disabled
     */
    public void onDisable()
    {

    }

    /**
     * Register Events
     */
    private void registerEvents()
    {
        PluginManager manager = Bukkit.getPluginManager();
        manager.registerEvents(new SpecialItemInteractListener(), this);
        manager.registerEvents(new SpecialItemPlayerItemDropListener(), this);
        manager.registerEvents(new SpecialItemPlayerItemHeldListener(), this);
        manager.registerEvents(new SpecialItemInventoryClickListener(), this);
    }
}
