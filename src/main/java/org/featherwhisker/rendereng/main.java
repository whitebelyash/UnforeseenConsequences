package org.featherwhisker.rendereng;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class main implements ModInitializer {
    public static Logger log = LoggerFactory.getLogger("rendereng");
    public static boolean shouldConvertShaders = true; //experimental, does not work
    public static String shaderVersion = "150"; // "300 es"
    @Override
    public void onInitialize() {

    }

    public static String convertShader(String str,int i) {
        String append = "";
        if (true) { //i != 1) {
            append = "precision lowp float;";
        }
        return (str
                .replaceAll("#version 150","#version "+shaderVersion+"\n"+append)
        );
    }
}
