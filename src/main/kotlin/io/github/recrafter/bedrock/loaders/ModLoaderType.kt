package io.github.recrafter.bedrock.loaders

import io.github.recrafter.bedrock.era.Beta
import io.github.recrafter.bedrock.era.Release
import io.github.recrafter.bedrock.versions.MappingsType
import io.github.recrafter.bedrock.versions.MinecraftVersion
import io.github.recrafter.bedrock.versions.MinecraftVersionRange
import io.github.recrafter.bedrock.versions.rangeTo

enum class ModLoaderType(val displayName: String, val supportedVersionRanges: List<MinecraftVersionRange>) {
    FABRIC(
        "Fabric",
        listOf(
            Release.V_1_14_4..MinecraftVersion.LATEST,
        )
    ),
    QUILT(
        "Quilt",
        listOf(
            Release.V_1_18_2..MinecraftVersion.LATEST,
        )
    ),
    LEGACY_FABRIC(
        "LegacyFabric",
        listOf(
            MappingsType.MERGED.startMinecraftVersion..Release.V_1_13_2,
        )
    ),
    ORNITHE(
        "Ornithe",
        listOf(
            MinecraftVersion.EARLIEST..Release.V_1_13_2,
        )
    ),
    BABRIC(
        "Babric",
        listOf(
            MinecraftVersionRange(Beta.B_1_7_3),
        )
    ),
    FORGE(
        "Forge",
        listOf(
            Release.V_1_20_4..MinecraftVersion.LATEST,
        )
    ),
    NEOFORGE(
        "NeoForge",
        listOf(
            Release.V_1_20_2..MinecraftVersion.LATEST,
        )
    ),
}
