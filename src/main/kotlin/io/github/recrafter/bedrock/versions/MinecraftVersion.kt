package io.github.recrafter.bedrock.versions

import io.github.diskria.kotlin.utils.extensions.common.failWithDetails
import io.github.diskria.kotlin.utils.extensions.previousEnumOrNull
import io.github.diskria.kotlin.utils.extensions.previousOrNull
import io.github.diskria.kotlin.utils.extensions.toSemverOrNull
import io.github.recrafter.bedrock.era.common.MinecraftEra

interface MinecraftVersion {

    val normalizedSemver: String
        get() = getEnumVersion()

    fun getEra(): MinecraftEra

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
            return era.versions.find { normalizedVersion == era.versionPrefix + it.getEnumVersion() }
        }

        fun of(enum: Enum<*>): MinecraftVersion =
            enum as MinecraftVersion
    }
}

operator fun MinecraftVersion.compareTo(other: MinecraftVersion): Int {
    val era = getEra()
    val otherEra = other.getEra()
    return when {
        era == otherEra -> getOrdinal() - other.getOrdinal()
        else -> era.ordinal - otherEra.ordinal
    }
}

operator fun MinecraftVersion.rangeTo(other: MinecraftVersion): MinecraftVersionRange =
    MinecraftVersionRange(this, other)

fun MinecraftVersion.asEnum(): Enum<*> =
    this as Enum<*>

fun MinecraftVersion.asString(): String =
    getEra().versionPrefix + getEnumVersion()

fun MinecraftVersion.getOrdinal(): Int =
    asEnum().ordinal

fun MinecraftVersion.previousOrNull(): MinecraftVersion? =
    asEnum().previousEnumOrNull()?.let { MinecraftVersion.of(it) } ?: getEra().previousOrNull()?.lastVersion()

val MinecraftVersion.previous: MinecraftVersion
    get() = previousOrNull() ?: error("Version has no previous version")

val MinecraftVersion.minJavaVersion: Int
    get() = JavaCompatibility.getMinJavaVersion(this)
