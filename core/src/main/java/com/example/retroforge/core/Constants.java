package com.example.retroforge.core;

/**
 * Version-agnostic constants shared by every module.
 *
 * <p>These are compile-time constants so they can be used directly in Forge's
 * {@code @Mod} annotation from the version modules.</p>
 *
 * <p>Keep the values in sync with {@code gradle.properties}.</p>
 */
public final class Constants {

    public static final String MOD_ID = "retroforge";
    public static final String MOD_NAME = "RetroForge Template";
    public static final String VERSION = "1.0.0";

    private Constants() {}
}
