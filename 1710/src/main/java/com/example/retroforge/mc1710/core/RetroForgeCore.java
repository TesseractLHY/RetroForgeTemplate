package com.example.retroforge.mc1710.core;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.gtnewhorizon.gtnhmixins.IEarlyMixinLoader;
import com.gtnewhorizon.gtnhmixins.builders.IMixins;

import cpw.mods.fml.relauncher.IFMLLoadingPlugin;

/**
 * FML coremod that registers this mod's mixin configuration.
 *
 * <p>On 1.7.10 a mixin config is not discovered from a bare {@code mixins.*.json}
 * on the classpath — a coremod implementing {@link IEarlyMixinLoader} (from
 * gtnhmixins, bundled in UniMixins) must hand the config name and mixin list to
 * the loader. See {@link Mixins} for the actual list.</p>
 *
 * <p>This coremod is loaded:</p>
 * <ul>
 *   <li><b>dev</b> — via the {@code -Dfml.coreMods.load=...} JVM arg in build.gradle,
 *       because the jar manifest is not on the runClient classpath;</li>
 *   <li><b>production</b> — via the {@code FMLCorePlugin} jar manifest attribute.</li>
 * </ul>
 */
@IFMLLoadingPlugin.MCVersion("1.7.10")
public final class RetroForgeCore implements IFMLLoadingPlugin, IEarlyMixinLoader {

    @Override
    public String getMixinConfig() {
        return "mixins.retroforge.json";
    }

    @Override
    public List<String> getMixins(Set<String> loadedCoreMods) {
        return IMixins.getEarlyMixins(Mixins.class, loadedCoreMods);
    }

    @Override
    public String[] getASMTransformerClass() {
        return null;
    }

    @Override
    public String getModContainerClass() {
        return null;
    }

    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data) {}

    @Override
    public String getAccessTransformerClass() {
        return null;
    }
}
