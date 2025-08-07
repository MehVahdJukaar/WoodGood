package net.mehvahdjukaar.every_compat;

import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Properties;

public class UnsafeModuleDisabler {

    private final Properties properties;
    private final File propertiesFile;

    private boolean isSafe = true;

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public UnsafeModuleDisabler() {
        this.properties = new Properties();
        Path configPath = PlatHelper.getGamePath().resolve("config");
        this.propertiesFile = configPath.resolve("everycomp-hazardous.properties").toFile();

        try {
            // Ensure the config directory exists
            File configDir = configPath.toFile();
            if (!configDir.exists()) {
                configDir.mkdirs(); // Create directories if they do not exist
            }

            if (propertiesFile.exists()) {
                try (FileInputStream fis = new FileInputStream(propertiesFile)) {
                    properties.load(fis);
                }
            } else {
                propertiesFile.createNewFile();
            }
        } catch (IOException e) {
            throw new RuntimeException("Error initializing EC properties at path " + propertiesFile.getAbsolutePath(), e);
        }
    }

    public void save() {
        try (FileOutputStream output = new FileOutputStream(propertiesFile)) {
            properties.store(output, "Hard disable entire modules. Use at your own risk and don't ask for support if you use this. Write modid = false to disable modules");
        } catch (IOException e) {
            EveryCompat.LOGGER.error("Failed to save everycomp-hazardous.properties: ", e);
        }
    }

    public boolean isModuleOn(String modId) {
        try {
            if (properties.getProperty(modId) == null) {
                properties.setProperty(modId, String.valueOf(true));
                save();
            } else {
                // Assuming the values in the properties file are boolean
                var ret = Boolean.parseBoolean(properties.getProperty(modId, "true"));
                if (!ret && isSafe) {
                    isSafe = false;
                    EveryCompat.LOGGER.warn("!!! You are using conditional modules registration. Proceed at your own risk and dont complain if you CANNOT connect to servers !!!");
                }
                return ret;
            }
        } catch (Exception ignored) {}
        return true;
    }

}
