package fr.unreal852.sunrealcore.debug;

import fr.unreal852.sunrealcore.configuration.data.object.ConfigValue;
import fr.unreal852.sunrealcore.configuration.data.object.IConfigObject;
import org.bukkit.Location;

import static fr.unreal852.sunrealcore.debug.DebugMain.MESSENGER;

import java.util.UUID;

public class MyConfigObject implements IConfigObject
{
    @ConfigValue(Index = 0, Path = "string")
    private String      m_StringTest;
    @ConfigValue(Index = 1, Path = "boolean")
    private Boolean     m_booleanTest;
    @ConfigValue(Index = 2, Path = "double")
    private Double      m_doubleTest;
    @ConfigValue(Index = 3, Path = "integer")
    private Integer     m_intTest;
    @ConfigValue(Index = 4, Path = "float")
    private Float       m_floatTest;
    @ConfigValue(Index = 5, Path = "long")
    private Long        m_longTest;
    @ConfigValue(Index = 6, Path = "location")
    private Location    m_locationTest;
    @ConfigValue(Index = 7, Path = "subObject")
    private MySubObject m_subObject;
    @ConfigValue(Index = 8, Path = "uuid")
    private UUID        m_uuidTest;

    @Override
    public void onConfigLoaded(String name)
    {
        MESSENGER.sendConsoleMessage("String: " + m_StringTest);
        MESSENGER.sendConsoleMessage("Boolean: " + m_booleanTest);
        MESSENGER.sendConsoleMessage("Double: " + m_doubleTest);
        MESSENGER.sendConsoleMessage("Integer: " + m_intTest);
        MESSENGER.sendConsoleMessage("Float: " + m_floatTest);
        MESSENGER.sendConsoleMessage("Long: " + m_longTest);
        MESSENGER.sendConsoleMessage("Location: " + m_locationTest);
        MESSENGER.sendConsoleMessage("UUID: " + m_uuidTest);
    }
}
