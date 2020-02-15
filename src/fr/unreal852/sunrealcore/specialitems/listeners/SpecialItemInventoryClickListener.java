package fr.unreal852.sunrealcore.specialitems.listeners;

import fr.unreal852.sunrealcore.specialitems.SpecialItem;
import fr.unreal852.sunrealcore.specialitems.events.inventory.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class SpecialItemInventoryClickListener implements Listener
{
    @EventHandler(priority = EventPriority.MONITOR)
    private void onInventoryClick(InventoryClickEvent event)
    {
        ItemStack clicked = event.getCurrentItem();
        ClickType type = event.getClick();
        SpecialItem specialItem = SpecialItem.get(clicked);
        if (specialItem == null)
            return;
        switch (type)
        {
            case LEFT:
            {
                if (specialItem instanceof IInventoryLeftClickable)
                    ((IInventoryLeftClickable) specialItem).onInventoryLeftClick(event);
                else
                    event.setCancelled(specialItem.cancelNotImplementedEvents());
            }
            case SHIFT_LEFT:
            {
                if (specialItem instanceof IInventoryShiftLeftClickable)
                    ((IInventoryShiftLeftClickable) specialItem).onInventoryShiftLeftClick(event);
                else
                    event.setCancelled(specialItem.cancelNotImplementedEvents());
            }
            case RIGHT:
            {
                if (specialItem instanceof IInventoryRightClickable)
                    ((IInventoryRightClickable) specialItem).onInventoryRightClick(event);
                else
                    event.setCancelled(specialItem.cancelNotImplementedEvents());
            }
            case SHIFT_RIGHT:
            {
                if (specialItem instanceof IInventoryShiftRightClickable)
                    ((IInventoryShiftRightClickable) specialItem).onInventoryShiftRightClick(event);
                else
                    event.setCancelled(specialItem.cancelNotImplementedEvents());
            }
            case WINDOW_BORDER_LEFT:
            {
                if (specialItem instanceof IInventoryWindowBorderLeftClickable)
                    ((IInventoryWindowBorderLeftClickable) specialItem).onInventoryBorderLeftClick(event);
                else
                    event.setCancelled(specialItem.cancelNotImplementedEvents());
            }
            case WINDOW_BORDER_RIGHT:
            {
                if (specialItem instanceof IInventoryWindowBorderRightClickable)
                    ((IInventoryWindowBorderRightClickable) specialItem).onInventoryBorderRightClick(event);
                else
                    event.setCancelled(specialItem.cancelNotImplementedEvents());
            }
            case MIDDLE:
            {
                if (specialItem instanceof IInventoryMiddleClickable)
                    ((IInventoryMiddleClickable) specialItem).onInventoryMiddleClick(event);
                else
                    event.setCancelled(specialItem.cancelNotImplementedEvents());
            }
            case NUMBER_KEY:
            {

            }
            case DOUBLE_CLICK:
            {

            }
            case DROP:
            {

            }
            case CONTROL_DROP:
            {

            }
            case CREATIVE:
            {

            }
            case UNKNOWN:
            {

            }
        }
    }
}
