package fr.unreal852.sunrealcore.specials.listeners;

import fr.unreal852.sunrealcore.specials.item.SpecialItem;
import fr.unreal852.sunrealcore.specials.events.IDroppable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;

public class SpecialPlayerItemDropListener implements Listener
{
    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerDropItem(PlayerDropItemEvent event)
    {
        ItemStack item = event.getItemDrop().getItemStack();
        SpecialItem specialItem = SpecialItem.get(item);
        if(specialItem instanceof IDroppable)
            ((IDroppable)specialItem).onDrop(event);
        else if(specialItem != null)
            event.setCancelled(specialItem.cancelNotImplementedEvents());
    }
}
