package fr.unreal852.sunrealcore.utils;

import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public final class ItemStackUtils
{
    /**
     * Get item meta from the specified ItemStack, if no meta is found, we create a new one and return it.
     *
     * @param stack ItemStack
     * @return ItemStack's ItemMeta
     */
    public static ItemMeta getMeta(ItemStack stack)
    {
        return stack.hasItemMeta() ? stack.getItemMeta() : Bukkit.getItemFactory().getItemMeta(stack.getType());
    }
}
