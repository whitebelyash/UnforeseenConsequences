package org.featherwhisker.rendereng.mixins;

import com.mojang.blaze3d.platform.GlStateManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static org.lwjgl.opengl.GL14.*;

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
    /**
     * @author Featherwhisker
     * @reason Not supported in OGL ES
     */
    @Overwrite
    public static void _glDrawPixels(int width, int height, int format, int type, long pixels) {}

    @Inject(
            method = "_texParameter",
            at=@At("HEAD"),
            cancellable = true
    )
    private static void _glDrawPixels(int a, int b, float c, CallbackInfo call) {
        if (a == GL_TEXTURE_2D && b == GL_TEXTURE_LOD_BIAS) {
            call.cancel();
        }
    }
}
