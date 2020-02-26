package fr.unreal852.sunrealcore.utils;

import java.util.concurrent.ThreadLocalRandom;

public final class JavaUtils
{
    public static int randInt(int min, int max)
    {
        return ThreadLocalRandom.current().nextInt(min, max);
    }

    public static double randDouble(double min, double max)
    {
        return ThreadLocalRandom.current().nextDouble(min, max);
    }

    public static long randLong(long min, long max)
    {
        return ThreadLocalRandom.current().nextLong(min, max);
    }

    public static String ensureEndWith(String value, String end)
    {
        if (!value.endsWith(end))
            value += end;
        return value;
    }

    public static <T> T ensureNotNull(T value, T defaultValue)
    {
        if(value == null)
            return defaultValue;
        return value;
    }

    public static boolean randBoolean()
    {
        return ThreadLocalRandom.current().nextBoolean();
    }
}
