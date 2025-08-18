package org.featherwhisker.rendereng.mixins;

import net.minecraft.client.gl.CompiledShader;

import net.minecraft.util.Identifier;
import org.featherwhisker.rendereng.util.ShaderConverter;
import org.jetbrains.annotations.NotNull;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static com.mojang.blaze3d.platform.GlStateManager.glShaderSource;
import static org.featherwhisker.rendereng.main.*;

@Mixin(CompiledShader.class)
public class CompiledShaderMixin {
	@Redirect(
			method = "compile",
			at = @At(
					value = "INVOKE",
					target = "com/mojang/blaze3d/platform/GlStateManager.glShaderSource (ILjava/lang/String;)V"
			)
	)
	private static void glShaderSourceIntercept(int i, @NotNull String source) {
		source = ShaderConverter.convert(source);
		log.info("Converted source: " + source);
		glShaderSource(i, source);
	}
	@Inject(method = "compile", at = @At(value = "HEAD"))
	private static CompiledShader compileHead(Identifier id, CompiledShader.Type type, String source, CallbackInfoReturnable<CompiledShader> info){
		log.info("Compiling shader: " + id.getNamespace() + ":" + id.getPath());
		return info.getReturnValue();
	}
}
