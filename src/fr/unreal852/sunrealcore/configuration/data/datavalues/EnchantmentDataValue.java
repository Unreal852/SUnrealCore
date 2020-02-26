package fr.unreal852.sunrealcore.configuration.data.datavalues;

import fr.unreal852.sunrealcore.configuration.ConfigFile;
import fr.unreal852.sunrealcore.configuration.data.IConfigDataValue;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;

public class EnchantmentDataValue implements IConfigDataValue<Enchantment>
{
    @Override
    public Enchantment readValue(Class<Enchantment> enchantmentClass, ConfigFile config, String path)
    {
        return Enchantment.getByKey(NamespacedKey.minecraft(config.getString(path).toLowerCase()));
    }

    @Override
    public void writeValue(Class<Enchantment> enchantmentClass, ConfigFile config, String path, Enchantment value)
    {
        config.getYamlConfiguration().set(path, value.getKey().getKey());
    }
}
