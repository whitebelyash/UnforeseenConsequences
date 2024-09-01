# Unforeseen Consequences - OpenGL ES 3.0 in Minecraft
### Note: This mod REQUIRES the `GL_OES_mapbuffer` extension to work at all!

> Prepare for unforeseen consequences.  
> -- <cite>The G-Man, Half Life 2 Episode 2</cite>

This mod hacks OpenGL ES 3.0 into Minecraft to try and get better performance
It also disables GLFW from throwing errors for the time being

This mod is under development and is not guaranteed to work with any other mod (especially mods like Sodium)
Part of my plan for this mod is to optimize some parts of Minecraft to run better and so it works without forcing `GLFW_CONTEXT_NO_ERROR`

Why is it named unforeseen consequences? I didn't realize how destructive this change is before I started making the mod and HL2 is my favorite game. 

Note that this does not work currently on the HD 4000, I need to pinpoint what causes the GLFW attribute causes this but at the moment you need a system that has at least GL ES 3.1 or 3.2
