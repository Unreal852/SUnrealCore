package fr.unreal852.sunrealcore.configuration.data.datavalues;

import fr.unreal852.sunrealcore.configuration.ConfigFile;
import fr.unreal852.sunrealcore.configuration.data.IConfigDataValue;
import org.bukkit.potion.PotionEffectType;

public class PotionEffectTypeDataValue implements IConfigDataValue<PotionEffectType>
{
    @Override
    public PotionEffectType readValue(Class<PotionEffectType> aClass, ConfigFile configFile, String path)
    {
        return PotionEffectType.getByName(configFile.getString(path));
    }

    @Override
    public void writeValue(Class<PotionEffectType> aClass, ConfigFile configFile, String path, PotionEffectType potionEffectType)
    {
        configFile.getYamlConfiguration().set(path, potionEffectType.getName());
    }
}
