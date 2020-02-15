package fr.unreal852.sunrealcore.raytrace.tracers;

import fr.unreal852.sunrealcore.raytrace.BaseRayTracer;
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
        return null;
    }

    @Override
    protected List<Block> getPossibleTargets(Location locationFrom, double maxRange)
    {
        return new ArrayList<>();
    }

    @Override
    protected boolean canGoThrough(Location locationFrom, Block block)
    {
        return true;
    }

    @Override
    protected boolean onHitType(LivingEntity entityFrom, Location locationFrom, Location locationProgress, Block hitType)
    {
        return false;
    }

    @Override
    protected boolean onHitBlock(LivingEntity entityFrom, Location locationFrom, Block block)
    {
        return false;
    }
}
