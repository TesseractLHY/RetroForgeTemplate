package com.example.retroforge.core.platform;

/**
 * The seam between shared logic and version-specific Minecraft APIs.
 *
 * <p>The core module knows nothing about Minecraft. Whenever shared code needs
 * to touch something that only exists in a concrete Minecraft version (logging,
 * registries, config, the game directory, ...), it goes through this interface.
 * Each version module ({@code :1710}, {@code :1122}) provides its own
 * implementation.</p>
 *
 * <p>Add methods here as your shared logic grows, then implement them in
 * {@code Platform1710} and {@code Platform1122}.</p>
 */
public interface IPlatform {

    /** Human-readable name of the running platform, e.g. "Forge 1.7.10". */
    String platformName();

    /** Log an informational message through the version's own logger. */
    void logInfo(String message);
}
