package fr.unreal852.sunrealcore.configuration.data.datavalues;

import fr.unreal852.sunrealcore.configuration.ConfigFile;
import fr.unreal852.sunrealcore.configuration.data.IConfigDataValue;

public class StringDataValue implements IConfigDataValue<String>
{
    @Override
    public String readValue(Class<String> tClass, ConfigFile config, String path)
    {
        return config.getString(path);
    }

    @Override
    public void writeValue(Class<String> tClass, ConfigFile config, String path, String value)
    {
        config.getYamlConfiguration().set(path, value);
    }
}
