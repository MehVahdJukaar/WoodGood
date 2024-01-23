package net.mehvahdjukaar.every_compat;

import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Properties;

public class UnsafeModuleDisabler {
    private final Properties properties;
    private final Path configPath = PlatHelper.getGamePath().resolve("config/everycomp-hazardous.properties");

    private boolean isSafe = true;

    public UnsafeModuleDisabler() {
        this.properties = new Properties();
        loadProperties();
    }

    private void loadProperties() {
        try (FileInputStream input = new FileInputStream(configPath.toFile())) {
            properties.load(input);
        } catch (IOException e) {
            save();
        }
    }

    public void save() {
        try (FileOutputStream output = new FileOutputStream(configPath.toFile())) {
            properties.store(output, "Hard disable entire modules. Use at your own risk and don't ask for support if you use this");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isModuleOn(String modId) {
        try {
            if(properties.getProperty(modId) == null){
                properties.setProperty(modId, String.valueOf(true));
            }else {
                // Assuming the values in the properties file are boolean
                var ret = Boolean.parseBoolean(properties.getProperty(modId, "true"));
                if(!ret && isSafe){
                    isSafe = false;
                    EveryCompat.LOGGER.warn("!!! You are using conditional modules registration. Proceed at your own risk and dont complain if you cant connect to servers !!!");
                }
                return ret;
            }
        }catch (Exception ignored){
        }
        return true;
    }

}
