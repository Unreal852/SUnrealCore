package fr.unreal852.sunrealcore.raytrace;

import org.bukkit.Location;

public class RayTraceHitResult<T>
{
    private T        m_hit;
    private Location m_from;
    private double   m_distance;

    public RayTraceHitResult(T hit, Location from, double distance)
    {
        m_hit = hit;
        m_from = from;
        m_distance = distance;
    }

    public T getHit()
    {
        return m_hit;
    }

    public Location getFrom()
    {
        return m_from;
    }

    public double getDistance()
    {
        return m_distance;
    }
}
