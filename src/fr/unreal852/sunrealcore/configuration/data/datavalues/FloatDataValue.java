package fr.unreal852.sunrealcore.configuration.data.datavalues;

import fr.unreal852.sunrealcore.configuration.ConfigFile;
import fr.unreal852.sunrealcore.configuration.data.IConfigDataValue;

public class FloatDataValue implements IConfigDataValue<Float>
{
    @Override
    public Float readValue(Class<Float> tClass, ConfigFile config, String path)
    {
        return config.getFloat(path);
    }

    @Override
    public void writeValue(Class<Float> tClass, ConfigFile config, String path, Float value)
    {
        config.getYamlConfiguration().set(path, (double)value);
    }
}
