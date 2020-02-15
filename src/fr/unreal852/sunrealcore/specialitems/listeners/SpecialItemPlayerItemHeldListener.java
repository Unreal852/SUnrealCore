package fr.unreal852.sunrealcore.specialitems.listeners;

import fr.unreal852.sunrealcore.specialitems.SpecialItem;
import fr.unreal852.sunrealcore.specialitems.events.IHeldChangeable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;

public class SpecialItemPlayerItemHeldListener implements Listener
{
    @EventHandler(priority = EventPriority.MONITOR)
    private void onPlayerItemHeld(PlayerItemHeldEvent event)
    {
        Player player = event.getPlayer();
        SpecialItem oldItem = SpecialItem.get(player.getInventory().getItem(event.getPreviousSlot()));
        SpecialItem newItem = SpecialItem.get(player.getInventory().getItem(event.getNewSlot()));
        if (oldItem instanceof IHeldChangeable)
            ((IHeldChangeable) oldItem).onUnselected(event);
        else if (oldItem != null)
            event.setCancelled(oldItem.cancelNotImplementedEvents());

        if (newItem instanceof IHeldChangeable)
            ((IHeldChangeable) newItem).onSelected(event);
        else if (newItem != null)
            event.setCancelled(newItem.cancelNotImplementedEvents());
    }
}
