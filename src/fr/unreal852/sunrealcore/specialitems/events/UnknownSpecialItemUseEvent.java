package fr.unreal852.sunrealcore.specialitems.events;

import fr.unreal852.sunrealcore.utils.ItemStackUtils;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

public class UnknownSpecialItemUseEvent extends Event
{
    private static final HandlerList handlers = new HandlerList();

    private String m_id;

    private ItemStack m_itemStack;

    public UnknownSpecialItemUseEvent(String id, ItemStack stack)
    {
        m_id = id;
        m_itemStack = stack;
    }

    public String getID()
    {
        return m_id;
    }

    public ItemStack getItemStack()
    {
        return m_itemStack;
    }

    public String getName()
    {
        return ItemStackUtils.getMeta(m_itemStack).getDisplayName();
    }

    @Override
    public HandlerList getHandlers()
    {
        return handlers;
    }

    public static HandlerList getHandlerList()
    {
        return handlers;
    }
}
