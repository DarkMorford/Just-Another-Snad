package net.darkmorford.jas.configuration;

import net.darkmorford.jas.JustAnotherSnad;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.logging.log4j.Level;

import java.io.File;

public class ConfigurationHandler {

    private static Configuration configuration;
    private static File configFile;

    public static void init() {
        setConfiguration(new Configuration(configFile));
    }

    public static void updateConfiguration() {
        try {
            configuration.load();

            ConfigurationData.SNAD_SPEED_INCREASE_VALUE =
                configuration.get(ConfigurationData.GROUP_GENERAL_KEY,
                    ConfigurationData.SNAD_SPEED_INCREASE_KEY,
                    ConfigurationData.SNAD_SPEED_INCREASE_DEFAULT).getInt();

            ConfigurationData.SOUL_SNAD_SPEED_INCREASE_VALUE =
                configuration.get(ConfigurationData.GROUP_GENERAL_KEY,
                    ConfigurationData.SOUL_SNAD_SPEED_INCREASE_KEY,
                    ConfigurationData.SOUL_SNAD_SPEED_INCREASE_DEFAULT).getInt();
        } catch (Exception exception) {
            JustAnotherSnad.logger.log(Level.ERROR, "Unable to read Config File", exception);
        } finally {
            if (configuration.hasChanged()) configuration.save();
        }
    }

    public static Configuration getConfiguration() {
        return configuration;
    }

    public static void setConfiguration(Configuration newConfiguration) {
        configuration = newConfiguration;
    }

    public static File getConfigFile() {
        return configFile;
    }

    public static void setConfigFile(File suggestedConfigurationFile) {
        configFile = suggestedConfigurationFile;
    }

    @SubscribeEvent
    public static void onConfigurationChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.getModID().equalsIgnoreCase(JustAnotherSnad.MODID)) updateConfiguration();
    }

    public static void checkConfigurationChange() {
        if (configuration.hasChanged()) configuration.save();
    }
}
