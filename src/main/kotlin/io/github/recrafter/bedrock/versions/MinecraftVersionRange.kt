package io.github.recrafter.bedrock.versions

import io.github.diskria.gradle.utils.extensions.common.gradleError
import io.github.diskria.kotlin.utils.extensions.splitToPairOrNull
import io.github.recrafter.bedrock.era.common.MinecraftEra

open class MinecraftVersionRange private constructor(
    val min: MinecraftVersion,
    val max: MinecraftVersion,
) {
    fun expand(): List<MinecraftVersion> =
        MinecraftEra.entries
            .filter { it in min.era..max.era }
            .flatMap { era ->
                when {
                    min.era == max.era -> era.versions.filter { it in min..max }
                    era == min.era -> era.versions.filter { it >= min }
                    era == max.era -> era.versions.filter { it <= max }
                    else -> era.versions
                }
            }
            .sortedWith(MinecraftVersion.COMPARATOR)

    fun asString(separator: String): String =
        buildString {
            append(min.asString())
            if (max != min) {
                append(separator)
                append(max.asString())
            }
        }

    override fun toString(): String =
        asString(DEFAULT_SEPARATOR)

    companion object {
        private const val DEFAULT_SEPARATOR: String = ".."

        fun of(min: MinecraftVersion, max: MinecraftVersion = min): MinecraftVersionRange {
            require(max >= min) {
                gradleError(
                    "Invalid Minecraft version range: " +
                            "max (${max.asString()}) must not be lower than min (${min.asString()})."
                )
            }
            return MinecraftVersionRange(min, max)
        }

        fun parseOrNull(range: String, separator: String = DEFAULT_SEPARATOR): MinecraftVersionRange? {
            val (minString, maxString) = range.splitToPairOrNull(separator) ?: (range to range)
            val min = MinecraftVersion.parseOrNull(minString) ?: return null
            val max = MinecraftVersion.parseOrNull(maxString) ?: return null
            return of(min, max)
        }

        fun parse(range: String, separator: String = DEFAULT_SEPARATOR): MinecraftVersionRange =
            parseOrNull(range, separator) ?: gradleError("Invalid Minecraft version range: $range")
    }
}
