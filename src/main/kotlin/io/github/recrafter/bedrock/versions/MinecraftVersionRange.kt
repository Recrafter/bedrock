package io.github.recrafter.bedrock.versions

import io.github.diskria.gradle.utils.extensions.common.gradleError
import io.github.diskria.gradle.utils.extensions.common.requireGradle
import io.github.diskria.kotlin.utils.Constants
import io.github.diskria.kotlin.utils.extensions.primitives.repeat
import io.github.diskria.kotlin.utils.extensions.splitToPairOrNull
import io.github.recrafter.bedrock.era.common.MinecraftEra

open class MinecraftVersionRange private constructor(
    val min: MinecraftVersion,
    val max: MinecraftVersion,
) : Comparable<MinecraftVersionRange> {

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

    fun asString(separator: String = DEFAULT_SEPARATOR): String =
        buildString {
            append(min.asString())
            if (!isSingleVersion()) {
                append(separator)
                append(max.asString())
            }
        }

    fun isSingleVersion(): Boolean =
        min == max

    override fun toString(): String =
        asString()

    override fun compareTo(other: MinecraftVersionRange): Int =
        MinecraftVersion.COMPARATOR.compare(min, other.min)

    companion object {
        val PROJECT_NAME_SEPARATOR: String = Constants.Char.HYPHEN.repeat(2)

        private const val DEFAULT_SEPARATOR: String = Constants.Char.EN_DASH.toString()

        fun of(min: MinecraftVersion, max: MinecraftVersion = min): MinecraftVersionRange {
            requireGradle(max >= min) {
                "Invalid Minecraft version range ${min.asString()}$DEFAULT_SEPARATOR${max.asString()}: " +
                        "max must not be lower than min."
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
