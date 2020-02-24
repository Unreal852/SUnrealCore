package fr.unreal852.sunrealcore.configuration.data.object;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ConfigValue
{
    /**
     * Config Path.
     *
     * @return Path to value
     */
    String Path();

    /**
     * Value index, use this to define in which order fields must be written to config.
     *
     * @return Value Index
     */
    int Index() default 0;
}
