package fr.unreal852.sunrealcore.configuration.data.datavalues;

import fr.unreal852.sunrealcore.configuration.ConfigFile;
import fr.unreal852.sunrealcore.configuration.data.IConfigDataValue;

public class IntegerDataValue implements IConfigDataValue<Integer>
{
    @Override
    public Integer readValue(Class<Integer> tClass, ConfigFile config, String path)
    {
        return config.getInteger(path);
    }

    @Override
    public void writeValue(Class<Integer> tClass, ConfigFile config, String path, Integer value)
    {
        config.getYamlConfiguration().set(path, value);
    }
}
