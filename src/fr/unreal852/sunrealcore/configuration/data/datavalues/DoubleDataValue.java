package fr.unreal852.sunrealcore.configuration.data.datavalues;

import fr.unreal852.sunrealcore.configuration.CustomFileConfig;
import fr.unreal852.sunrealcore.configuration.data.IConfigDataValue;

public class DoubleDataValue implements IConfigDataValue<Double>
{
    @Override
    public Double readValue(CustomFileConfig config, String path)
    {
        return config.getDouble(path);
    }

    @Override
    public void writeValue(CustomFileConfig config, String path, Double value)
    {
        config.getYamlConfiguration().set(path, value);
    }
}
