package fr.unreal852.sunrealcore.raytrace.tracers;

import fr.unreal852.sunrealcore.raytrace.BaseRayTracer;
import fr.unreal852.sunrealcore.raytrace.RayTraceHitResult;
import fr.unreal852.sunrealcore.raytrace.RayTraceResult;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class BlockRayTracer extends BaseRayTracer<Block>
{
    @Override
    public RayTraceResult<Block> trace(LivingEntity entityFrom, Location locationFrom, double maxRange, int maxHits)
    {
        RayTraceResult<Block> traceResult = new RayTraceResult<>();
        Block hitBlock;
        Location locationProgress = locationFrom.clone();
        Vector vectorProgress = locationProgress.getDirection().normalize().clone().multiply(getVectorProgress());
        maxRange = (100 * maxRange) / (getVectorProgress() * 100);
        int currentLoop = 0;
        while (currentLoop < maxRange)
        {
            ++currentLoop;
            locationProgress.add(vectorProgress);
            onProgress(entityFrom, locationFrom, locationProgress);
            hitBlock = locationProgress.getBlock();
            if (!isWallHack() && hitBlock.getType() != Material.AIR && !canGoThrough(entityFrom, locationFrom, locationProgress, hitBlock))
                continue;
            if (onHitBlock(entityFrom, locationFrom, hitBlock))
            {
                traceResult.add(new RayTraceHitResult<>(hitBlock, locationFrom, (currentLoop * (getVectorProgress() * 100)) / 100));
                if (traceResult.size() >= maxHits)
                    break;
            }
            if (traceResult.size() >= maxHits)
                break;
        }
        traceResult.lock();
        return traceResult;
    }

    @Override
    protected List<Block> getPossibleTargets(Location locationFrom, double maxRange)
    {
        return new ArrayList<>();
    }

    @Override
    protected boolean canGoThrough(LivingEntity entityFrom, Location locationFrom, Location locationProgress, Block block)
    {
        return true;
    }

    @Override
    protected boolean onHitType(LivingEntity entityFrom, Location locationFrom, Location locationProgress, Block hitType)
    {
        return true;
    }

    @Override
    protected boolean onHitBlock(LivingEntity entityFrom, Location locationFrom, Block block)
    {
        return true;
    }
}
