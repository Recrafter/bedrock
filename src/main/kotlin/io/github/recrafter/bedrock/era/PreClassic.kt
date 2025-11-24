package io.github.recrafter.bedrock.era

import io.github.recrafter.bedrock.era.common.MinecraftEra
import io.github.recrafter.bedrock.versions.MinecraftVersion

enum class PreClassic(private val version: String) : MinecraftVersion {

    RD_132211_LAUNCHER("132211-launcher"),
    RD_132328_LAUNCHER("132328-launcher"),
    RD_160052_LAUNCHER("160052-launcher"),
    RD_161348_LAUNCHER("161348-launcher");

    override val normalizedSemver: String
        get() = "0.0.0-${era.prefix}$version"

    override val era: MinecraftEra
        get() = MinecraftEra.PRE_CLASSIC

    override val enumVersion: String
        get() = version
}
