package fr.unreal852.sunrealcore.raytrace.tracers;

import fr.unreal852.sunrealcore.raytrace.BaseRayTracer;
import fr.unreal852.sunrealcore.utils.EntityUtils;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

import java.util.List;

public class EntityRayTracer extends BaseRayTracer<Entity>
{
    @Override
    protected List<Entity> getPossibleTargets(Location locationFrom, double maxRange)
    {
        return EntityUtils.getNearbyEntities(locationFrom, maxRange);
    }

    @Override
    protected boolean canGoThrough(Location locationFrom, Block block)
    {
        return !block.getType().isSolid();
    }

    @Override
    protected boolean onHitType(LivingEntity entityFrom, Location locationFrom, Location locationProgress, Entity hitType)
    {
        return (entityFrom != hitType && hitType.getBoundingBox().contains(locationProgress.getX(), locationProgress.getY(), locationProgress.getZ()));
    }

    @Override
    protected boolean onHitBlock(LivingEntity entityFrom, Location locationFrom, Block block)
    {
        return false;
    }
}
