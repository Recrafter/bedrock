package io.github.recrafter.bedrock.crafter

import io.github.diskria.gradle.utils.extensions.common.requireGradleNotNull
import io.github.diskria.kotlin.utils.Constants
import io.github.diskria.kotlin.utils.extensions.common.`kebab-case`
import io.github.diskria.kotlin.utils.extensions.mappers.toEnum
import io.github.recrafter.bedrock.loaders.ModLoaderType
import io.github.recrafter.bedrock.versions.MinecraftVersion

sealed class CrafterFlow(val name: String) {

    protected fun requireProperty(name: String): String =
        requireGradleNotNull(findProperty(name)) {
            "Crafter property '${buildPropertyName(name)}' required for flow '$this' but not set."
        }

    override fun toString(): String = name

    companion object {
        private fun buildPropertyName(name: String): String =
            CrafterConstants.PLUGIN_LOWER_NAME + Constants.Char.DOT + name

        private fun findProperty(name: String): String? =
            System.getProperty(buildPropertyName(name))

        fun detect(): CrafterFlow {
            val flowValue = findProperty("flow")
            return values().firstOrNull { it.name == flowValue } ?: Normal
        }

        fun values(): List<CrafterFlow> =
            listOf(Normal, Single)
    }

    data object Normal : CrafterFlow("normal")

    data object Single : CrafterFlow("single") {
        val loader: ModLoaderType get() = requireProperty("loader").toEnum<ModLoaderType>(`kebab-case`)
        val version: MinecraftVersion get() = MinecraftVersion.parse(requireProperty("version"))
        val modProjectName: String get() = requireProperty("modProjectName")
    }
}
