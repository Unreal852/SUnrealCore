package fr.unreal852.sunrealcore.configuration.data;

import fr.unreal852.sunrealcore.Main;
import fr.unreal852.sunrealcore.configuration.data.datavalues.*;
import fr.unreal852.sunrealcore.configuration.data.object.IConfigObject;
import fr.unreal852.sunrealcore.reflection.ReflectionUtils;
import org.bukkit.Location;

import java.util.*;

public class ConfigDataManager
{
    private static final ConfigDataManager s_instance = new ConfigDataManager();

    public static ConfigDataManager getPublicDataManager()
    {
        return s_instance;
    }

    public static <T> IConfigDataValue<T> getDataValue(Class<T> tClass)
    {
        return s_instance.get(tClass);
    }

    private final Map<Class<?>, IConfigDataValue> m_serializers = new HashMap<>();

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
        register(IConfigObject.class, new ConfigObjectDataValue<>());
    }

    public <T> void register(Class<T> tClass, IConfigDataValue<T> value)
    {
        m_serializers.remove(tClass);
        m_serializers.put(tClass, value);
    }

    public void unregister(Class<?> tClass)
    {
        m_serializers.remove(tClass);
    }

    @SuppressWarnings("unchecked")
    public <T> IConfigDataValue<T> get(Class<T> tClass)
    {
        if (!m_serializers.containsKey(tClass))
        {
            if (IConfigObject.class.isAssignableFrom(tClass))
                return m_serializers.get(IConfigObject.class);
            else
            {
                Main.getMessenger().sendConsoleMessage("Warning, Missing config data value '" + tClass.getTypeName() + "'");
                return null;
            }
        }
        return m_serializers.get(tClass);
    }
}
