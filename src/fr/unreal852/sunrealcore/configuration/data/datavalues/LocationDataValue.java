package fr.unreal852.sunrealcore.configuration.data.datavalues;

import fr.unreal852.sunrealcore.configuration.CustomFileConfig;
import fr.unreal852.sunrealcore.configuration.data.IConfigDataValue;
import org.bukkit.Bukkit;
import org.bukkit.Location;

public class LocationDataValue implements IConfigDataValue<Location>
{
    @Override
    public Location readValue(Class<Location> tClass, CustomFileConfig config, String path)
    {
        String value = config.getString(path);
        if (value.isEmpty() || !value.contains("-"))
            return null;
        String[] split = value.split(":");
        if (split.length != 6)
            return null;
        return new Location(Bukkit.getWorld(split[0]), Double.parseDouble(split[1]), Double.parseDouble(split[2]), Double.parseDouble(split[3]), Float.parseFloat(split[4]), Float.parseFloat(split[5]));
    }

    @Override
    public void writeValue(Class<Location> tClass, CustomFileConfig config, String path, Location location)
    {
        String builder = location.getWorld().getName()
                + ":" + location.getX() + ":" + location.getY() + ":" + location.getZ() + ":"
                + location.getPitch() + ":" + location.getYaw();
        config.getYamlConfiguration().set(path, builder);
    }
}
