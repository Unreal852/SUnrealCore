package fr.unreal852.sunrealcore.configuration.data;

import fr.unreal852.sunrealcore.configuration.ConfigFile;

public interface IConfigDataValue<T>
{
    T readValue(Class<T> tClass, ConfigFile config, String path);

    void writeValue(Class<T> tClass, ConfigFile config, String path, T value);
}
