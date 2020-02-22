package fr.unreal852.sunrealcore.configuration.data.object;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ConfigObject
{
    /**
     * Config Path
     *
     * @return Path to value
     */
    String Path();
}
