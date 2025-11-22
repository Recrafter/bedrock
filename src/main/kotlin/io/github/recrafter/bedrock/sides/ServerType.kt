package io.github.recrafter.bedrock.sides

import io.github.recrafter.bedrock.era.Release
import io.github.recrafter.bedrock.era.common.MinecraftEra
import io.github.recrafter.bedrock.versions.MinecraftVersion
import io.github.recrafter.bedrock.versions.compareTo

enum class ServerType(val startMinecraftVersion: MinecraftVersion) {

    INTERNAL(MinecraftVersion.EARLIEST),
    SPLIT(MinecraftEra.BETA.firstVersion()),
    INTEGRATED(Release.V_1_3_1);

    companion object {
        fun of(minecraftVersion: MinecraftVersion): ServerType =
            ServerType.entries
                .sortedWith(compareByDescending(MinecraftVersion.COMPARATOR) { it.startMinecraftVersion })
                .first { minecraftVersion >= it.startMinecraftVersion }
    }
}
