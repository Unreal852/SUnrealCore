package fr.unreal852.sunrealcore.configuration.data.datavalues;

import fr.unreal852.sunrealcore.configuration.CustomFileConfig;
import fr.unreal852.sunrealcore.configuration.data.IConfigDataValue;

public class LongDataValue implements IConfigDataValue<Long>
{
    @Override
    public Long readValue(CustomFileConfig config, String path)
    {
        return config.getLong(path);
    }

    @Override
    public void writeValue(CustomFileConfig config, String path, Long value)
    {
        config.getYamlConfiguration().set(path, value);
    }
}
