package fr.unreal852.sunrealcore.configuration.data;

import fr.unreal852.sunrealcore.configuration.CustomFileConfig;

public interface IConfigDataValue<T>
{
    T readValue(CustomFileConfig config, String path);

    void writeValue(CustomFileConfig config, String path, T value);
}
