package com.lypaka.clicky;

import com.lypaka.lypakautils.ConfigurationLoaders.BasicConfigManager;
import com.lypaka.lypakautils.ConfigurationLoaders.ConfigUtils;
import net.minecraftforge.fml.common.Mod;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Mod("clicky")
public class Clicky {

    public static final String MOD_ID = "clicky";
    public static final String MOD_NAME = "Clicky";
    public static Logger logger = LogManager.getLogger("Clicky");
    public static BasicConfigManager configManager;

    public Clicky() throws ObjectMappingException, IOException {

        Path dir = ConfigUtils.checkDir(Paths.get("./config/clicky"));
        String[] files = new String[]{"settings.conf", "player-storage.conf"};
        configManager = new BasicConfigManager(files, dir, Clicky.class, MOD_NAME, MOD_ID, logger);
        configManager.init();
        ConfigGetters.load();

    }

}
