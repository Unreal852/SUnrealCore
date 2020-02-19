package fr.unreal852.sunrealcore.configuration.data;

import fr.unreal852.sunrealcore.configuration.data.datavalues.LocationDataValue;
import fr.unreal852.sunrealcore.configuration.data.datavalues.UUIDDataValue;
import org.bukkit.Location;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class ConfigDataManager
{
    private static final Map<Class<?>, IConfigDataValue> s_serializers = new HashMap<>();

    static
    {
        register(UUID.class, new UUIDDataValue());
        register(Location.class, new LocationDataValue());
    }

    public static void register(Class<?> tClass, IConfigDataValue value)
    {
        s_serializers.remove(tClass);
        s_serializers.put(tClass, value);
    }

    public static void unregister(Class<?> tClass)
    {
        s_serializers.remove(tClass);
    }

    public static <T> IConfigDataValue<T> get(Class<T> tClass)
    {
        if (!s_serializers.containsKey(tClass))
            return null;
        return s_serializers.get(tClass);
    }
}
