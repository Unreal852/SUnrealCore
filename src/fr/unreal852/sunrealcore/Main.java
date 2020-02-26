package fr.unreal852.sunrealcore;

import fr.unreal852.sunrealcore.debug.DebugMain;
import fr.unreal852.sunrealcore.messages.PluginMessenger;
import fr.unreal852.sunrealcore.specials.listeners.SpecialInteractListener;
import fr.unreal852.sunrealcore.specials.listeners.SpecialInventoryClickListener;
import fr.unreal852.sunrealcore.specials.listeners.SpecialPlayerItemDropListener;
import fr.unreal852.sunrealcore.specials.listeners.SpecialPlayerItemHeldListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin
{
    private static       PluginMessenger s_messenger;
    private static       Main            s_instance;
    private static final boolean         s_debug = false;

    public static Main getInstance()
    {
        return s_instance;
    }

    public static PluginMessenger getMessenger()
    {
        return s_messenger;
    }

    /**
     * Called when this plugin is being enabled
     */
    public void onEnable()
    {
        s_instance = this;
        s_messenger = new PluginMessenger(this, ChatColor.BLUE + "UnrealCore" + ChatColor.WHITE + " >> ");
        loadDebug();
        registerEvents();
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
        manager.registerEvents(new SpecialInteractListener(), this);
        manager.registerEvents(new SpecialPlayerItemDropListener(), this);
        manager.registerEvents(new SpecialPlayerItemHeldListener(), this);
        manager.registerEvents(new SpecialInventoryClickListener(), this);
    }

    private void loadDebug()
    {
        if (!s_debug)
            return;
        DebugMain.init();
    }
}
