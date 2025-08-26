package org.featherwhisker.rendereng.mixins;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.gl.WindowFramebuffer;
import org.featherwhisker.rendereng.main;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(WindowFramebuffer.class)
public class WindowFBMixin {

    @Redirect(method = "init", at = @At(value = "INVOKE", target = "com/mojang/blaze3d/platform/GlStateManager._texParameter(III)V"))
    private static void texParameterWrap(int i1, int i2, int i3){
        GlStateManager._texParameter(i1, i2, i3);
        main.log.info("Tex parameter bound!, err: " + GlStateManager._getError());
    }
    @Redirect(method = "init", at = @At(value = "INVOKE", target = "com/mojang/blaze3d/platform/GlStateManager._glFramebufferTexture2D(IIIII)V"))
    private static void framebufferWrap(int i1, int i2, int i3, int i4, int i5){
        // TODO: Re-enable depth buffer
        if(i2 == 36096){
            main.log.info("Disabling depth framebuffer for the funny!!");
            return;
        }
        GlStateManager._glFramebufferTexture2D(i1, i2, i3, i4, i5);

    }
}
