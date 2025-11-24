package io.github.recrafter.bedrock.era

import io.github.recrafter.bedrock.era.common.MinecraftEra
import io.github.recrafter.bedrock.versions.MinecraftVersion

enum class Classic(private val version: String) : MinecraftVersion {

    C_0_0_11A_LAUNCHER("0.0.11a-launcher"),
    C_0_0_12A_03("0.0.12a_03"),
    C_0_0_13A_03_LAUNCHER("0.0.13a_03-launcher"),
    C_0_0_14A_08("0.0.14a_08"),
    C_0_0_15A("0.0.15a"),
    C_0_0_16A_02("0.0.16a_02"),
    C_0_0_17A("0.0.17a"),
    C_0_0_18A_02("0.0.18a_02"),
    C_0_0_19A_06("0.0.19a_06"),
    C_0_0_20A_02("0.0.20a_02"),
    C_0_0_21A("0.0.21a"),
    C_0_0_22A_05("0.0.22a_05"),
    C_0_0_23A_01("0.0.23a_01"),
    C_0_24_ST_03("0.24_st_03"),
    C_0_25_05_ST("0.25_05_st"),
    C_0_27_ST("0.27_st"),
    C_0_28_01("0.28_01"),
    C_0_29_02("0.29_02"),
    C_0_30_C_RENEW("0.30-c-renew");

    override val era: MinecraftEra = MinecraftEra.CLASSIC

    override fun getEnumVersion(): String = version
}
