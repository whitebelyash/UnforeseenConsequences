package org.featherwhisker.rendereng.util;

import com.mojang.blaze3d.platform.GlConst;

import java.util.HashMap;
import java.util.Map;
import java.util.OptionalInt;

public class ReplacementConstants {
    private static final Map<Integer, Integer> CONSTANTS = new HashMap<>();

    public static OptionalInt getReplacementConstant(int constant) {
        if(!CONSTANTS.containsKey(constant)) return OptionalInt.empty();
        return OptionalInt.of(CONSTANTS.get(constant));
    }

    static {
        int GL_DEPTH_COMPONENT32F = 36012;

        CONSTANTS.put(GlConst.GL_RGBA8, GlConst.GL_RGBA);
        CONSTANTS.put(GlConst.GL_DEPTH_COMPONENT, GL_DEPTH_COMPONENT32F);
    }
}