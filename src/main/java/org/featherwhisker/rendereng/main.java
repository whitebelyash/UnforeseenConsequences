package org.featherwhisker.rendereng;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class main implements ModInitializer {
    public static Logger log = LoggerFactory.getLogger("rendereng");
    public static boolean shouldConvertShaders = true;
    public static boolean debugMode = false;
    public static String shaderVersion = "300 es";
    @Override
    public void onInitialize() {

    }
}
