package fr.unreal852.sunrealcore.raytrace;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RayTraceResult<T>
{
    private List<RayTraceHitResult<T>> m_hits = new ArrayList<>();

    public RayTraceResult()
    {

    }

    public RayTraceResult(Collection<RayTraceHitResult<T>> rayTraceHitResultCollection)
    {
        m_hits.addAll(rayTraceHitResultCollection);
    }

    protected void add(RayTraceHitResult<T> hitResult)
    {
        m_hits.add(hitResult);
    }

    public int size()
    {
        return m_hits.size();
    }

    public boolean hasHit()
    {
        return m_hits.size() > 0;
    }

    public RayTraceHitResult<T> getFirstHit()
    {
        if (!hasHit())
            return null;
        return m_hits.get(0);
    }

    public RayTraceHitResult<T> getLastHit()
    {
        if (!hasHit())
            return null;
        return m_hits.get(m_hits.size() - 1);
    }

    public Collection<RayTraceHitResult<T>> getHits()
    {
        if (!hasHit())
            return new ArrayList<>();
        return m_hits;
    }
}
