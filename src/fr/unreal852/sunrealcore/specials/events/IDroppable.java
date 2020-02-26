package fr.unreal852.sunrealcore.specials.events;

import org.bukkit.event.player.PlayerDropItemEvent;

public interface IDroppable
{
    /**
     * Called when a drop is triggered
     *
     * @param event Player Drop Item Event
     */
    void onDrop(PlayerDropItemEvent event);
}
