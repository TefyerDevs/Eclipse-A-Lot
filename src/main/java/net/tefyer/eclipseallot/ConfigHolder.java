package net.tefyer.eclipseallot;

import dev.toma.configuration.Configuration;
import dev.toma.configuration.config.Config;
import dev.toma.configuration.config.Configurable;
import dev.toma.configuration.config.format.ConfigFormats;

@Config(id =  Eclipseallot.MODID, group = "/"+Eclipseallot.MODID)
public class ConfigHolder {

    public static ConfigHolder INSTANCE;
    private static final Object LOCK = new Object();

    @Configurable
    public DeveloperConfigs dev = new DeveloperConfigs();

    public static void init() {
        synchronized (LOCK) {
            if (INSTANCE == null) {
                INSTANCE = Configuration.registerConfig(ConfigHolder.class, ConfigFormats.YAML).getConfigInstance();
            }
        }
    }

    public static class DeveloperConfigs {

        @Configurable
        @Configurable.Comment({ "Debug general events? (will print recipe conficts etc. to server's debug.log)",
                "Default: false" })
        public boolean debug = false;
        @Configurable
        @Configurable.Comment({ "Debug ore vein placement? (will print placed veins to server's debug.log)",
                "Default: false (no placement printout in debug.log)" })
        public boolean debugWorldgen = false;
        @Configurable
        @Configurable.Comment({ "Generate ores in superflat worlds?", "Default: false" })
        public boolean doSuperflatOres = false;
        @Configurable
        @Configurable.Comment({ "Dump all registered GT recipes?", "Default: false" })
        public boolean dumpRecipes = false;
        @Configurable
        @Configurable.Comment({ "Dump all registered GT models/blockstates/etc?", "Default: false" })
        public boolean dumpAssets = false;
    }

}
