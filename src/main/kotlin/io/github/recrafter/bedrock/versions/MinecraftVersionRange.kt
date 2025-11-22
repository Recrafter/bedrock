package io.github.recrafter.bedrock.versions

import io.github.diskria.kotlin.utils.Constants
import io.github.diskria.kotlin.utils.extensions.primitives.repeat
import io.github.diskria.kotlin.utils.extensions.splitToPairOrNull
import io.github.recrafter.bedrock.era.common.MinecraftEra

open class MinecraftVersionRange(val min: MinecraftVersion, val max: MinecraftVersion = min) {

    fun expand(): List<MinecraftVersion> {
        val minEra = min.getEra()
        val maxEra = max.getEra()

        return MinecraftEra.entries.filter { it in minEra..maxEra }.flatMap { era ->
            val versions = era.versions
            when {
                minEra == maxEra -> versions.filter { it >= min && it <= max }
                era == minEra -> versions.filter { it >= min }
                era == maxEra -> versions.filter { it <= max }
                else -> versions
            }
        }.sortedWith(MinecraftVersion.COMPARATOR)
    }

    fun includesVersion(minecraftVersion: MinecraftVersion): Boolean =
        minecraftVersion >= min && minecraftVersion <= max

    fun asString(): String =
        buildString {
            append(min.asString())
            if (max != min) {
                append(Constants.Char.HYPHEN)
                append(max.asString())
            }
        }

    companion object {
        fun parse(range: String): MinecraftVersionRange =
            range
                .splitToPairOrNull(Constants.Char.HYPHEN.repeat(2))
                ?.let { (min, max) -> MinecraftVersion.parse(min)..MinecraftVersion.parse(max) }
                ?: MinecraftVersionRange(MinecraftVersion.parse(range))
    }
}
