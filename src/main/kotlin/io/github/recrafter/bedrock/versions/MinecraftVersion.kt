package io.github.recrafter.bedrock.versions

import io.github.diskria.gradle.utils.extensions.common.gradleError
import io.github.diskria.kotlin.utils.extensions.*
import io.github.diskria.kotlin.utils.extensions.common.failWithDetails
import io.github.recrafter.bedrock.era.common.MinecraftEra
import io.github.recrafter.bedrock.sides.ServerType

interface MinecraftVersion {

    val normalizedSemver: String
        get() = getEnumVersion()

    val era: MinecraftEra

    fun getEnumVersion(): String

    companion object {
        val EARLIEST: MinecraftVersion = MinecraftEra.entries.first().firstVersion()
        val LATEST: MinecraftVersion = MinecraftEra.entries.last().lastVersion()

        val COMPARATOR: Comparator<MinecraftVersion> = Comparator { before, after -> before.compareTo(after) }

        fun parse(version: String): MinecraftVersion =
            parseOrNull(version) ?: failWithDetails("Failed to parse Minecraft version $version")

        fun parseOrNull(version: String): MinecraftVersion? {
            val era = MinecraftEra.parse(version)
            val normalizedVersion = version.toSemverOrNull()?.toVersion(dropZeroPatch = true) ?: version
            return era.versions.find { normalizedVersion == era.prefix + it.getEnumVersion() }
        }

        fun of(enum: Enum<*>): MinecraftVersion =
            enum as MinecraftVersion
    }
}

internal fun MinecraftVersion.asEnum(): Enum<*> =
    this as Enum<*>

internal fun MinecraftVersion.getOrdinal(): Int =
    asEnum().ordinal

operator fun MinecraftVersion.compareTo(other: MinecraftVersion): Int =
    if (era == other.era) getOrdinal() - other.getOrdinal()
    else era.ordinal - other.era.ordinal

operator fun MinecraftVersion.rangeTo(other: MinecraftVersion): MinecraftVersionRange =
    MinecraftVersionRange.of(this, other)

operator fun MinecraftVersionRange.contains(version: MinecraftVersion): Boolean =
    version >= min && version <= max

fun MinecraftVersion.asString(): String =
    era.prefix + getEnumVersion()

fun MinecraftVersion.previousOrNull(): MinecraftVersion? =
    asEnum().previousEnumOrNull()?.let { MinecraftVersion.of(it) } ?: era.previousOrNull()?.lastVersion()

fun MinecraftVersion.previousOrSelf(): MinecraftVersion =
    previousOrNull() ?: this

fun MinecraftVersion.nextOrNull(): MinecraftVersion? =
    asEnum().nextEnumOrNull()?.let { MinecraftVersion.of(it) } ?: era.nextOrNull()?.firstVersion()

fun MinecraftVersion.nextOrSelf(): MinecraftVersion =
    nextOrNull() ?: this

val MinecraftVersion.previous: MinecraftVersion
    get() = if (this == MinecraftVersion.EARLIEST) {
        gradleError("Version ${asString()} is the earliest known Minecraft version.")
    } else {
        previousOrNull() ?: gradleError("Failed to resolve previous version for ${asString()}.")
    }

val MinecraftVersion.next: MinecraftVersion
    get() = if (this == MinecraftVersion.LATEST) {
        gradleError("Version ${asString()} is the latest known Minecraft version.")
    } else {
        nextOrNull() ?: gradleError("Failed to resolve next version for ${asString()}.")
    }

val MinecraftVersion.minJavaVersion: Int
    get() = JavaCompatibility.getMinJavaVersion(this)

val MinecraftVersion.isInternalServer: Boolean
    get() = ServerType.of(this) == ServerType.INTERNAL

val MinecraftVersion.isIntegratedServer: Boolean
    get() = ServerType.of(this) == ServerType.INTEGRATED
