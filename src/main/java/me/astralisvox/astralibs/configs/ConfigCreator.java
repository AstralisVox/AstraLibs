package me.astralisvox.astralibs.configs;

import me.astralisvox.astralibs.Utilities;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigCreator {
    private File config;
    private FileConfiguration customConfig;
    private final String fileName;

    public ConfigCreator(final String fileName) {
        this.fileName = fileName;
    }

    public void createConfig() {
        config = new File(Utilities.getInstance().getDataFolder(), fileName);
        if(!config.exists()) {
            config.getParentFile().mkdirs();
            Utilities.getInstance().saveResource(fileName, false);
        }
        customConfig = new YamlConfiguration();
        try {
            customConfig.load(config);
        } catch (final InvalidConfigurationException | IOException ex) {
            ex.printStackTrace();
        }
    }

    public void reloadConfig() {
        try {
            customConfig.load(config);
        } catch (IOException | InvalidConfigurationException ex) {
            ex.printStackTrace();
        }
    }

    public void saveConfig() {
        try {
            customConfig.save(config);
        } catch (final IOException ex) {
            ex.printStackTrace();
        }
    }

    public void deleteConfig() {
        try {
            config.delete();
        } catch (SecurityException ignored) {}
    }

    public FileConfiguration getConfig() {
        return customConfig;
    }

    public File getFile() {
        return config;
    }

    public String getFileName() {
        return fileName;
    }
}
