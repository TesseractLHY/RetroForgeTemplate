package com.example.retroforge.mc1710.core;

import com.gtnewhorizon.gtnhmixins.builders.IMixins;
import com.gtnewhorizon.gtnhmixins.builders.MixinBuilder;

/**
 * Declares this mod's mixins and which side/phase each applies on. gtnhmixins
 * resolves the names below against the {@code package} declared in
 * {@code mixins.retroforge.json}, so {@code "MixinMinecraft"} means
 * {@code com.example.retroforge.mc1710.mixin.MixinMinecraft}.
 *
 * <p>Add an entry per feature. Use {@code addClientMixins} / {@code addServerMixins}
 * / {@code addCommonMixins} to scope by side. {@link RetroForgeCore} feeds these
 * to the loader via {@link IMixins#getEarlyMixins}.</p>
 */
public enum Mixins implements IMixins {

    MINECRAFT(new MixinBuilder("Example client mixin")
            .addClientMixins("MixinMinecraft")
            .setPhase(Phase.EARLY));

    private final MixinBuilder builder;

    Mixins(MixinBuilder builder) {
        this.builder = builder;
    }

    @Override
    public MixinBuilder getBuilder() {
        return builder;
    }
}
