package org.featherwhisker.rendereng.mixins;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.gl.Framebuffer;
import org.featherwhisker.rendereng.main;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.nio.IntBuffer;

@Mixin(Framebuffer.class)
public class FramebufferMixin {
    @Redirect(method = "initFbo", at = @At(value = "INVOKE", target = "com/mojang/blaze3d/platform/GlStateManager._texImage2D(IIIIIIIILjava/nio/IntBuffer;)V"))
    private void wrapTexture2D(int target, int level, int internalFormat, int width, int height, int border, int format, int type, @Nullable IntBuffer pixels){
        main.log.info("Format: " + format + ", internalformat: " + internalFormat);
        // TODO: simplify
        if(internalFormat == 32856){
            main.log.info("Detected incompatible format 32856, replacing with 6408");
            internalFormat = 6408;
        }
        if(internalFormat == 6402){
            main.log.info("Detected incompatible format 6402, replacing with 36012");
            internalFormat = 36012;
        }
        GlStateManager._texImage2D(target, level, internalFormat, width, height, border, format, type, pixels);
    }
}
