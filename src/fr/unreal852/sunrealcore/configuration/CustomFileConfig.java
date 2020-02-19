package fr.unreal852.sunrealcore.configuration;

import com.google.common.collect.Sets;
import fr.unreal852.sunrealcore.configuration.data.ConfigDataManager;
import fr.unreal852.sunrealcore.configuration.data.IConfigDataValue;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.libs.org.apache.commons.io.IOUtils;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;

public class CustomFileConfig
{
    private JavaPlugin m_plugin;

    private File m_file;

    private String m_resourcePath;

    private FileConfiguration m_fileConfig;

    public CustomFileConfig(JavaPlugin plugin, String filePath, String resourcePath)
    {
        m_plugin = plugin;
        m_file = new File(plugin.getDataFolder().getAbsolutePath().concat(filePath));
        m_resourcePath = resourcePath;
        load();
    }

    /**
     * Returns the associated plugin of this file.
     *
     * @return Plugin
     */
    public JavaPlugin getPlugin()
    {
        return m_plugin;
    }

    /**
     * Returns the file.
     *
     * @return File
     */
    public File getFile()
    {
        return m_file;
    }

    /**
     * Returns the resource path ( Inside Jar Path ).
     *
     * @return Resource Path ( can be null )
     */
    public String getResourcePath()
    {
        return m_resourcePath;
    }

    /**
     * Returns the YAML configuration.
     *
     * @return YAML Configuration
     */
    public FileConfiguration getYamlConfiguration()
    {
        return m_fileConfig;
    }

    /**
     * Checks if this config file is loaded
     *
     * @return true if loaded, false otherwise
     */
    public boolean isLoaded()
    {
        return getYamlConfiguration() != null;
    }

    /**
     * Check if this config file contains the specified paths
     *
     * @param paths Requiered Paths
     * @return true if all paths exists, false otherwise
     */
    public boolean checkPaths(Collection<String> paths)
    {
        if (paths == null || paths.size() == 0)
            return true;
        for (String path : paths)
        {
            if (!exists(path) || getYamlConfiguration().get(path) == null)
                return false;
        }
        return true;
    }

    /**
     * Checks if the specified path exists in this config file
     *
     * @param path Path to check
     * @return true if the path exists, false otherwise
     */
    public boolean exists(String path)
    {
        return getYamlConfiguration().contains(path);
    }

    /**
     * Get section from file
     *
     * @param path Path to section
     * @return Section Keys from path, or empty string set if the path doesn't exists or is not valid
     */
    public Set<String> getSection(String path)
    {
        if (!getYamlConfiguration().isConfigurationSection(path))
            return Sets.newHashSet();
        ConfigurationSection section = getYamlConfiguration().getConfigurationSection(path);
        return section == null ? Sets.newHashSet() : section.getKeys(false);
    }

    /**
     * Get string from file
     *
     * @param path Path to string
     * @return String from path, or empty string if the path doesn't exists or is not valid
     */
    public String getString(String path)
    {
        if (getYamlConfiguration().isString(path))
            return getYamlConfiguration().getString(path);
        return "";
    }

    /**
     * Get string list from file
     *
     * @param path Path to String List
     * @return String List from path, or empty string List if the path doesn't exists or is not valid
     */
    public List<String> getStringList(String path)
    {
        if (getYamlConfiguration().isList(path))
            return getYamlConfiguration().getStringList(path);
        return new ArrayList<>();
    }

    /**
     * Get boolean from file
     *
     * @param path Path to boolean
     * @return boolean value from path, or false if the path doesn't exists or is not valid
     */
    public boolean getBoolean(String path)
    {
        if (getYamlConfiguration().isBoolean(path))
            return getYamlConfiguration().getBoolean(path);
        return false;
    }

    /**
     * Get integer from file
     *
     * @param path Path to integer
     * @return integer value from path, or 0 if the path doesn't exists or is not valid
     */
    public int getInteger(String path)
    {
        if (getYamlConfiguration().isInt(path))
            return getYamlConfiguration().getInt(path);
        return 0;
    }

    /**
     * Get double from file
     *
     * @param path Path to double
     * @return double value from path, or 0.0 if the path doesn't exists or is not valid
     */
    public double getDouble(String path)
    {
        if (getYamlConfiguration().isDouble(path))
            return getYamlConfiguration().getDouble(path);
        return 0.0;
    }

    /**
     * Get float from file
     *
     * @param path Path to float
     * @return float value from path, or 0.0f if the path doesn't exists or is not valid
     */
    public float getFloat(String path)
    {
        double val = getDouble(path);
        return (float) val;
    }

    public <T> T get(Class<T> tClass, String path)
    {
        IConfigDataValue dataValue = ConfigDataManager.get(tClass);
        if (dataValue == null)
            return null;
        Object value = dataValue.readValue(this, path);
        if (value == null)
            return null;
        return (T) value;
    }

    public <T> void set(Class<T> tClass, String path, T value)
    {
        IConfigDataValue dataValue = ConfigDataManager.get(tClass);
        if (dataValue == null)
            return;
        dataValue.writeValue(this, path, value);
        save();
    }

    public void save()
    {
        try
        {
            getYamlConfiguration().save(getFile());
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    /**
     * Load file
     */
    protected void load()
    {
        getFile().getAbsoluteFile().getParentFile().mkdirs();
        if (!getFile().exists())
        {
            try
            {
                if (getResourcePath() == null && !getFile().createNewFile())
                    return;
                else
                {
                    InputStream stream = getPlugin().getResource(getResourcePath());
                    if (stream == null)
                        return;
                    OutputStream out = new FileOutputStream(getFile());
                    IOUtils.copy(stream, out);
                    stream.close();
                    out.close();
                }
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }
        m_fileConfig = YamlConfiguration.loadConfiguration(m_file);
    }
}
