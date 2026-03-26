package io.github.recrafter.bedrock.versions

import io.github.recrafter.bedrock.era.FullRelease

object CompatibilityHelper {

    private val MIN_JAVA_VERSIONS: Map<MinecraftVersion, Int> = mapOf(
        MinecraftVersion.EARLIEST to 8,
        FullRelease.V_1_17 to 16,
        FullRelease.V_1_18 to 17,
        FullRelease.V_1_20_5 to 21,
        FullRelease.V_26_1 to 25,
    )

    private val DATA_PACK_FORMATS: Map<MinecraftVersion, String> = mapOf(
        FullRelease.V_1_13 to "4",
        FullRelease.V_1_15 to "5",
        FullRelease.V_1_16_2 to "6",
        FullRelease.V_1_17 to "7",
        FullRelease.V_1_18 to "8",
        FullRelease.V_1_18_2 to "9",
        FullRelease.V_1_19 to "10",
        FullRelease.V_1_19_4 to "12",
        FullRelease.V_1_20 to "15",
        FullRelease.V_1_20_2 to "18",
        FullRelease.V_1_20_3 to "26",
        FullRelease.V_1_20_5 to "41",
        FullRelease.V_1_21 to "48",
        FullRelease.V_1_21_2 to "57",
        FullRelease.V_1_21_4 to "61",
        FullRelease.V_1_21_5 to "71",
        FullRelease.V_1_21_6 to "80",
        FullRelease.V_1_21_7 to "81",
        FullRelease.V_1_21_9 to "88.0",
        FullRelease.V_1_21_11 to "94.1",
        FullRelease.V_26_1 to "101.1",
    )

    private val RESOURCE_PACK_FORMATS: Map<MinecraftVersion, String> = mapOf(
        FullRelease.V_1_6_1 to "1",
        FullRelease.V_1_9 to "2",
        FullRelease.V_1_11 to "3",
        FullRelease.V_1_13 to "4",
        FullRelease.V_1_15 to "5",
        FullRelease.V_1_16_2 to "6",
        FullRelease.V_1_17 to "7",
        FullRelease.V_1_18 to "8",
        FullRelease.V_1_19 to "9",
        FullRelease.V_1_19_3 to "12",
        FullRelease.V_1_19_4 to "13",
        FullRelease.V_1_20 to "15",
        FullRelease.V_1_20_2 to "18",
        FullRelease.V_1_20_3 to "22",
        FullRelease.V_1_20_5 to "32",
        FullRelease.V_1_21 to "34",
        FullRelease.V_1_21_2 to "42",
        FullRelease.V_1_21_4 to "46",
        FullRelease.V_1_21_5 to "55",
        FullRelease.V_1_21_6 to "63",
        FullRelease.V_1_21_7 to "64",
        FullRelease.V_1_21_9 to "69.0",
        FullRelease.V_1_21_11 to "75",
        FullRelease.V_26_1 to "84.0",
    )

    fun getMinJavaVersion(minecraftVersion: MinecraftVersion): Int =
        requireNotNull(MIN_JAVA_VERSIONS.resolve(minecraftVersion))

    fun getDataPackFormat(minecraftVersion: MinecraftVersion): String? =
        DATA_PACK_FORMATS.resolve(minecraftVersion)

    fun getResourcePackFormat(minecraftVersion: MinecraftVersion): String? =
        RESOURCE_PACK_FORMATS.resolve(minecraftVersion)

    private fun <T> Map<MinecraftVersion, T>.resolve(minecraftVersion: MinecraftVersion): T? =
        entries
            .sortedWith(compareByDescending(MinecraftVersion.COMPARATOR) { it.key })
            .firstOrNull { minecraftVersion >= it.key }
            ?.value
}
