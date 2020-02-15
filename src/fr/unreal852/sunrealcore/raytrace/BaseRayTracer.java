package fr.unreal852.sunrealcore.raytrace;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.Vector;

import java.util.List;


public abstract class BaseRayTracer<T>
{
    private double  m_vectorProgress = 0.7;
    private boolean m_showTrace      = false;
    private boolean m_wallHack       = false;

    public BaseRayTracer()
    {

    }

    public boolean isWallHack()
    {
        return m_wallHack;
    }

    public boolean isTraceVisible()
    {
        return m_showTrace;
    }

    public double getVectorProgress()
    {
        return m_vectorProgress;
    }

    public void setWallHack(boolean wallHack)
    {
        m_wallHack = wallHack;
    }

    public void setVectorProgress(double vectorProgress)
    {
        m_vectorProgress = vectorProgress;
    }

    public void setTraceVisible(boolean traceVisible)
    {
        m_showTrace = traceVisible;
    }

    public RayTraceResult<T> trace(Location locationFrom, double maxRange, int maxHits)
    {
        return trace(null, locationFrom, maxRange, maxHits);
    }

    public RayTraceResult<T> trace(LivingEntity entityFrom, Location locationFrom, double maxRange, int maxHits)
    {
        RayTraceResult<T> traceResult = new RayTraceResult<>();
        List<T> possibleTargets = getPossibleTargets(locationFrom, maxRange);
        if (possibleTargets.size() <= 0)
            return traceResult;
        Block hitBlock;
        Location locationProgress = locationFrom.clone();
        Vector vectorProgress = locationProgress.getDirection().normalize().clone().multiply(m_vectorProgress);
        maxRange = (100 * maxRange) / (m_vectorProgress * 100);
        int currentLoop = 0;
        while (currentLoop < maxRange)
        {
            ++currentLoop;
            locationProgress.add(vectorProgress);
            onProgress(entityFrom, locationFrom, locationProgress);
            hitBlock = locationProgress.getBlock();
            if (!m_wallHack && hitBlock.getType() != Material.AIR && !canGoThrough(locationFrom, hitBlock))
                continue;
            onHitBlock(entityFrom, locationFrom, hitBlock);
            for (T hitType : possibleTargets)
            {
                if (!onHitType(entityFrom, locationFrom, locationProgress, hitType))
                    continue;
                traceResult.add(new RayTraceHitResult<T>(hitType, locationFrom, (currentLoop * (m_vectorProgress * 100)) / 100));
                if (traceResult.size() >= maxHits)
                    break;
            }
            if (traceResult.size() >= maxHits)
                break;
        }
        return traceResult;
    }

    protected void onProgress(LivingEntity entityFrom, Location locationFrom, Location locationProgress)
    {
        if (!m_showTrace || locationFrom.getWorld() == null)
            return;
        locationFrom.getWorld().spawnParticle(Particle.REDSTONE, locationProgress, 1, new Particle.DustOptions(Color.YELLOW, 1));
    }

    protected abstract List<T> getPossibleTargets(Location locationFrom, double maxRange);

    protected abstract boolean canGoThrough(Location locationFrom, Block block);

    protected abstract boolean onHitType(LivingEntity entityFrom, Location locationFrom, Location locationProgress, T hitType);

    protected abstract boolean onHitBlock(LivingEntity entityFrom, Location locationFrom, Block block);
}
