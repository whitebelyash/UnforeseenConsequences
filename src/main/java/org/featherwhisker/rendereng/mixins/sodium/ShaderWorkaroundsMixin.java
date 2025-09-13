package org.featherwhisker.rendereng.mixins.sodium;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import org.featherwhisker.rendereng.util.ShaderConverter;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(targets = { "net.caffeinemc.mods.sodium.client.gl.shader.ShaderWorkarounds" })
public abstract class ShaderWorkaroundsMixin {
    @WrapMethod(
            method = "safeShaderSource", remap = false
    )
    private static void safeShaderSource(int glId, CharSequence source, Operation<Void> original) {
        original.call(glId, ShaderConverter.convert(source.toString()));
    }
}
