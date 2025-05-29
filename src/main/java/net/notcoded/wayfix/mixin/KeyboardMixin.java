package net.notcoded.wayfix.mixin;

import net.minecraft.client.Keyboard;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.InputUtil;
import net.notcoded.wayfix.WayFix;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/*
- Credits to ishland
- https://github.com/ishland/fix-keyboard-on-linux
*/

@Mixin(Keyboard.class)
public class KeyboardMixin {
    @Inject(method = "onChar", at = @At("HEAD"), cancellable = true)
    private void charTyped(long window, int codePoint, int modifiers, CallbackInfo ci) {
        if (!WayFix.config.keyModifiersFix || !WayFix.isWayland()) return;
        if (Screen.hasControlDown() || InputUtil.isKeyPressed(MinecraftClient.getInstance().getWindow().getHandle(), GLFW.GLFW_KEY_LEFT_ALT)) ci.cancel();
    }
}
