package fr.unreal852.sunrealcore.configuration.data.datavalues;

import fr.unreal852.sunrealcore.configuration.CustomFileConfig;
import fr.unreal852.sunrealcore.configuration.data.IConfigDataValue;

public class StringDataValue implements IConfigDataValue<String>
{
    @Override
    public String readValue(CustomFileConfig config, String path)
    {
        return config.getString(path);
    }

    @Override
    public void writeValue(CustomFileConfig config, String path, String value)
    {
        config.getYamlConfiguration().set(path, value);
    }
}
