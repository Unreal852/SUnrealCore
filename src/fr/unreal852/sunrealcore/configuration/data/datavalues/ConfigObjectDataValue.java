package fr.unreal852.sunrealcore.configuration.data.datavalues;

import fr.unreal852.sunrealcore.configuration.CustomFileConfig;
import fr.unreal852.sunrealcore.configuration.data.IConfigDataValue;
import fr.unreal852.sunrealcore.configuration.data.object.ConfigValue;
import fr.unreal852.sunrealcore.configuration.data.object.IConfigObject;
import fr.unreal852.sunrealcore.reflection.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

public class ConfigObjectDataValue<T extends IConfigObject> implements IConfigDataValue<T>
{
    @Override
    public T readValue(Class<T> tClass, CustomFileConfig config, String path)
    {
        try
        {
            T instance = tClass.newInstance();
            if (!path.endsWith("."))
                path += ".";
            for (Field field : ReflectionUtils.getAnnotatedFields(ConfigValue.class, tClass, true))
            {
                ConfigValue annotation = field.getAnnotation(ConfigValue.class);
                if (annotation.Path().isEmpty() || !config.exists(path + annotation.Path()))
                    continue;
                field.set(instance, config.get(field.getType(), path + annotation.Path()));
            }
            instance.onConfigLoaded();
            return instance;
        }
        catch (InstantiationException | IllegalAccessException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void writeValue(Class<T> tClass, CustomFileConfig config, String path, T value)
    {
        try
        {
            T instance = tClass.cast(value);
            if (!path.endsWith("."))
                path += ".";
            config.setAutoSave(false);
            List<Field> fields = ReflectionUtils.getAnnotatedFields(ConfigValue.class, tClass, true)
                    .stream().sorted(Comparator.comparingInt(f -> f.getAnnotation(ConfigValue.class).Index())).collect(Collectors.toList());
            for (Field field : fields)
            {
                ConfigValue annotation = field.getAnnotation(ConfigValue.class);
                Object fieldValue = field.get(instance);
                config.set(field.getType(), path + annotation.Path(), fieldValue);
            }
            config.setAutoSave(true);
        }
        catch (IllegalAccessException e)
        {
            e.printStackTrace();
        }
    }
}
