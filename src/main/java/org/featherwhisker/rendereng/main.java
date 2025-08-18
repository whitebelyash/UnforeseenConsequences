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

    public static String convertShader(String str,int i) {
        String append = "precision mediump float;\nprecision mediump int;\n\n";
        return (str
                .replaceAll("#version 150","#version "+shaderVersion+"\n"+append)
               .replaceAll("texCoord2 = UV2;","texCoord2 = vec2(UV2);")
			    .replaceAll("uv / 256.0","vec2(uv) / 256.0")
                // Hacky 1.21.3/4 lightmap workaround - probably there are some proper fixes to this?
                .replaceAll("float block_brightness = get_brightness\\(floor\\(texCoord.x \\* 16\\) / 15\\) \\* BlockFactor",
                        "float block_brightness = get_brightness(floor(texCoord.x * 16.0) / 15.0) * BlockFactor")
                .replaceAll("float sky_brightness = get_brightness\\(floor\\(texCoord.y \\* 16\\) / 15\\) \\* SkyFactor",
                        "float sky_brightness = get_brightness(floor(texCoord.y * 16.0) / 15.0) * SkyFactor")
             //   .replaceAll("//.*\\n//.*\\n/.*\\n","") //because CC needs to have comments before the version statement
        );
    }
}
