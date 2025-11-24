package io.github.recrafter.bedrock.era.common

import io.github.diskria.kotlin.utils.Constants
import io.github.diskria.kotlin.utils.extensions.common.`Train-Case`
import io.github.diskria.kotlin.utils.extensions.mappers.getName
import io.github.recrafter.bedrock.era.*
import io.github.recrafter.bedrock.versions.MinecraftVersion

enum class MinecraftEra(val versions: List<MinecraftVersion>, val prefix: String) {

    PRE_CLASSIC(PreClassic.entries, "rd-"),
    CLASSIC(Classic.entries, "c"),
    INDEV(Indev.entries, "in-"),
    INFDEV(Infdev.entries, "inf-"),
    ALPHA(Alpha.entries, "a"),
    BETA(Beta.entries, "b"),
    RELEASE(Release.entries, Constants.Char.EMPTY);

    val displayName: String
        get() = getName(`Train-Case`)

    fun firstVersion(): MinecraftVersion =
        versions.first()

    fun lastVersion(): MinecraftVersion =
        versions.last()

    companion object {
        fun parse(version: String): MinecraftEra =
            MinecraftEra.entries
                .filterNot { it == RELEASE }
                .find { version.startsWith(it.prefix) }
                ?: RELEASE
    }
}
