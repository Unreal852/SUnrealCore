package fr.unreal852.sunrealcore.raytrace.tracers;

import fr.unreal852.sunrealcore.raytrace.BaseRayTracer;
import fr.unreal852.sunrealcore.utils.EntityUtils;
import net.minecraft.server.v1_15_R1.*;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_15_R1.*;
import org.bukkit.craftbukkit.v1_15_R1.block.CraftBlock;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

import java.util.List;
import java.util.Objects;

public class EntityRayTracer extends BaseRayTracer<Entity>
{
    @Override
    protected List<Entity> getPossibleTargets(Location locationFrom, double maxRange)
    {
        return EntityUtils.getNearbyEntities(locationFrom, maxRange);
    }

    @Override
    protected boolean canGoThrough(LivingEntity entityFrom, Location locationFrom, Location locationProgress, Block block)
    {
        if (block.getType().isSolid())
        {
            World world = ((CraftWorld) Objects.requireNonNull(locationFrom.getWorld())).getHandle();
            BlockPosition blockPos = new BlockPosition(block.getX(), block.getY(), block.getZ());
            VoxelShape nmsVoxelShape = ((CraftBlock) block).getNMS().getCollisionShape(world, blockPos);
            if (nmsVoxelShape.isEmpty())
                return true;
            Vec3D vector = new Vec3D(locationProgress.getX(), locationProgress.getY(), locationProgress.getZ());
            for (AxisAlignedBB axis : nmsVoxelShape.d())
            {
                if (axis.a(blockPos).c(vector))
                    return false;
            }
        }
        return true;
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
