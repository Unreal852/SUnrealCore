package fr.unreal852.sunrealcore.configuration.data.datavalues;

import fr.unreal852.sunrealcore.configuration.ConfigFile;
import fr.unreal852.sunrealcore.configuration.data.IConfigDataValue;

public class LongDataValue implements IConfigDataValue<Long>
{
    @Override
    public Long readValue(Class<Long> tClass, ConfigFile config, String path)
    {
        return config.getLong(path);
    }

    @Override
    public void writeValue(Class<Long> tClass, ConfigFile config, String path, Long value)
    {
        config.getYamlConfiguration().set(path, value);
    }
}
