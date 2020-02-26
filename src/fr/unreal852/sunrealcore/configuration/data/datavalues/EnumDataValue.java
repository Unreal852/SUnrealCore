package fr.unreal852.sunrealcore.configuration.data.datavalues;

import fr.unreal852.sunrealcore.configuration.ConfigFile;
import fr.unreal852.sunrealcore.configuration.data.IConfigDataValue;

public class EnumDataValue<T extends Enum> implements IConfigDataValue<T>
{
    @Override
    public T readValue(Class<T> tClass, ConfigFile config, String path)
    {
        return (T) Enum.valueOf(tClass, config.getString(path));
    }

    @Override
    public void writeValue(Class<T> tClass, ConfigFile config, String path, T value)
    {
        config.getYamlConfiguration().set(path, value.name());
    }
}
