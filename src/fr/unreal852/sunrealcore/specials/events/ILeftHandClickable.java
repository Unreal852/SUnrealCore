package fr.unreal852.sunrealcore.specials.events;

import org.bukkit.event.player.PlayerInteractEvent;

public interface ILeftHandClickable
{
    /**
     * Called when a left hand click is triggered
     *
     * @param event Player Interact Event
     */
    void onHandLeftClick(PlayerInteractEvent event);
}
