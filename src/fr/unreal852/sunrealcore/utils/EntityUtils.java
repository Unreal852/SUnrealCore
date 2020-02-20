package fr.unreal852.sunrealcore.utils;

import org.bukkit.Location;
import org.bukkit.entity.Entity;

import java.util.ArrayList;
import java.util.List;

public final class EntityUtils
{
    /**
     * Get nearby entities at the specified location within the specified range.
     *
     * @param loc   Location
     * @param range Range
     * @return Nearby Entities
     */
    public static List<Entity> getNearbyEntities(Location loc, double range)
    {
        if (loc.getWorld() == null)
            return new ArrayList<>();
        List<Entity> entities = new ArrayList<>();
        for (Entity entity : loc.getWorld().getEntities())
        {
            if (entity.getLocation().distanceSquared(loc) <= (range * range))
                entities.add(entity);
        }
        return entities;
    }
}