package fr.unreal852.sunrealcore.nms;

import net.minecraft.server.v1_15_R1.EntityPlayer;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerWrapper extends NMSWrapper<EntityPlayer>
{
    private static final Map<UUID, PlayerWrapper> s_wrappers = new HashMap<>();

    public static PlayerWrapper get(Player player)
    {
        if (s_wrappers.containsKey(player.getUniqueId()))
            return s_wrappers.get(player.getUniqueId());
        return new PlayerWrapper(player);
    }

    private PlayerWrapper(Player player)
    {

    }

    @Override
    public EntityPlayer getNMS()
    {
        return null;
    }
}
