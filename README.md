# RetroForge Template

A multi-module Minecraft Forge mod template that targets **both 1.7.10 and
1.12.2** from one codebase, sharing a common core.

```
RetroForgeTemplate/
├── core/     version-agnostic shared code (pure Java, no Minecraft references)
├── 1710/     Minecraft 1.7.10 module  (cpw.mods.fml,  Forge 10.13.x, UniMixins)
└── 1122/     Minecraft 1.12.2 module  (net.minecraftforge.fml, Forge 14.23.x, MixinBooter)
```

Both version modules apply [RetroFuturaGradle](https://github.com/GTNewHorizons/RetroFuturaGradle)
(RFG) independently — the same build plugin drives both Minecraft versions, only
`mcVersion` (and the 1.12.2 MCP mappings) differ.

## How the modules fit together

`core` knows nothing about Minecraft. Anything version-specific is reached
through a small abstraction, so shared feature code lives in one place:

- `core` — `Constants`, `CommonModLogic` (shared logic), and the
  `IPlatform` / `Platform` seam.
- `1710` / `1122` — the `@Mod` entry point plus a `Platform17xx` / `Platform11xx`
  implementation of `IPlatform`. Each `@Mod` installs its platform during
  pre-init, then calls `CommonModLogic.init()`.

Write new shared logic in `core` against `Platform.get()`; add a method to
`IPlatform` whenever you need something a concrete Minecraft version provides.

The compiled `core` classes are embedded into each version's mod jar, so the
shipped jar is self-contained.

## Build & run

Each version module gets its own RFG tasks:

```bash
# Build both mod jars (output in <module>/build/libs/)
./gradlew build

# Launch a dev client for a specific version
./gradlew :1710:runClient
./gradlew :1122:runClient

# Dev server
./gradlew :1710:runServer
./gradlew :1122:runServer
```

The first RFG task per module downloads and decompiles Minecraft/Forge — that
run is slow; later runs are cached.

## Configuration

All mod metadata lives in `gradle.properties` (`mod_id`, `mod_name`,
`mod_version`, `mod_description`, `mod_authors`). These feed the jar name and
the `mcmod.info` / `pack.mcmeta` resources.

The Java code mirrors id/name/version in
`core/.../Constants.java` (used in the `@Mod` annotation, which needs
compile-time constants). **Keep `Constants.java` in sync with
`gradle.properties`.**

To rebrand the template, rename the `com.example.retroforge` package in all
three modules and update `root_package` in `gradle.properties`.

## Mixins

Both modules are wired for [Mixin](https://github.com/SpongePowered/Mixin),
each using its version's standard loader:

- **1710 → [UniMixins](https://github.com/LegacyModdingMC/UniMixins)**
  (`io.github.legacymoddingmc:unimixins`). 1.7.10 has **no** automatic
  `mixins.*.json` discovery, so a small coremod (`core/RetroForgeCore`,
  implementing gtnhmixins' `IEarlyMixinLoader`) registers the config by name. The
  mixins themselves are just listed in the json's `client` / `server` / `mixins`
  arrays. The coremod is discovered automatically — RetroFuturaGradle's launcher
  finds it in **dev**, and FML reads the `FMLCorePlugin` jar manifest in
  **production** (no `-Dfml.coreMods.load` needed).
- **1122 → [MixinBooter](https://github.com/CleanroomMC/MixinBooter)**
  (`zone.rong:mixinbooter`). The config is declared through the jar manifest
  attribute `MixinConfigs`.

RetroFuturaGradle's `modUtils.enableMixins(...)` handles refmap generation and
reobfuscation for both.

Each module ships a sample `mixin/MixinMinecraft.java` that logs a line from the
`Minecraft` constructor — run a client and look for `MixinMinecraft applied on ...`
to confirm mixins load. To add your own, in **either** module: put the mixin
class under `<module>/.../mixin/` and list its simple name in the `client` /
`server` / `mixins` arrays of `mixins.retroforge.json`. (On 1710 the coremod
already registers the config, so nothing else is needed.)

The config filename, its `refmap` field, the 1710 `FMLCorePlugin` / 1122
`MixinConfigs` manifest values are all derived from `mod_id`. If you change
`mod_id`, rename the `mixins.<id>.json` files and update the `package` / `refmap`
fields inside them.

## Requirements / gotchas

- **JDK for the Gradle daemon:** the daemon must run on a JDK new enough to load
  RetroFuturaGradle's plugin classes. Point `JAVA_HOME` at a recent JDK (this
  template was verified on Azul Zulu 25). Mods themselves still compile to
  Java 8 via an auto-provisioned toolchain.
- Do **not** commit a `gradle/gradle-daemon-jvm.properties` that pins an older
  JDK — it can select a Java too old to load RFG and break the build. It is
  git-ignored for that reason.
