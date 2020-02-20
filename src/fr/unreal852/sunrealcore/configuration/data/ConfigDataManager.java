package fr.unreal852.sunrealcore.configuration.data;

import fr.unreal852.sunrealcore.Main;
import fr.unreal852.sunrealcore.configuration.data.datavalues.*;
import org.bukkit.Location;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ConfigDataManager
{
    private static final ConfigDataManager s_instance = new ConfigDataManager();

    public static <T> IConfigDataValue<T> getDataValue(Class<T> tClass)
    {
        return s_instance.get(tClass);
    }

    private final Map<Class<?>, IConfigDataValue> s_serializers = new HashMap<>();

    public ConfigDataManager()
    {
        register(Boolean.class, new BooleanDataValue());
        register(Double.class, new DoubleDataValue());
        register(Float.class, new FloatDataValue());
        register(Integer.class, new IntegerDataValue());
        register(Long.class, new LongDataValue());
        register(String.class, new StringDataValue());
        register(UUID.class, new UUIDDataValue());
        register(Location.class, new LocationDataValue());
    }

    public <T> void register(Class<T> tClass, IConfigDataValue<T> value)
    {
        s_serializers.remove(tClass);
        s_serializers.put(tClass, value);
    }

    public void unregister(Class<?> tClass)
    {
        s_serializers.remove(tClass);
    }

    public <T> IConfigDataValue<T> get(Class<T> tClass)
    {
        if (!s_serializers.containsKey(tClass))
        {
            Main.getMessenger().sendConsoleMessage("Warning, Missing config data value '" + tClass.getTypeName() + "'.");
            return null;
        }
        return s_serializers.get(tClass);
    }
}
