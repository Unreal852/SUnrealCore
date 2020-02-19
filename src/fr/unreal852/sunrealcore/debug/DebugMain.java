package fr.unreal852.sunrealcore.debug;

import fr.unreal852.sunrealcore.Main;
import fr.unreal852.sunrealcore.configuration.CustomFileConfig;
import fr.unreal852.sunrealcore.messages.PluginMessenger;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.UUID;

public final class DebugMain implements Listener
{
    private static final PluginMessenger  MESSENGER = new PluginMessenger(Main.getInstance(), ChatColor.RED + "[DEBUG] ");
    public static final  CustomFileConfig CONFIG    = new CustomFileConfig(Main.getInstance(), "/debugconfig.yml", "fr/unreal852/sunrealcore/debug/debugconfig.yml");

    public static void init()
    {
        Bukkit.getPluginManager().registerEvents(new DebugMain(), Main.getInstance());
        UUID uuid = CONFIG.get(UUID.class, "player.unreal");
        MESSENGER.sendConsoleMessage("UUID : " + uuid.toString());
    }

    @EventHandler
    private void onPlayerInteract(PlayerInteractEvent event)
    {
        if (event.getItem().getType() == Material.REDSTONE_ORE)
        {
            CONFIG.set(Location.class, "player.unreal.loc", event.getPlayer().getLocation());
            MESSENGER.sendConsoleMessage("Location : " + CONFIG.get(Location.class, "player.unreal.loc"));
        }
    }
}
