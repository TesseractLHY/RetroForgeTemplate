package com.example.retroforge.mc1710;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.gtnewhorizon.gtnhmixins.IEarlyMixinLoader;

import cpw.mods.fml.relauncher.IFMLLoadingPlugin;

/**
 * FML coremod that registers this mod's mixin configuration.
 *
 * <p>On 1.7.10 a mixin config is not discovered from a bare {@code mixins.*.json}
 * on the classpath — a coremod implementing {@link IEarlyMixinLoader} (from
 * gtnhmixins, bundled in UniMixins) must name the config. The mixins themselves
 * are listed in {@code mixins.retroforge.json} ({@code client}/{@code server}/
 * {@code mixins} arrays), so {@link #getMixins} returns an empty list.</p>
 *
 * <p>The coremod is discovered automatically — no {@code -Dfml.coreMods.load}
 * is needed: in <b>dev</b> RetroFuturaGradle's launcher picks up the coremod
 * from the classpath, and in <b>production</b> FML reads the {@code FMLCorePlugin}
 * jar manifest attribute.</p>
 */
@IFMLLoadingPlugin.MCVersion("1.7.10")
public final class RetroForgeCore implements IFMLLoadingPlugin, IEarlyMixinLoader {

    @Override
    public String getMixinConfig() {
        return "mixins.retroforge.json";
    }

    @Override
    public List<String> getMixins(Set<String> loadedCoreMods) {
        return Collections.emptyList();
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
