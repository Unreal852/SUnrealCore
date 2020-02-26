package fr.unreal852.sunrealcore.configuration.data.datavalues;

import fr.unreal852.sunrealcore.configuration.ConfigFile;
import fr.unreal852.sunrealcore.configuration.data.IConfigDataValue;
import org.bukkit.Material;

public class MaterialDataValue implements IConfigDataValue<Material>
{
    @Override
    public Material readValue(Class<Material> tClass, ConfigFile config, String path)
    {
        return Material.getMaterial(config.getString(path));
    }

    @Override
    public void writeValue(Class<Material> tClass, ConfigFile config, String path, Material value)
    {
        config.getYamlConfiguration().set(path, value.toString());
    }
}
