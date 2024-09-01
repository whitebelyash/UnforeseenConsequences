package org.featherwhisker.rendereng.mixins;

import net.minecraft.client.gl.GlDebug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static org.featherwhisker.rendereng.main.debugMode;

@Mixin(GlDebug.class)
public class GlDebugMixin {
    @Inject(method="info",at=@At("HEAD"),cancellable = true)
    private static void shutup(CallbackInfo ci) {
        if (!debugMode) {
            ci.cancel();
        }
    }
}
