package fr.unreal852.sunrealcore.debug;

import fr.unreal852.sunrealcore.configuration.data.object.ConfigObject;
import fr.unreal852.sunrealcore.configuration.data.object.IConfigObject;
import org.bukkit.Location;

import static fr.unreal852.sunrealcore.debug.DebugMain.MESSENGER;

import java.util.UUID;

public class MyConfigObject implements IConfigObject
{
    @ConfigObject(Path = "string")
    private String   m_StringTest;
    @ConfigObject(Path = "boolean")
    private Boolean  m_booleanTest;
    @ConfigObject(Path = "double")
    private Double   m_doubleTest;
    @ConfigObject(Path = "integer")
    private Integer  m_intTest;
    @ConfigObject(Path = "float")
    private Float    m_floatTest;
    @ConfigObject(Path = "long")
    private Long     m_longTest;
    @ConfigObject(Path = "location")
    private Location m_locationTest;
    @ConfigObject(Path = "uuid")
    private UUID     m_uuidTest;

    public void printAll()
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
