package fr.unreal852.sunrealcore.specialitems.listeners;

import fr.unreal852.sunrealcore.specialitems.SpecialItem;
import fr.unreal852.sunrealcore.specialitems.events.ILeftHandClickable;
import fr.unreal852.sunrealcore.specialitems.events.IRightHandClickable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class SpecialItemInteractListener implements Listener
{
    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerInteract(PlayerInteractEvent event)
    {
        if (!event.hasItem())
            return;
        SpecialItem specialItem = SpecialItem.get(event.getItem());
        if (specialItem == null)
            return;
        Action action = event.getAction();
        switch (action)
        {
            case LEFT_CLICK_BLOCK:
            case LEFT_CLICK_AIR:
            {
                if (specialItem instanceof ILeftHandClickable)
                    ((ILeftHandClickable) specialItem).onHandLeftClick(event);
                else
                    event.setCancelled(specialItem.cancelNotImplementedEvents());
            }
            return;
            case RIGHT_CLICK_BLOCK:
            case RIGHT_CLICK_AIR:
            {
                if (specialItem instanceof IRightHandClickable)
                    ((IRightHandClickable) specialItem).onHandRightClick(event);
                else
                    event.setCancelled(specialItem.cancelNotImplementedEvents());
            }
            return;
            case PHYSICAL:
        }
    }
}