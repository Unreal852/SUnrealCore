package fr.unreal852.sunrealcore.configuration.data.datavalues;

import fr.unreal852.sunrealcore.configuration.CustomFileConfig;
import fr.unreal852.sunrealcore.configuration.data.IConfigDataValue;

public class IntegerDataValue implements IConfigDataValue<Integer>
{
    @Override
    public Integer readValue(CustomFileConfig config, String path)
    {
        return config.getInteger(path);
    }

    @Override
    public void writeValue(CustomFileConfig config, String path, Integer value)
    {
        config.getYamlConfiguration().set(path, value);
    }
}
