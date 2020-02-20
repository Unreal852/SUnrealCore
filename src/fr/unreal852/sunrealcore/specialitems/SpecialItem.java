package fr.unreal852.sunrealcore.specialitems;

import fr.unreal852.sunrealcore.flags.Flag;
import fr.unreal852.sunrealcore.specialitems.events.UnknownSpecialItemUseEvent;
import fr.unreal852.sunrealcore.utils.ItemStackUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.*;

public abstract class SpecialItem
{
    private static       Map<String, SpecialItem> ITEMS   = new HashMap<>();
    private static final Random                   RANDOM  = new Random();
    private static final String                   FLAG_ID = "SpecialItemID";

    public static SpecialItem get(ItemStack stack)
    {
        if (stack == null || stack.getType() == Material.AIR)
            return null;
        String id = Flag.getFlag(stack, FLAG_ID, PersistentDataType.STRING);
        if (id == null || id.isEmpty())
            return null;
        if (!ITEMS.containsKey(id))
            Bukkit.getPluginManager().callEvent(new UnknownSpecialItemUseEvent(id, stack));
        if (ITEMS.containsKey(id))
            return ITEMS.get(id);
        return null;
    }

    private static String generateID()
    {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 5; i++)
            builder.append(RANDOM.nextInt(10));
        return builder.toString();
    }

    private String m_id;

    private ItemStack m_stack;

    private boolean m_cancelNotImplementedEvents = false;

    public SpecialItem(String name, ItemStack stack, String... lore)
    {
        m_id = generateID();
        m_stack = stack;
        Flag.setFlag(stack, FLAG_ID, m_id, PersistentDataType.STRING);
        prepareStack(name, lore);
        ITEMS.put(m_id, this);
    }

    public SpecialItem(ItemStack stack)
    {
        m_id = Flag.getFlag(stack, FLAG_ID, PersistentDataType.STRING);
        m_stack = stack;
        ITEMS.put(m_id, this);
    }

    private void prepareStack(String name, String... lore)
    {
        ItemMeta meta = ItemStackUtils.getMeta(m_stack);
        meta.setDisplayName(name);
        meta.setLore(Arrays.asList(lore));
        m_stack.setItemMeta(meta);
    }

    public String getID()
    {
        return m_id;
    }

    public String getName()
    {
        return ItemStackUtils.getMeta(m_stack).getDisplayName();
    }

    public Collection<String> getLore()
    {
        return ItemStackUtils.getMeta(m_stack).getLore();
    }

    public ItemStack getItemStack()
    {
        return m_stack;
    }

    public ItemStack getClonedItemStack()
    {
        return m_stack.clone();
    }

    public boolean isRegistered()
    {
        return ITEMS.containsKey(m_id);
    }

    public boolean cancelNotImplementedEvents()
    {
        return m_cancelNotImplementedEvents;
    }

    public void unregister()
    {
        Flag.removeFlag(m_stack, FLAG_ID);
        ITEMS.remove(m_id);
        onUnregister();
    }

    protected void setCancelNotImplementedEvents(boolean cancel)
    {
        m_cancelNotImplementedEvents = cancel;
    }

    protected abstract void onUnregister();
}