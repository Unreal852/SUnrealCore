package fr.unreal852.sunrealcore.specials.events;

import org.bukkit.event.player.PlayerInteractEvent;

public interface IRightHandClickable
{
    /**
     * Called when a right hand click is triggered
     *
     * @param event Player Interact Event
     */
    void onHandRightClick(PlayerInteractEvent event);
}
