package fr.unreal852.sunrealcore.configuration.data.datavalues;

import fr.unreal852.sunrealcore.configuration.ConfigFile;
import fr.unreal852.sunrealcore.configuration.data.IConfigDataValue;

import java.util.UUID;

public class UUIDDataValue implements IConfigDataValue<UUID>
{
    @Override
    public UUID readValue(Class<UUID> tClass, ConfigFile config, String path)
    {
        String value = config.getString(path);
        if (value.isEmpty() || !value.contains("-"))
            return null;
        return UUID.fromString(value);
    }

    @Override
    public void writeValue(Class<UUID> tClass, ConfigFile config, String path, UUID value)
    {
        config.getYamlConfiguration().set(path, value.toString());
    }
}
