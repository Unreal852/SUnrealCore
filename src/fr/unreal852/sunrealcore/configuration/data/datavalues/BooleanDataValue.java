package fr.unreal852.sunrealcore.configuration.data.datavalues;

import fr.unreal852.sunrealcore.configuration.ConfigFile;
import fr.unreal852.sunrealcore.configuration.data.IConfigDataValue;

public class BooleanDataValue implements IConfigDataValue<Boolean>
{
    @Override
    public Boolean readValue(Class<Boolean> tClass, ConfigFile config, String path)
    {
        return config.getBoolean(path);
    }

    @Override
    public void writeValue(Class<Boolean> tClass, ConfigFile config, String path, Boolean value)
    {
        config.getYamlConfiguration().set(path, value);
    }
}
