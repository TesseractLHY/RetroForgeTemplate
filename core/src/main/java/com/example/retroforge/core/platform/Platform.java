package com.example.retroforge.core.platform;

/**
 * Static holder for the active {@link IPlatform} implementation.
 *
 * <p>Each version module installs its implementation early in mod loading
 * (during pre-init) via {@link #set(IPlatform)}. Shared code then calls
 * {@link #get()} to reach version-specific behaviour.</p>
 */
public final class Platform {

    private static IPlatform instance;

    private Platform() {}

    public static void set(IPlatform platform) {
        instance = platform;
    }

    public static IPlatform get() {
        if (instance == null) {
            throw new IllegalStateException(
                    "Platform has not been initialised yet. Call Platform.set(...) during pre-init.");
        }
        return instance;
    }
}
