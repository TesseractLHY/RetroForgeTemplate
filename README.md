# RetroForge Template

A multi-module Minecraft Forge mod template that targets **both 1.7.10 and
1.12.2** from one codebase, sharing a common core.

```
RetroForgeTemplate/
├── core/     version-agnostic shared code (pure Java, no Minecraft references)
├── 1710/     Minecraft 1.7.10 module  (cpw.mods.fml,  Forge 10.13.x)
└── 1122/     Minecraft 1.12.2 module  (net.minecraftforge.fml, Forge 14.23.x)
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

## Requirements / gotchas

- **JDK for the Gradle daemon:** the daemon must run on a JDK new enough to load
  RetroFuturaGradle's plugin classes. Point `JAVA_HOME` at a recent JDK (this
  template was verified on Azul Zulu 25). Mods themselves still compile to
  Java 8 via an auto-provisioned toolchain.
- Do **not** commit a `gradle/gradle-daemon-jvm.properties` that pins an older
  JDK — it can select a Java too old to load RFG and break the build. It is
  git-ignored for that reason.
