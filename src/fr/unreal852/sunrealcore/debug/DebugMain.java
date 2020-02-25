package fr.unreal852.sunrealcore.debug;

import fr.unreal852.sunrealcore.Main;
import fr.unreal852.sunrealcore.configuration.CustomFileConfig;
import fr.unreal852.sunrealcore.configuration.data.datavalues.ConfigObjectDataValue;
import fr.unreal852.sunrealcore.messages.PluginMessenger;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.event.Listener;

import java.util.UUID;

public final class DebugMain implements Listener
{
    public static final PluginMessenger  MESSENGER = new PluginMessenger(Main.getInstance(), ChatColor.RED + "[DEBUG] " + ChatColor.YELLOW);
    public static final CustomFileConfig CONFIG    = new CustomFileConfig(Main.getInstance(), "/debugconfig.yml", "fr/unreal852/sunrealcore/debug/debugconfig.yml");

    public static void init()
    {
        Bukkit.getPluginManager().registerEvents(new DebugMain(), Main.getInstance());
        debugCustomConfig();
    }

    private static void debugCustomConfig()
    {
        CONFIG.getDataManager().register(MyConfigObject.class, new ConfigObjectDataValue<>());
        CONFIG.setAutoSave(false);

        CONFIG.set(String.class, "test.string", "My String");
        CONFIG.set(Boolean.class, "test.boolean", true);
        CONFIG.set(Double.class, "test.double", 1.75D);
        CONFIG.set(Integer.class, "test.integer", 2);
        CONFIG.set(Float.class, "test.float", 0.3f);
        CONFIG.set(Long.class, "test.long", 14523L);
        CONFIG.set(Location.class, "test.location", new Location(Bukkit.getWorlds().get(0), -180.93018913098916, 69.0, 119.36027887536451, 12.149996f, 217.61688f));
        CONFIG.set(UUID.class, "test.uuid", UUID.fromString("36b3014f-f8cf-42fc-9d0f-924eb5e9af22"));
        CONFIG.save();

        MESSENGER.sendConsoleMessage("String: " + CONFIG.get(String.class, "test.string"));
        MESSENGER.sendConsoleMessage("Boolean: " + CONFIG.get(Boolean.class, "test.boolean"));
        MESSENGER.sendConsoleMessage("Double: " + CONFIG.get(Double.class, "test.double"));
        MESSENGER.sendConsoleMessage("Integer: " + CONFIG.get(Integer.class, "test.integer"));
        MESSENGER.sendConsoleMessage("Float: " + CONFIG.get(Float.class, "test.float"));
        MESSENGER.sendConsoleMessage("Long: " + CONFIG.get(Long.class, "test.long"));
        MESSENGER.sendConsoleMessage("Location: " + CONFIG.get(Location.class, "test.location"));
        MESSENGER.sendConsoleMessage("UUID: " + CONFIG.get(UUID.class, "test.uuid"));
        MESSENGER.sendConsoleMessage("--------------------------------------------------------");
        MyConfigObject myConfigObject = CONFIG.get(MyConfigObject.class, "test");

        CONFIG.set(MyConfigObject.class, "objectSet", myConfigObject);
        CONFIG.save();

        CONFIG.setAutoSave(true);
    }
}
