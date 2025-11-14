package io.github.recrafter.bedrock.era

import io.github.recrafter.bedrock.era.common.MinecraftEra
import io.github.recrafter.bedrock.versions.MinecraftVersion

enum class Infdev(private val version: String) : MinecraftVersion {

    INFDEV_20100618("20100618");

    override fun getEra(): MinecraftEra = MinecraftEra.INFDEV

    override fun getEnumVersion(): String = version
}
