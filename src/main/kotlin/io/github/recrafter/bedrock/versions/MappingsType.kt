package io.github.recrafter.bedrock.versions

import io.github.recrafter.bedrock.era.Release
import io.github.recrafter.bedrock.era.common.MinecraftEra
import io.github.recrafter.bedrock.sides.ModSide
import kotlin.text.compareTo

enum class MappingsType(val startMinecraftVersion: MinecraftVersion, val sides: List<ModSide>) {

    CLIENT(
        MinecraftVersion.EARLIEST,
        listOf(ModSide.CLIENT),
    ),
    SPLIT(
        MinecraftEra.BETA.firstVersion(),
        listOf(ModSide.CLIENT, ModSide.SERVER),
    ),
    MERGED(
        Release.V_1_3_1,
        listOf(ModSide.CLIENT, ModSide.SERVER),
    );

    companion object {
        fun of(minecraftVersion: MinecraftVersion): MappingsType =
            values()
                .sortedWith(compareByDescending(MinecraftVersion.COMPARATOR) { it.startMinecraftVersion })
                .first { minecraftVersion >= it.startMinecraftVersion }
    }
}
