package fr.unreal852.sunrealcore.debug;

import fr.unreal852.sunrealcore.configuration.data.object.ConfigValue;
import fr.unreal852.sunrealcore.configuration.data.object.IConfigObject;
import org.bukkit.Location;

import java.util.UUID;

import static fr.unreal852.sunrealcore.debug.DebugMain.MESSENGER;

public class MySubObject implements IConfigObject
{
    public MySubObject()
    {
        m_StringTest = "Dorian";
        m_booleanTest = false;
        m_doubleTest = 16.6;
    }

    @ConfigValue(Index = 0, Path = "string")
    private String  m_StringTest;
    @ConfigValue(Index = 1, Path = "boolean")
    private Boolean m_booleanTest;
    @ConfigValue(Index = 2, Path = "double")
    private Double  m_doubleTest;

    @Override
    public void onConfigLoaded()
    {

    }
}
