package com.example.retroforge.core;

import com.example.retroforge.core.platform.Platform;

/**
 * Example of version-agnostic mod logic.
 *
 * <p>This runs identically on 1.7.10 and 1.12.2. It reaches anything
 * version-specific through {@link Platform}, so put shared feature code here
 * instead of duplicating it in each version module.</p>
 */
public final class CommonModLogic {

    private CommonModLogic() {}

    /** Call this from each version's pre-init, after the platform is installed. */
    public static void init() {
        Platform.get().logInfo(
                "Hello from " + Constants.MOD_NAME + " v" + Constants.VERSION
                        + " running on " + Platform.get().platformName());
    }
}
