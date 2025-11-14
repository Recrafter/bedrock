package io.github.recrafter.bedrock.versions

import io.github.recrafter.bedrock.era.Release

object JavaCompatibility {

    private val REQUIREMENTS: Map<MinecraftVersion, Int> = mapOf(
        MinecraftVersion.EARLIEST to 8,
        Release.V_1_17 to 16,
        Release.V_1_18 to 17,
        Release.V_1_20_5 to 21,
    )

    fun getMinJavaVersion(minecraftVersion: MinecraftVersion): Int =
        REQUIREMENTS.entries
            .sortedWith(compareByDescending(MinecraftVersion.COMPARATOR) { it.key })
            .first { minecraftVersion >= it.key }
            .value
}
