package fr.unreal852.sunrealcore.reflection;

import com.google.common.collect.Sets;
import fr.unreal852.sunrealcore.configuration.data.object.ConfigValue;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Set;

public final class ReflectionUtils
{
    public static <T> T newInstance(Class<T> tClass)
    {
        try
        {
            return tClass.newInstance();
        }
        catch (IllegalAccessException | InstantiationException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public static Collection<Field> getAnnotatedFields(Class<? extends ConfigValue> annotation, Class<?> tClass, boolean setAccessible)
    {
        Set<Field> fields = Sets.newHashSet();
        if (annotation == null || tClass == null)
            return fields;
        for (Field field : tClass.getDeclaredFields())
        {
            if (field.getAnnotation(annotation) == null)
                continue;
            if (!field.isAccessible() && setAccessible)
                field.setAccessible(true);
            fields.add(field);
        }
        return fields;
    }
}
