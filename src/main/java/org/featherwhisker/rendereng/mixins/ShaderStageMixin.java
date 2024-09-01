package org.featherwhisker.rendereng.mixins;

import net.minecraft.client.gl.ShaderStage;

import org.jetbrains.annotations.NotNull;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import static com.mojang.blaze3d.platform.GlStateManager.glShaderSource;
import static org.featherwhisker.rendereng.main.*;

@Mixin(ShaderStage.class)
public class ShaderStageMixin {
	@Redirect(
			method="load",
			at=@At(
					value="INVOKE",
					target="com/mojang/blaze3d/platform/GlStateManager.glShaderSource (ILjava/util/List;)V"
			)
	)
	private static void glShaderSourceIntercept(int i,@NotNull java. util. List<String> strings) {
		if (shouldConvertShaders) {
			for (int i1 = 0; i1 < strings.size(); i1++) {
				var a = strings.get(i1);
				strings.set(i1,convertShader(a,i1));
			}
			//log.info(strings.toString());
		}
		glShaderSource(i,strings);
	}
}
