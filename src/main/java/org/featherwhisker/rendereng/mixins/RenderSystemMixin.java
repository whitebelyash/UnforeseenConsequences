package org.featherwhisker.rendereng.mixins;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(RenderSystem.class)
public class RenderSystemMixin {
    /**
     * @author Featherwhisker
     * @reason why not
     */
    @Overwrite
    public static void logicOp(GlStateManager.LogicOp op) {}
    /**
     * @author Featherwhisker
     * @reason why not
     */
    @Overwrite
    public static void polygonMode(int face, int mode) {}
}
