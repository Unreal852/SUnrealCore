package fr.unreal852.sunrealcore.nms.packets;

import fr.unreal852.sunrealcore.nms.NMSWrapper;
import net.minecraft.server.v1_16_R1.*;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;

public class PacketWrapper<T extends Packet> extends NMSWrapper<T>
{
    private T m_packet;

    public PacketWrapper(T packet)
    {
        m_packet = packet;
    }

    @Override
    public T getNMS()
    {
        return m_packet;
    }

    public void setField(String fieldName, Object value)
    {
        try
        {
            Field field = m_packet.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(m_packet, value);
        }
        catch (NoSuchFieldException | IllegalAccessException e)
        {
            e.printStackTrace();
        }
    }

    public void send(Player... players)
    {
        if (players == null || players.length <= 0)
            return;
    }
}
