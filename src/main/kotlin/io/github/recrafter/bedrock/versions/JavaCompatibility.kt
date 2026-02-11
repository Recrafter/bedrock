package io.github.recrafter.bedrock.versions

import io.github.recrafter.bedrock.era.FullRelease

object JavaCompatibility {

    private val REQUIREMENTS: Map<MinecraftVersion, Int> = mapOf(
        MinecraftVersion.EARLIEST to 8,
        FullRelease.V_1_17 to 16,
        FullRelease.V_1_18 to 17,
        FullRelease.V_1_20_5 to 21,
//        FullRelease.V_26_1 to 25,
    )

    fun getMinJavaVersion(minecraftVersion: MinecraftVersion): Int =
        REQUIREMENTS.entries
            .sortedWith(compareByDescending(MinecraftVersion.COMPARATOR) { it.key })
            .first { minecraftVersion >= it.key }
            .value
}
