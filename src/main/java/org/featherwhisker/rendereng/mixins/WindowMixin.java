package org.featherwhisker.rendereng.mixins;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.MinecraftVersion;
import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.client.util.Window;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import static org.featherwhisker.rendereng.main.log;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL30.*;
@Environment(EnvType.CLIENT)
@Mixin(Window.class)
public class WindowMixin {
    // Inject our function right before the window is created
    @Inject(method = "<init>", at = @At(value = "INVOKE",remap = false, target = "org/lwjgl/glfw/GLFW.glfwCreateWindow (IILjava/lang/CharSequence;JJ)J", shift = At.Shift.BEFORE))
    public void injected(CallbackInfo ci) {
        //Get frame name
        String frameName = "Minecraft "+MinecraftVersion.create().getName();

        // Throw out whatever vanilla tells GLFW
        glfwDefaultWindowHints();

        // Set OpenGL version
        glfwWindowHint(GLFW_CLIENT_API, GLFW_OPENGL_ES_API);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 0);

        // Rendering related things
        glfwWindowHint(GLFW_REFRESH_RATE, GL_DONT_CARE);
        glfwWindowHint(GLFW_SAMPLES, GL_NONE);

        // Misc OpenGL hints
        glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GL_TRUE);
        glfwWindowHint(GLFW_CONTEXT_CREATION_API, GLFW_NATIVE_CONTEXT_API);
        glfwWindowHint(GLFW_CONTEXT_NO_ERROR, GL_TRUE); //more performance + not having to rewrite texture attaching

        //Platform Specific
        try {
            String str = "Running on: ";
            if (glfwGetPlatform() == GLFW_PLATFORM_X11) {
                str = str + "*nix with X11";
                glfwWindowHintString(GLFW_X11_CLASS_NAME, "Minecraft");
                glfwWindowHintString(GLFW_X11_INSTANCE_NAME, frameName);
            } else if (glfwGetPlatform() == GLFW_PLATFORM_WAYLAND) {
                str = str + "*nix with Wayland";
            } else if (glfwGetPlatform() == GLFW_PLATFORM_COCOA) {
                str = str + "macOS";
                glfwWindowHintString(GLFW_COCOA_FRAME_NAME, frameName);
            } else if (glfwGetPlatform() == GLFW_PLATFORM_WIN32) {
                str = str + "Windows";
            } else {
                str = str +  "Unknown platform";
            }
            log.info(str);
            if (str != "Running on: Windows"){
                log.info("There are known issues running on non-windows platforms.");
            }
        } catch(Exception ignored) {
            log.info("Could not detect platform, old GLFW version?");
        }
    }
}