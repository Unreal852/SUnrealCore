package fr.unreal852.sunrealcore.specialitems.events;

import org.bukkit.event.player.PlayerItemHeldEvent;

public interface IHeldChangeable
{
    void onSelected(PlayerItemHeldEvent event);

    void onUnselected(PlayerItemHeldEvent event);
}
