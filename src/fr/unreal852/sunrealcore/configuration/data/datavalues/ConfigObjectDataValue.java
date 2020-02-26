package fr.unreal852.sunrealcore.configuration.data.datavalues;

import fr.unreal852.sunrealcore.Main;
import fr.unreal852.sunrealcore.configuration.ConfigFile;
import fr.unreal852.sunrealcore.configuration.data.IConfigDataValue;
import fr.unreal852.sunrealcore.configuration.data.object.ConfigValue;
import fr.unreal852.sunrealcore.configuration.data.object.IConfigObject;
import fr.unreal852.sunrealcore.reflection.ReflectionUtils;
import fr.unreal852.sunrealcore.utils.JavaUtils;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

public class ConfigObjectDataValue<T extends IConfigObject> implements IConfigDataValue<T>
{
    @Override
    public T readValue(Class<T> tClass, ConfigFile config, String path)
    {
        try
        {
            T instance = tClass.newInstance();
            String objName = path.substring(path.lastIndexOf(".") + 1);
            path = JavaUtils.ensureEndWith(path, ".");
            for (Field field : ReflectionUtils.getAnnotatedFields(ConfigValue.class, tClass, true))
            {
                ConfigValue annotation = field.getAnnotation(ConfigValue.class);
                if (annotation.Path().isEmpty() || !config.exists(path + annotation.Path()))
                    continue;
                Class<?> fieldType = field.getType();
                if (List.class.isAssignableFrom(fieldType) && annotation.Type() != void.class) //Todo: Create datavalue for List, Map etc?
                    field.set(instance, config.getList(annotation.Type(), path + annotation.Path()));
                else if (Map.class.isAssignableFrom(fieldType)&& annotation.Type() != void.class)
                    field.set(instance, config.getMap(annotation.Type(), path + annotation.Path()));
                else
                    field.set(instance, config.get(field.getType(), path + annotation.Path()));
            }
            instance.onConfigLoaded(objName);
            return instance;
        }
        catch (InstantiationException | IllegalAccessException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void writeValue(Class<T> tClass, ConfigFile config, String path, T value)
    {
        try
        {
            T instance = tClass.cast(value);
            path = JavaUtils.ensureEndWith(path, ".");
            config.setAutoSave(false);
            List<Field> fields = ReflectionUtils.getAnnotatedFields(ConfigValue.class, tClass, true)
                    .stream().sorted(Comparator.comparingInt(f -> f.getAnnotation(ConfigValue.class).Index())).collect(Collectors.toList());
            for (Field field : fields)
            {
                ConfigValue annotation = field.getAnnotation(ConfigValue.class);
                Object fieldValue = field.get(instance);
                config.set(field.getType(), path + annotation.Path(), fieldValue);
                // Todo: Support List Set & Map
            }
            config.setAutoSave(true);
        }
        catch (IllegalAccessException e)
        {
            e.printStackTrace();
        }
    }
}
