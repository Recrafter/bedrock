package io.github.recrafter.bedrock.era.common

import io.github.diskria.kotlin.utils.Constants
import io.github.recrafter.bedrock.era.*
import io.github.recrafter.bedrock.versions.MinecraftVersion

enum class MinecraftEra(val versions: List<MinecraftVersion>, val versionPrefix: String) {

    PRE_CLASSIC(PreClassic.entries, "rd-"),
    CLASSIC(Classic.entries, "c"),
    INDEV(Indev.entries, "in-"),
    INFDEV(Infdev.entries, "inf-"),
    ALPHA(Alpha.entries, "a"),
    BETA(Beta.entries, "b"),
    RELEASE(Release.entries, Constants.Char.EMPTY);

    fun firstVersion(): MinecraftVersion =
        versions.first()

    fun lastVersion(): MinecraftVersion =
        versions.last()

    companion object {
        fun parse(version: String): MinecraftEra =
            MinecraftEra.entries.filterNot { it == RELEASE }.find { version.startsWith(it.versionPrefix) } ?: RELEASE
    }
}
