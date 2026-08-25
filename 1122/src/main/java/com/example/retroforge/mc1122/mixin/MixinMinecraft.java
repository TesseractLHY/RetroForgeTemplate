package com.example.retroforge.mc1122.mixin;

import net.minecraft.client.Minecraft;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.example.retroforge.mc1122.RetroForge1122;

/**
 * Minimal example mixin. Injects at the end of the {@link Minecraft}
 * constructor and logs a line, so you can confirm mixins are being applied.
 *
 * <p>Targeting {@code <init>} needs no obfuscation mapping, which keeps this
 * example robust. Delete it once you add real mixins.</p>
 */
@Mixin(Minecraft.class)
public class MixinMinecraft {

    @Inject(method = "<init>", at = @At("RETURN"))
    private void retroforge$onConstructed(CallbackInfo ci) {
        RetroForge1122.LOG.info("MixinMinecraft applied on 1.12.2");
    }
}
