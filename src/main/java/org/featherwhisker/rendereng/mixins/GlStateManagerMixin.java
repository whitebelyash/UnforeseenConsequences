package org.featherwhisker.rendereng.mixins;

import com.mojang.blaze3d.platform.GlStateManager;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.nio.ByteBuffer;

@Mixin(GlStateManager.class)
public class GlStateManagerMixin {
    /**
     * @author Featherwhisker
     * @reason Not supported in OGL ES
     */
    @Overwrite
    public static void _logicOp(int op) {}
    /**
     * @author Featherwhisker
     * @reason Not supported in OGL ES
     */
    @Overwrite
    public static void _getTexImage(int target, int level, int format, int type, long pixels) {}
    /**
     * @author Featherwhisker
     * @reason Not supported in OGL ES
     */
    @Overwrite
    public static void _polygonMode(int face, int mode) {}
}
